package com.luke.palebluedot.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FeedEdit {
    private final String content;
    private final Integer likeCount;

    @Builder
    public FeedEdit(String content, Integer likeCount) {
        this.content = content;
        this.likeCount = likeCount;
    }

}
