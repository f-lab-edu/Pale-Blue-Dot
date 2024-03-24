package com.luke.palebluedot.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreate {
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;
    @NotBlank(message = "계정이름을 입력해주세요")
    private String memberName;
    @NotBlank(message = "이메일을 입력해주세요")
    private String email;
}
