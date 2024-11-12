package com.koi.identity_service.service;


import com.koi.identity_service.dto.request.AuthenticationRequest;
import com.koi.identity_service.dto.request.IntrospectRequest;
import com.koi.identity_service.dto.response.AuthenticationResponse;
import com.koi.identity_service.dto.response.IntrospectResponse;
import com.koi.identity_service.exception.AppException;
import com.koi.identity_service.exception.ErrorCode;
import com.koi.identity_service.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected  String SIGNER_KEY;
    //Verify token
    public IntrospectResponse introspect(IntrospectRequest request)
            throws JOSEException, ParseException {
        var token = request.getToken();

        JWSVerifier verifier= new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);


        //Kiem tra token het han
        Date expityTime= signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .valid( verified && expityTime.after(new Date())
)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        System.out.println("Auth request: "+ request);
        var user = userRepository.findByuserName(request.getUserName())
                .orElseThrow( () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder= new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(),
                user.getPassword());
        if(!authenticated)
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        var token = generateToken(request.getUserName());

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }


    private String generateToken(String userName){

        //Build TOKEN
        //Build Header
        JWSHeader header= new JWSHeader(JWSAlgorithm.HS512);
        //Build Payload
        JWTClaimsSet jwtClaimsSet= new JWTClaimsSet.Builder()
                .subject(userName)// subject dai dien cho username dang nhap
                .issuer("cictkoi.com")//xac dinh token nay duoc issuer tu ai
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now(). plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))// thoi han token
                .claim("custom claim", "custom")
                .build();
        Payload payload= new Payload(jwtClaimsSet.toJSONObject());
        //Bui Signature
        JWSObject jwsObject= new JWSObject(header,payload);
        //Sign TOKEN
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));

            return jwsObject.serialize();//serialize ra kieu String

        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }
}
