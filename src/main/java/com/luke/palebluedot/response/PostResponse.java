package com.luke.palebluedot.response;

import com.luke.palebluedot.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponse {

    private Long id;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private int likeCount;

    @Builder
    public PostResponse(Long id, String content, LocalDateTime createDate, LocalDateTime updateDate, int likeCount) {
        this.id = id;
        this.content = content.substring(0, Math.min(content.length(), 10));
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.likeCount = likeCount;
    }
}
