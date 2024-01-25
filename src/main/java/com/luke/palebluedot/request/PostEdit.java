package com.luke.palebluedot.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostEdit {
    private final String content;
    private LocalDateTime updateDate;
    private int likeCount;
    private String useYn;

    @Builder
    public PostEdit(String content, LocalDateTime updateDate, int likeCount, String useYn) {
        this.content = content;
        this.updateDate = updateDate;
        this.likeCount = likeCount;
        this.useYn = useYn;
    }

}
