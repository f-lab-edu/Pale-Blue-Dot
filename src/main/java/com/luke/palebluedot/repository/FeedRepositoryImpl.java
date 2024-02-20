package com.luke.palebluedot.repository;

import com.luke.palebluedot.domain.Feed;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import java.util.List;
import static com.luke.palebluedot.domain.QFeed.feed;

public class FeedRepositoryImpl extends QuerydslRepositorySupport implements FeedRepositoryCustom {
    public FeedRepositoryImpl(EntityManager em) {
        super(Feed.class);
        super.setEntityManager(em);
    }

    @Override
    public List<Feed> getFeeds(int size){
        return from(feed)
                .orderBy(feed.feedId.asc())
                .limit(size)
                .fetch();
    }
}
