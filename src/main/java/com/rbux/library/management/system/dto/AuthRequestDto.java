package com.rbux.library.management.system.dto;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class AuthRequestDto {
    private String email;
    private String password;
}
