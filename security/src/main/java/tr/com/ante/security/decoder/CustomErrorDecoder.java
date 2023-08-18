package tr.com.ante.security.decoder;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import tr.com.ante.core.exception.RestApiClientException;
import tr.com.ante.core.exception.RestApiServerException;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String requestUrl = response.request().url();
        Response.Body responseBody = response.body();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());

        if (responseStatus.is5xxServerError()) {
            return new RestApiServerException("RestApiServerException: " + requestUrl);
        } else if (responseStatus.is4xxClientError()) {
            return new RestApiClientException("RestApiClientException: " + requestUrl);
        } else {
            return new Exception("Generic exception");
        }
    }
}
