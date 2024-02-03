package com.luke.palebluedot.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "MEMBER_NAME")
    private String memberName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CREATE_DATE")
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "UPDATE_DATE")
    @LastModifiedDate
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "member")
    private List<Feed> feeds = new ArrayList<>();

    @Builder
    public Member(String memberId, String password, String memberName, String email, List<Feed> feeds){
        this.memberId = memberId;
        this.password = password;
        this.memberName = memberName;
        this.email = email;
        this.feeds = feeds;
    }
}
