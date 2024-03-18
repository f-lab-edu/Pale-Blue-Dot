package com.luke.palebluedot.request;

import com.luke.palebluedot.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberCreate {
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;
    @NotBlank(message = "계정이름을 입력해주세요")
    private String memberName;
    @NotBlank(message = "이메일을 입력해주세요")
    private String email;

    @Builder
    public MemberCreate(String password, String memberName, String email) {
        this.password = password;
        this.memberName = memberName;
        this.email = email;
    }
    public MemberCreate toDTO(Member entity){
        return MemberCreate.builder()
                .password(entity.getPassword())
                .memberName(entity.getMemberName())
                .email(entity.getEmail())
                .build();
    }

}
