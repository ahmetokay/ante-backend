package tr.com.ante.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tr.com.ante.auth.model.AuthenticationResponse;
import tr.com.ante.security.model.UserPrincipal;
import tr.com.ante.security.service.JwtService;
import tr.com.ante.security.service.SessionService;
import tr.com.ante.security.user.converter.UserConverter;
import tr.com.ante.security.user.entity.UserEntity;
import tr.com.ante.security.user.model.UserModel;
import tr.com.ante.security.user.repository.UserRepository;
import tr.com.ante.security.utils.PermissionUtils;

import java.io.IOException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;
    private final UserConverter converter;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final SessionService sessionService;

    @Override
    public AuthenticationResponse register(UserModel userModel) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userModel.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userEntity.setName(userModel.getName());
        userEntity.setSurname(userModel.getSurname());

        var user = User.builder()
                .username(userModel.getEmail())
                .password(passwordEncoder.encode(userModel.getPassword()))
                .build();

        repository.save(userEntity);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userModel.getEmail());
        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(accessToken, refreshToken, userDetails);
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(UserModel userModel) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userModel.getEmail(),
                        userModel.getPassword()
                )
        );

        repository.findByEmail(userModel.getEmail()).orElseThrow();

        UserDetails userDetails = userDetailsService.loadUserByUsername(userModel.getEmail());
        var token = jwtService.generateToken(userDetails);
        var refreshToken = jwtService.generateRefreshToken(userDetails);
        saveUserToken(token, refreshToken, userDetails);
        return AuthenticationResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String oldAccessToken;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        oldAccessToken = authHeader.substring(7);
        username = jwtService.extractUsername(oldAccessToken);
        if (username != null) {
            repository.findByEmail(username).orElseThrow();

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(oldAccessToken, userDetails)) {
                var accessToken = jwtService.generateToken(userDetails);
                var refreshToken = jwtService.generateRefreshToken(userDetails);
                deleteUserToken(oldAccessToken, null);
                saveUserToken(accessToken, refreshToken, userDetails);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    @Override
    public UserPrincipal getSession(String accessToken) {
        return sessionService.getSession(accessToken);
    }

    @Override
    public UserModel getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserPrincipal) {
                return converter.toModel(repository.getById(((UserPrincipal) principal).getUserId()));
            }
        }

        return null;
    }

    @Override
    public void logout(String accessToken, String refreshToken) {
        deleteUserToken(accessToken, refreshToken);
    }

    @Override
    public boolean hasPermission(String permission) {
        return PermissionUtils.checkPermission(SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(t -> t.getAuthority()).collect(Collectors.toList()), permission);
    }

    private void deleteUserToken(String accessToken, String refreshToken) {
        sessionService.logoutSession(accessToken, refreshToken);
    }

    private void saveUserToken(String accessToken, String refreshToken, UserDetails userDetails) {
        sessionService.createSession(accessToken, refreshToken, userDetails);
    }
}