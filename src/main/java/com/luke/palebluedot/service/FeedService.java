package com.luke.palebluedot.service;

import com.luke.palebluedot.domain.Member;
import com.luke.palebluedot.domain.Feed;
import com.luke.palebluedot.repository.FeedRepository;
import com.luke.palebluedot.request.FeedCreate;
import com.luke.palebluedot.request.FeedEdit;
import com.luke.palebluedot.response.FeedResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FeedService {

    private final FeedRepository feedRepository;

    public FeedService(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    public void write(FeedCreate feedCreate, String memberId) {
        Member member = Member.builder().memberId(memberId).build();

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
