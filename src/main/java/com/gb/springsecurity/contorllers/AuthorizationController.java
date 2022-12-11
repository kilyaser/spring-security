package com.gb.springsecurity.contorllers;

import com.gb.springsecurity.services.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RequiredArgsConstructor
@RestController
@Slf4j
public class AuthorizationController {
//    private final AuthenticationManager authenticationManager;
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtService jwtService;

    @PostMapping("/auth")
    public AuthResponse authorize(@RequestBody AuthRequest request) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

        try {
//          Authentication authenticate = authenticationManager.authenticate(authentication);

            Authentication authenticate = daoAuthenticationProvider.authenticate(authentication);
            UserDetails user = (UserDetails) authenticate.getPrincipal();
            String jwt = jwtService.generateJwtToken(user);

            return new AuthResponse(jwt);

        } catch (AuthenticationException e) {
            log.error("Bad credentials for [{}, {}]", request.getUsername(), request.getPassword());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }


    }
}
