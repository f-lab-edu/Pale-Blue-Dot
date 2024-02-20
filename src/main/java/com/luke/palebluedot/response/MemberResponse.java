package com.luke.palebluedot.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {

    private final Long memberId;
    private final String memberName;
    private final String email;

    @Builder
    public MemberResponse(Long memberId, String memberName, String email) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.email = email;
    }
}
