package com.luke.palebluedot.repository;

import com.luke.palebluedot.domain.Feed;

import java.util.List;

public interface FeedRepositoryCustom {
    List<Feed> findMoreFeeds(int size);
    List<Feed> getMyFeeds(int size, String memberName);
}
