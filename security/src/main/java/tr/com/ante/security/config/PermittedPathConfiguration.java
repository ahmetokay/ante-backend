package tr.com.ante.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application.security")
@Configuration
public class PermittedPathConfiguration {

    private List<String> permittedPathList = new ArrayList<>(Arrays.asList("/login", "/register", "/refresh-token", "/get-session", "/logout", "/delete-session", "/has-permission", "/v3/api-docs/**", "/swagger-ui/**"));

}