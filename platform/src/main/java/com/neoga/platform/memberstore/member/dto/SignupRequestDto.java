package com.neoga.platform.memberstore.member.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignupRequestDto {
    private String uid;
    private String passwd;
    private String name;
}
