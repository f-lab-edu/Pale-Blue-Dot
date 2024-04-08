package com.luke.palebluedot.response;

import com.luke.palebluedot.domain.Comment;
import com.luke.palebluedot.domain.Feed;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class FeedResponse {

    private final Long feedId;
    private final String feedContent;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;
    private final Integer likeCount;
    private final List<Comment> comments;

    @Builder
    public FeedResponse(Long feedId, String feedContent, LocalDateTime createDate, LocalDateTime updateDate, int likeCount, List<Comment> comments) {
        this.feedId = feedId;
        this.feedContent = feedContent;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.likeCount = likeCount;
        this.comments = comments;
    }

    public static FeedResponse from(Feed entity){
        return FeedResponse.builder()
                .feedContent(entity.getFeedContent())
                .build();
    }
}
