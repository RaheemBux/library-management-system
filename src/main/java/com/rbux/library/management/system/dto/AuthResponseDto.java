package com.rbux.library.management.system.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class AuthResponseDto {
    private String jwtToken;
    private String username;
}
