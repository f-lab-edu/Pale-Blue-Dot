package com.luke.palebluedot.repository;

import com.luke.palebluedot.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {
}
