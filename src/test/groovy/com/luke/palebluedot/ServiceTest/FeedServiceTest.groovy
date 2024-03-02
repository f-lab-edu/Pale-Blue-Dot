package com.luke.palebluedot.ServiceTest

import com.luke.palebluedot.domain.Comment
import com.luke.palebluedot.domain.Feed
import com.luke.palebluedot.repository.CommentRepository
import com.luke.palebluedot.repository.FeedRepository
import com.luke.palebluedot.repository.MemberRepository
import com.luke.palebluedot.response.FeedResponse
import com.luke.palebluedot.service.FeedService
import spock.lang.Specification

class FeedServiceTest extends Specification {

    FeedService feedService
    FeedRepository feedRepository = Mock()
    MemberRepository memberRepository = Mock()
    CommentRepository commentRepository = Mock()

    def setup() {
        feedService = new FeedService(feedRepository, memberRepository, commentRepository)
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
    def createComments(int size){
        List<Comment> comments = []
        (1..size).each{index ->
            comments << new Comment(commentId: index, commentContent: "Comment test $index")
        }
        return comments
    }

    def "getFeed - 성공시 내용 조회 확인"() {
        given:
        Long feedId = 1L
        int size = 0
        Feed feed = createFeed(feedId)
        when:
        1*feedRepository.findById(feedId) >> Optional.of(feed)

        FeedResponse feedResponse = feedService.getFeed(feedId, size)

        then:
        feedResponse.getContent() == feed.getFeedContent()

    }

    def "getFeeds - 성공시 피드 리스트 조회 확인"(){
        given:
        int size = 5
        List<Feed> expectedFeeds = createFeeds(size)

        when:
        List<Feed> result = feedService.findMoreFeeds(size)

        then:
        result == expectedFeeds

        and:
        1*feedRepository.findMoreFeeds(size) >> expectedFeeds
    }

}
