package com.luke.palebluedot.ServiceTest

import com.luke.palebluedot.domain.Post
import com.luke.palebluedot.repository.PostRepository
import com.luke.palebluedot.response.PostResponse
import com.luke.palebluedot.service.PostService
import spock.lang.Specification


public class PostServiceTest extends Specification {

    PostService postService
    PostRepository postRepository = Mock()

    def setup() {
        postService = new PostService(postRepository)
    }

    def cleanup() {
        postRepository.deleteAll();
    }

    def "getPost - 성공시 내용 조회 확인"() {
        given:
        Long postId = 1L

        when:
        PostResponse postResponse = postService.getPost(postId)

        then:
        postResponse.getContent() == expected
        postResponse.getPostId() == postId

        and:
        1 * postRepository.findById(_) >> Optional.of(createPost(postId, content))

        where:
        content || expected
        "test"  || "test"
    }

    def createPost(Long postId, String content = "test") {
        return new Post(id: postId, content: content)
    }
}
