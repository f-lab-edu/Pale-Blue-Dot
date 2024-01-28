package com.luke.palebluedot.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@NoArgsConstructor(access= AccessLevel.PUBLIC)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long postId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Lob
    @Column(name = "POST_CONTENT")
    private String content;

    @Column(name = "CREATE_DATE")
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "UPDATE_DATE")
    @LastModifiedDate
    private LocalDateTime updateDate;

    @Column(name = "LIKE_COUNT")
    private Integer likeCount;

    @Builder
    public Post(String content, Long memberId) {
        this.content = content;
        Member.builder().memberId(memberId).build();
    }
}
