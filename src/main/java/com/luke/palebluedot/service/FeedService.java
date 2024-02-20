package com.luke.palebluedot.service;

import com.luke.palebluedot.domain.Member;
import com.luke.palebluedot.domain.Feed;
import com.luke.palebluedot.domain.QFeed;
import com.luke.palebluedot.repository.FeedRepository;
import com.luke.palebluedot.repository.MemberRepository;
import com.luke.palebluedot.request.FeedCreate;
import com.luke.palebluedot.request.FeedEdit;
import com.luke.palebluedot.response.FeedResponse;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class FeedService {

    private final FeedRepository feedRepository;
    private final MemberRepository memberRepository;


    public FeedService(FeedRepository feedRepository, MemberRepository memberRepository) {
        this.feedRepository = feedRepository;
        this.memberRepository = memberRepository;
    }

    public void createFeed(FeedCreate feedCreate, String memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(()->new IllegalArgumentException("회원 정보가 없습니다."));

        Feed feed = Feed.builder()
                .content(feedCreate.getContent())
                .member(member)
                .build();
        feedRepository.save(feed);

    }

    public FeedResponse getFeed(Long feedId) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(()->new IllegalArgumentException("게시글이 없습니다."));

        return FeedResponse.builder()
                .feedId(feed.getFeedId())
                .content(feed.getContent())
                .build();
    }

    public List<Feed> getFeeds(int size){
        return feedRepository.getFeeds(size);
    }


    public void editFeed(Long feedId, FeedEdit feedEdit){
        Feed existingFeed = feedRepository.findById(feedId)
                .orElseThrow(()->new IllegalArgumentException("아이디가 없습니다."));
        Feed changedFeed = existingFeed.builder()
                .content(feedEdit.getContent())
                .build();
        feedRepository.save(changedFeed);
    }

    public void deleteFeed(Long feedId) {
        feedRepository.deleteById(feedId);
    }


}
