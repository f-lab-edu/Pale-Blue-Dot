package com.luke.palebluedot.response;

import com.luke.palebluedot.domain.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class FeedResponse {

    private final Long feedId;
    private final String content;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;
    private final Integer likeCount;
    private List<Comment> comments;

    @Builder
    public FeedResponse(Long feedId, String content, LocalDateTime createDate, LocalDateTime updateDate, int likeCount, List<Comment> comments) {
        this.feedId = feedId;
        this.content = content.substring(0, Math.min(content.length(), 10));
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.likeCount = likeCount;
        this.comments = comments;
    }
}
