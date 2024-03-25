package com.luke.palebluedot.request;

import com.luke.palebluedot.domain.Feed;
import com.luke.palebluedot.domain.FeedImage;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class FeedEditRequest {
    private final String content;
    private final List<FeedImage> feedImages;

    @Builder
    public FeedEditRequest(String content, List<FeedImage> feedImages) {
        this.content = content;
        this.feedImages = feedImages;
    }

    public static FeedEditRequest toDTO(Feed entity) {
        return FeedEditRequest.builder()
                .content(entity.getFeedContent())
                .feedImages(entity.getFeedImages())
                .build();
    }

}
