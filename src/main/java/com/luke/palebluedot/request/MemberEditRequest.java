package com.luke.palebluedot.request;

import com.luke.palebluedot.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberEditRequest {
    private final String memberName;
    private final String password;
    private final String email;

    @Builder
    public MemberEditRequest(String memberName, String password, String email) {
        this.memberName = memberName;
        this.password = password;
        this.email = email;
    }

    public static MemberEditRequest toDTO(Member entity){
        return MemberEditRequest.builder()
                .password(entity.getPassword())
                .memberName(entity.getMemberName())
                .email(entity.getEmail())
                .build();
    }


}
