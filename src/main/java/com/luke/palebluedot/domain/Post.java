package com.luke.palebluedot.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access= AccessLevel.PUBLIC)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //내용 작성
    @Lob
    private String content;

    //파일 저장 경로를 불러와서 보여줄것?
    private String filePath;

    @Builder
    public Post(String content, String filePath) {
        this.content = content;
        this.filePath = filePath;
    }
}
