package com.luke.palebluedot.request;

import com.luke.palebluedot.domain.Feed;
import com.luke.palebluedot.domain.FeedImage;
import com.luke.palebluedot.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class FeedCreateRequest {
    @NotBlank(message = "내용을 입력해주세요")
    private String content;
    private Member member;
    private List<FeedImage> feedImages;

    @Builder
    public FeedCreateRequest(String content, Member member, List<FeedImage> feedImages) {
        this.content = content;
        this.member = member;
        this.feedImages = feedImages;
    }

    public static FeedCreateRequest toDTO(Feed entity){
        return FeedCreateRequest.builder()
                .content(entity.getFeedContent())
                .member(entity.getMember())
                .feedImages(entity.getFeedImages())
                .build();
    }

    public static Feed toEntity(FeedCreateRequest dto, Member member, List<FeedImage> feedImageList){
        return Feed.builder()
                .feedContent(dto.getContent())
                .member(member)
                .feedImages(feedImageList)
                .build();
    }


}
