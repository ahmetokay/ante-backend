package tr.com.ante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
//@OpenAPIDefinition(info = @Info(title = "Auth server", version = "1.0", description = "description"))
@SpringBootApplication(scanBasePackages = "tr.com.ante")
public class AuthenticationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServerApplication.class, args);
    }
}