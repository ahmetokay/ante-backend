package tr.com.ante.security.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tr.com.ante.core.model.ResponseModel;
import tr.com.ante.security.decoder.CustomErrorDecoder;
import tr.com.ante.security.model.TokenRecord;
import tr.com.ante.security.model.UserPrincipal;

@FeignClient(value = "authentication-server", configuration = {CustomErrorDecoder.class})
public interface AuthenticationClient {

    @PostMapping("/auth/get-session")
    ResponseModel<UserPrincipal> getSession(@RequestBody TokenRecord tokenRecord);

    @PostMapping("/auth/delete-session")
    ResponseModel<Boolean> deleteSession(@RequestBody TokenRecord tokenRecord);

}