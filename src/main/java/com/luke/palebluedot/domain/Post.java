package com.luke.palebluedot.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor(access= AccessLevel.PUBLIC)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    @Lob
    @Column(name = "POST_CONTENT")
    private String content;
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;
    @Column(name = "LIKE_COUNT")
    private int likeCount;
    @Column(name = "USEY_YN")
    private String useYn;


    @Builder
    public Post(String content, LocalDateTime createDate, LocalDateTime updateDate, String useYn) {
        this.content = content;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.useYn = useYn;
    }
}
