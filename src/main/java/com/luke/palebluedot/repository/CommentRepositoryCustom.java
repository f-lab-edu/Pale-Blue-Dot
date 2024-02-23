package com.luke.palebluedot.repository;

import com.luke.palebluedot.domain.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
    List<Comment> getComments(int size, Long feedId);
}
