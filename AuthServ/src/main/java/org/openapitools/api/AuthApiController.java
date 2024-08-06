package org.openapitools.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-06T14:49:25.152364100+05:00[Asia/Yekaterinburg]")

@Controller
@RequestMapping("${openapi.authenticationService.base-path:}")
public class AuthApiController implements AuthApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AuthApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
