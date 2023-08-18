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
@Configuration
public class FilterIgnoredPathConfiguration {

    private List<String> ignoredPathList = new ArrayList<>(Arrays.asList("/access-log", "/export"));

}