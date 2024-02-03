package com.luke.palebluedot.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberResponse {

    private String memberId;
    private String memberName;
    private String email;

    @Builder
    public MemberResponse(String memberId, String memberName, String email) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.email = email;
    }
}
