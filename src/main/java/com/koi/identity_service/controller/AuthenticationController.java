package com.koi.identity_service.controller;

import com.cloudinary.Api;
import com.koi.identity_service.dto.request.ApiResponse;
import com.koi.identity_service.dto.request.AuthenticationRequest;
import com.koi.identity_service.dto.request.IntrospectRequest;
import com.koi.identity_service.dto.response.AuthenticationResponse;
import com.koi.identity_service.dto.response.IntrospectResponse;
import com.koi.identity_service.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor //tu dong Autowired cac bien
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        System.out.println("Request sent: "+request);
        var result= authenticationService.authenticate(request);
        System.out.println("Result: "+ result);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        System.out.println("Request sent: "+request);
        var result= authenticationService.introspect(request);
        System.out.println("Result: "+ result);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

}
