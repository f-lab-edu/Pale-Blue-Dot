package com.luke.palebluedot.request;

import com.luke.palebluedot.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberEdit {
    private final String memberName;
    private final String password;
    private final String email;

    @Builder
    public MemberEdit(String memberName, String password, String email) {
        this.memberName = memberName;
        this.password = password;
        this.email = email;
    }

    public MemberEdit toDTO(Member entity){
        return MemberEdit.builder()
                .password(entity.getPassword())
                .memberName(entity.getMemberName())
                .email(entity.getEmail())
                .build();
    }


}
