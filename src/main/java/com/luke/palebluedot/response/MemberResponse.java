package com.luke.palebluedot.response;

import com.luke.palebluedot.domain.Member;
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

    public static MemberResponse from(Member entity){
        return MemberResponse.builder()
                .memberId(entity.getMemberId())
                .build();
    }
}
