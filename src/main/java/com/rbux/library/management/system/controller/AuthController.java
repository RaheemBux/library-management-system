package com.rbux.library.management.system.controller;

import com.rbux.library.management.system.dto.AuthRequestDto;
import com.rbux.library.management.system.dto.AuthResponseDto;
import com.rbux.library.management.system.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/login")
@Slf4j
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Endpoint to generate token
     * @param authRequestDto request payload to generate token
     * @return return the response dto which include token information
     */
    @PostMapping
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
        log.info("login request initiated");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequestDto.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        AuthResponseDto response = AuthResponseDto.builder()
                .username(userDetails.getUsername())
                .jwtToken(jwt)
                .build();
        log.info("login request completed");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
