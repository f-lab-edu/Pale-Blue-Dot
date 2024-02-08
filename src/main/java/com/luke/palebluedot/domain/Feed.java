package com.luke.palebluedot.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@NoArgsConstructor(access= AccessLevel.PUBLIC)
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FEED_ID")
    private Long feedId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Lob
    @Column(name = "FEED_CONTENT")
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
    public Feed(String content, Member member) {
        this.content = content;
        this.member = member;
    }
}
