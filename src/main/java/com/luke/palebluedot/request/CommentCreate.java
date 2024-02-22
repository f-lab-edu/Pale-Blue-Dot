package com.luke.palebluedot.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreate {
    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    @Builder
    public CommentCreate(String content) {
        this.content = content;
    }
}
