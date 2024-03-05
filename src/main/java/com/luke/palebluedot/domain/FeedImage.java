package com.luke.palebluedot.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@NoArgsConstructor(access= AccessLevel.PUBLIC)
@EntityListeners(AuditingEntityListener.class)
public class FeedImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_image_id")
    private Long feedImageId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @Column(name = "create_date")
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "update_date")
    @LastModifiedDate
    private LocalDateTime updateDate;

    @Column(name = "file_path")
    private String filePath;

    @Builder
    public FeedImage(String filePath) {
        this.filePath = filePath;
    }
}
