package com.luke.palebluedot.repository;

import com.luke.palebluedot.domain.Feed;

import java.util.List;

public interface FeedRepositoryCustom {
    List<Feed> findMoreFeeds(int size, Long lastFeedId);
    List<Feed> getMyFeeds(int size, Long memberId);
}
