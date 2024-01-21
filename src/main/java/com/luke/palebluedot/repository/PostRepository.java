package com.luke.palebluedot.repository;

import com.luke.palebluedot.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
