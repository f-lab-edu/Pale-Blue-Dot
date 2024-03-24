package com.luke.palebluedot.request;

import com.luke.palebluedot.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestMemberCreate {
    @NotBlank(message = "비밀번호를 입력해주세요")
    private final String password;
    @NotBlank(message = "계정이름을 입력해주세요")
    private final String memberName;
    @NotBlank(message = "이메일을 입력해주세요")
    private final String email;

    @Builder
    public RequestMemberCreate(String password, String memberName, String email) {
        this.password = password;
        this.memberName = memberName;
        this.email = email;
    }
    public static RequestMemberCreate toDTO(Member entity){
        return RequestMemberCreate.builder()
                .password(entity.getPassword())
                .memberName(entity.getMemberName())
                .email(entity.getEmail())
                .build();
    }

    public static Member toEntity(RequestMemberCreate dto){
        return Member.builder()
                .password(dto.getPassword())
                .email(dto.getEmail())
                .memberName(dto.getMemberName())
                .build();
    }

}
