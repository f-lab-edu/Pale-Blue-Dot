package com.luke.palebluedot.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostEditor {
    private final String content;

    @Builder
    public PostEditor(String content) {
        this.content = content;
    }

}
