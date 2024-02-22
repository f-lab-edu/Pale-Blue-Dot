package com.luke.palebluedot.service;

import com.luke.palebluedot.domain.Feed;
import com.luke.palebluedot.domain.Member;
import com.luke.palebluedot.repository.FeedRepository;
import com.luke.palebluedot.repository.MemberRepository;
import com.luke.palebluedot.request.FeedCreate;
import com.luke.palebluedot.request.FeedEdit;
import com.luke.palebluedot.response.FeedResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

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

    public void createFeed(FeedCreate feedCreate, String memberName) {
        Member member = memberRepository.findByMemberName(memberName)
                .orElseThrow(()->new IllegalArgumentException("회원 정보가 없습니다."));

        Feed feed = Feed.builder()
                .feedContent(feedCreate.getContent())
                .member(member)
                .build();
        feedRepository.save(feed);

    }

    public FeedResponse getFeed(Long feedId) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(()->new IllegalArgumentException("게시글이 없습니다."));

        return FeedResponse.builder()
                .feedId(feed.getFeedId())
                .content(feed.getFeedContent())
                .build();
    }

    public List<Feed> getFeeds(int size){
        return feedRepository.getFeeds(size);
    }


    public void editFeed(Long feedId, FeedEdit feedEdit){
        Optional<Feed> searchFeed = feedRepository.findById(feedId);
        if(searchFeed.isPresent()){
            Feed changedFeed = Feed.builder()
                    .feedContent(feedEdit.getContent())
                    .build();
            feedRepository.save(changedFeed);
        }else{
            throw new IllegalArgumentException("게시물이 없습니다.");
        }
    }

    public void deleteFeed(Long feedId) {
        feedRepository.deleteById(feedId);
    }


}
