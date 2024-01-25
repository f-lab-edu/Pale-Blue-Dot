package com.luke.palebluedot.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class PostCreate {
    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    @Builder
    public PostCreate(String content) {
        this.content = content;

    }
}
