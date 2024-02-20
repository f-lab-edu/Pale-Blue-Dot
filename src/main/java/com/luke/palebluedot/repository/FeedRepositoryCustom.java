package com.luke.palebluedot.repository;

import com.luke.palebluedot.domain.Feed;
import com.querydsl.core.QueryResults;

import java.util.List;

public interface FeedRepositoryCustom {
    List<Feed> getFeeds(int size);
}
