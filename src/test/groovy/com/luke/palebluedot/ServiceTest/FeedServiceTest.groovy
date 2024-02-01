package com.luke.palebluedot.ServiceTest

import com.luke.palebluedot.domain.Feed
import com.luke.palebluedot.repository.FeedRepository
import com.luke.palebluedot.response.FeedResponse
import com.luke.palebluedot.service.FeedService
import spock.lang.Specification


class FeedServiceTest extends Specification {

    FeedService postService
    FeedRepository postRepository = Mock()

    def setup() {
        postService = new FeedService(postRepository)
    }

    def cleanup() {
        postRepository.deleteAll()
    }

    def "getPost - 성공시 내용 조회 확인"() {
        given:
        Long postId = 1L

        when:
        FeedResponse postResponse = postService.getFeed(postId)

        then:
        postResponse.getContent() == expected
        postResponse.getFeedId() == postId

        and:
        1 * postRepository.findById(_) >> Optional.of(createPost(postId, content))

        where:
        content || expected
        "test"  || "test"
    }

    def createPost(Long feedId, String content = "test") {
        return new Feed(feedId: feedId, content: content)
    }
}
