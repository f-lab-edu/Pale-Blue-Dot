package com.luke.palebluedot.request;

import com.luke.palebluedot.domain.Feed;
import com.luke.palebluedot.domain.FeedImage;
import com.luke.palebluedot.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class FeedCreateRequest {
    @NotBlank(message = "내용을 입력해주세요")
    private final String feedContent;
    private final Member member;
    private final List<FeedImage> feedImages;

    @Builder
    public FeedCreateRequest(String feedContent, Member member, List<FeedImage> feedImages) {
        this.feedContent = feedContent;
        this.member = member;
        this.feedImages = feedImages;
    }


    public static Feed of(FeedCreateRequest dto, Member member, List<FeedImage> feedImageList){
        return Feed.builder()
                .feedContent(dto.getFeedContent())
                .member(member)
                .feedImages(feedImageList)
                .build();
    }


}
