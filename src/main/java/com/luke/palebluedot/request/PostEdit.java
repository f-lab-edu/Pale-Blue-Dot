package com.luke.palebluedot.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostEdit {
    private final String content;
    private int likeCount;

    @Builder
    public PostEdit(String content, int likeCount) {
        this.content = content;
        this.likeCount = likeCount;
    }

}
