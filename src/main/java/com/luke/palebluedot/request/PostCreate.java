package com.luke.palebluedot.request;

import com.luke.palebluedot.domain.Member;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

import static jakarta.persistence.FetchType.LAZY;

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
