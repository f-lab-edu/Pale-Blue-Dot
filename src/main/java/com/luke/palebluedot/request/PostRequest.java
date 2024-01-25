package com.luke.palebluedot.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostRequest {

    @NotBlank(message = "내용을 입력해주세요")
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private int likeCount;
    private String useYn;

    @Builder
    public PostRequest(String content, LocalDateTime createDate, LocalDateTime updateDate, int likeCount, String useYn) {
        this.content = content.substring(0, Math.min(content.length(), 10));
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.likeCount = likeCount;
        this.useYn = useYn;
    }
}
