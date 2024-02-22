package com.luke.palebluedot.repository;

import com.luke.palebluedot.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
