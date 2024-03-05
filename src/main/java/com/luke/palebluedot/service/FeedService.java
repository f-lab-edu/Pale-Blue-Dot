package com.luke.palebluedot.service;

import com.luke.palebluedot.domain.Comment;
import com.luke.palebluedot.domain.Feed;
import com.luke.palebluedot.domain.FeedImage;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FeedService {

    private final FeedRepository feedRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final FeedImageService feedImageService;

    public FeedService(FeedRepository feedRepository, MemberRepository memberRepository, CommentRepository commentRepository, FeedImageService feedImageService) {
        this.feedRepository = feedRepository;
        this.memberRepository = memberRepository;
        this.commentRepository = commentRepository;
        this.feedImageService = feedImageService;
    }

    @Transactional
    public void createFeed(FeedCreate feedCreate, Long memberId, List<MultipartFile> files) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new IllegalArgumentException("회원 정보가 없습니다."));

        List<FeedImage> feedImageList = new ArrayList<>();
        if(files != null && !files.isEmpty()){
            for(MultipartFile uploadedFile : files){
                FeedImage feedImage = feedImageService.uploadFile(uploadedFile, memberId);
                feedImageList.add(feedImage);
            }
        }
        Feed feed = Feed.builder()
                .feedContent(feedCreate.getContent())
                .member(member)
                .feedImages(feedImageList)
                .build();
        feedRepository.save(feed);

    }
    @Transactional
    public FeedResponse getFeed(Long feedId, int size) {
        Feed existfeed = feedRepository.findById(feedId)
                .orElseThrow(()->new IllegalArgumentException("게시글이 없습니다."));

        List<Comment> comments = commentRepository.getComments(size, feedId);

        return FeedResponse.builder()
                .content(existfeed.getFeedContent())
                .comments(comments)
                .build();


    }
    @Transactional
    public List<Feed> findMoreFeeds(int size, Long lastFeedId){
        return feedRepository.findMoreFeeds(size, lastFeedId);
    }
    @Transactional
    public List<Feed> getMyFeeds(int size, String memberName){
        return feedRepository.getMyFeeds(size, memberName);
    }

    @Transactional
    public void editFeed(Long feedId, FeedEdit feedEdit){
        Feed existFeed = feedRepository.findById(feedId)
                .orElseThrow(() -> new IllegalArgumentException("피드가 존재하지 않습니다."));

        existFeed = Feed.builder()
                .feedContent(feedEdit.getContent())
                .build();
        feedRepository.save(existFeed);

    }
    @Transactional
    public void deleteFeed(Long feedId) {
        feedRepository.deleteById(feedId);
    }


}
