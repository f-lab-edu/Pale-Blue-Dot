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
    public List<Feed> findMoreFeeds(int size, Long lastFeedId){
        return from(feed)
                .where(feed.feedId.lt(lastFeedId))
                .orderBy(feed.feedId.asc())
                .limit(size)
                .fetch();
    }

    @Override
    public List<Feed> getMyFeeds(int size, Long memberId){
        return from(feed)
                .where(feed.member.memberId.eq(memberId))
                .orderBy(feed.feedId.asc())
                .limit(size)
                .fetch();
    }
}
