package com.luke.palebluedot.ServiceTest

import com.luke.palebluedot.domain.Feed
import com.luke.palebluedot.repository.FeedRepository
import com.luke.palebluedot.repository.MemberRepository
import com.luke.palebluedot.response.FeedResponse
import com.luke.palebluedot.service.FeedService
import spock.lang.Specification

class FeedServiceTest extends Specification {

    FeedService feedService
    FeedRepository feedRepository = Mock()
    MemberRepository memberRepository = Mock()

    def setup() {
        feedService = new FeedService(feedRepository, memberRepository)
    }
    def cleanup() {
        feedRepository.deleteAll()
    }
    def createFeed(Long feedId, String content = "test") {
        return new Feed(feedId: feedId, feedContent: content)
    }
    def createFeeds(int size){
        List<Feed> feeds = []
        (1..size).each{index ->
            feeds << new Feed(feedId: index, feedContent: "Feed test $index")
        }
        return feeds
    }

    def "getFeed - 성공시 내용 조회 확인"() {
        given:
        Long feedId = 1L

        when:
        FeedResponse feedResponse = feedService.getFeed(feedId)

        then:
        feedResponse.getContent() == expected
        feedResponse.getFeedId() == feedId

        and:
        1 * feedRepository.findById(_) >> Optional.of(createFeed(feedId, content))

        where:
        content || expected
        "test"  || "test"
    }

    def "getFeeds - 성공시 피드 리스트 조회 확인"(){
        given:
        int size = 5
        List<Feed> expectedFeeds = createFeeds(size)

        when:
        List<Feed> result = feedService.getFeeds(size)

        then:
        result == expectedFeeds

        and:
        1*feedRepository.getFeeds(size) >> expectedFeeds
    }

}
