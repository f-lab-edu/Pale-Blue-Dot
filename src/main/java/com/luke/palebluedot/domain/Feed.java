package com.luke.palebluedot.domain;

import com.luke.palebluedot.request.FeedEditRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@NoArgsConstructor(access= AccessLevel.PUBLIC)
@EntityListeners(AuditingEntityListener.class)
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Long feedId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Lob
    @Column(name = "feed_content")
    private String feedContent;

    @Column(name = "create_date")
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "update_date")
    @LastModifiedDate
    private LocalDateTime updateDate;

    @Column(name = "like_count")
    private Integer likeCount;

    @OneToMany(mappedBy = "feed")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "feed")
    private List<FeedImage> feedImages = new ArrayList<>();

    @Builder
    public Feed(Long feedId, String feedContent, Member member, List<Comment> comments, List<FeedImage> feedImages) {
        this.feedId = feedId;
        this.feedContent = feedContent;
        this.member = member;
        this.comments = comments;
        this.feedImages = feedImages;
    }

    public void update(FeedEditRequest feedEditRequest) {
        this.feedContent = feedEditRequest.getContent();
        this.feedImages = feedEditRequest.getFeedImages();
    }
}
