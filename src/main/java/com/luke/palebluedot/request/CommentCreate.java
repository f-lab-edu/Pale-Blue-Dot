package com.luke.palebluedot.request;

import com.luke.palebluedot.domain.Feed;
import com.luke.palebluedot.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CommentCreate {
    private Feed feed;
    private Member member;
    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    @Builder
    public CommentCreate(String content) {
        this.content = content;
    }
}
