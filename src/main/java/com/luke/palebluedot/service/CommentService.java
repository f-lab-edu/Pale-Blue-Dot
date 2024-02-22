package com.luke.palebluedot.service;

import com.luke.palebluedot.domain.Comment;
import com.luke.palebluedot.domain.Feed;
import com.luke.palebluedot.domain.Member;
import com.luke.palebluedot.repository.CommentRepository;
import com.luke.palebluedot.repository.FeedRepository;
import com.luke.palebluedot.repository.MemberRepository;
import com.luke.palebluedot.request.CommentCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final FeedRepository feedRepository;
    private final MemberRepository memberRepository;


    @Autowired
    public CommentService(CommentRepository commentRepository,
                            FeedRepository feedRepository,
                            MemberRepository memberRepository) {
        this.commentRepository = commentRepository;
        this.feedRepository = feedRepository;
        this.memberRepository = memberRepository;
    }

    public void createComment(CommentCreate commentCreate, Long feedId, String memberName){
        Feed feed = feedRepository.findById(feedId).orElseThrow(() -> new IllegalArgumentException("피드가 존재하지 않습니다."));
        Member member = memberRepository.findByMemberName(memberName).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        Comment comment = Comment.builder()
                .commentContent(commentCreate.getContent())
                .feed(feed)
                .member(member)
                .build();

        commentRepository.save(comment);
    }
}
