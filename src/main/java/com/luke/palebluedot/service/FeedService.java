package com.luke.palebluedot.service;

import com.luke.palebluedot.domain.Comment;
import com.luke.palebluedot.domain.Feed;
import com.luke.palebluedot.domain.Member;
import com.luke.palebluedot.repository.CommentRepository;
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
    private final CommentRepository commentRepository;


    public FeedService(FeedRepository feedRepository, MemberRepository memberRepository, CommentRepository commentRepository) {
        this.feedRepository = feedRepository;
        this.memberRepository = memberRepository;
        this.commentRepository = commentRepository;
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

    public FeedResponse getFeed(Long feedId, int size) {
        Optional<Feed> optionalFeed = feedRepository.findById(feedId);

        if (optionalFeed.isPresent()) {
            Feed feed = optionalFeed.get();
            List<Comment> comments = commentRepository.getComments(size, feedId);

            return FeedResponse.builder()
                    .content(feed.getFeedContent())
                    .comments(comments)
                    .build();
        }else{
            throw new IllegalArgumentException("게시물이 없습니다.");
        }

    }

    public List<Feed> findMoreFeeds(int size){
        return feedRepository.findMoreFeeds(size);
    }

    public List<Feed> getMyFeeds(int size, String memberName){
        return feedRepository.getMyFeeds(size, memberName);
    }


    public void editFeed(Long feedId, FeedEdit feedEdit){
        Optional<Feed> optionalFeed = feedRepository.findById(feedId);
        if(optionalFeed.isPresent()){
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
