package com.luke.palebluedot.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostCreate {

    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    @NotBlank(message = "이미지를 첨부해주세요")
    private String filePath;

    @Builder
    public PostCreate(String content, String filePath) {
        this.content = content;
        this.filePath = filePath;
    }
}
