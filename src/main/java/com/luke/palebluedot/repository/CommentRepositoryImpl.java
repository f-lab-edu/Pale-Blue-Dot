package com.luke.palebluedot.repository;

import com.luke.palebluedot.domain.Comment;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.luke.palebluedot.domain.QComment.comment;
import static com.luke.palebluedot.domain.QFeed.feed;

public class CommentRepositoryImpl extends QuerydslRepositorySupport implements CommentRepositoryCustom{
    public CommentRepositoryImpl(EntityManager em) {
        super(Comment.class);
        super.setEntityManager(em);
    }

    @Override
    public List<Comment> getComments(int size, Long feedId, Long lastCommentId){
        return from(comment)
                .where(comment.feed.feedId.eq(feedId)
                        .and(comment.commentId.lt(lastCommentId)))
                .orderBy(comment.createDate.asc())
                .limit(size)
                .fetch();
    }
}
