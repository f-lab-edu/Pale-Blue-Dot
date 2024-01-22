package com.luke.palebluedot.response;

import com.luke.palebluedot.domain.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponse {

    private Long id;
    private String content;

    @Builder
    public PostResponse(Long id, String content) {
        this.id = id;
        // 모든 내용을 다 가져오면 길어지니까 일부분만 보이도록 이 이상 길어지면 '더보기' 클릭해서 보는 식으로
        this.content = content.substring(0, Math.min(content.length(), 10));
    }
}
