package tr.com.ante.security.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import tr.com.ante.core.exception.CacheException;
import tr.com.ante.core.exception.SessionExpiredException;
import tr.com.ante.security.model.UserPrincipal;

import java.util.concurrent.TimeUnit;

@Component
public class SessionServiceImpl implements SessionService {

    private final String SESSION_ACCESS_TOKEN_PREFIX = "ACCESS_TOKEN_";
    private final String SESSION_REFRESH_TOKEN_PREFIX = "REFRESH_TOKEN_";

    private final RedisTemplate redisTemplate;

    @Value("${spring.data.redis.access-token-ttl}")
    private long accessTokenTTL;
    @Value("${spring.data.redis.refresh-token-ttl}")
    private long refreshTokenTTL;
    private ValueOperations<String, Object> valueOps;

    public SessionServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;

        valueOps = redisTemplate.opsForValue();
    }

    @Override
    public boolean hasSession(String token) {
        return redisTemplate.hasKey(token);
    }

    @Override
    public UserPrincipal getSession(String token) {
        Boolean b = redisTemplate.hasKey(token);
        if (Boolean.TRUE.equals(b)) {
            return (UserPrincipal) valueOps.get(token);
        }

        throw new SessionExpiredException("Session bulunamadı.");
    }

    @Override
    public void createSession(String accessToken, String refreshToken, UserDetails userDetails) {
        try {
            valueOps.set(accessToken, userDetails);
            redisTemplate.expire(accessToken, accessTokenTTL, TimeUnit.MILLISECONDS);

            valueOps.set(refreshToken, userDetails);
            redisTemplate.expire(refreshToken, refreshTokenTTL, TimeUnit.MILLISECONDS);
        } catch (RuntimeException e) {
            throw new CacheException("Session kaydedilirken hata oluştu.");
        }
    }

    @Override
    public void logoutSession(String accessToken, String refreshToken) {
        Boolean accessTokenExists = redisTemplate.hasKey(accessToken);
        if (Boolean.TRUE.equals(accessTokenExists)) {
            valueOps.getAndDelete(accessToken);
        }

        if (refreshToken != null) {
            Boolean refreshTokenExists = redisTemplate.hasKey(refreshToken);
            if (Boolean.TRUE.equals(refreshTokenExists)) {
                valueOps.getAndDelete(refreshToken);
            }
        }
    }
}