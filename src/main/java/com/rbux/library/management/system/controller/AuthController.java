package com.rbux.library.management.system.controller;

import com.rbux.library.management.system.dto.AuthRequestDto;
import com.rbux.library.management.system.dto.AuthResponseDto;
import com.rbux.library.management.system.util.JwtUtil;
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
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequestDto.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        AuthResponseDto response = AuthResponseDto.builder()
                .username(userDetails.getUsername())
                .jwtToken(jwt)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
