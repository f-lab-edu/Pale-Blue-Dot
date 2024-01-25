package com.luke.palebluedot.ServiceSpecification

import com.luke.palebluedot.domain.Post
import com.luke.palebluedot.repository.PostRepository
import com.luke.palebluedot.response.PostResponse
import com.luke.palebluedot.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import static org.mockito.BDDMockito.given
import spock.lang.Specification

@SpringBootTest
public class ServiceSpecification extends Specification{

    @Autowired
    PostService postService
    @MockBean(name = "postRepository")
    PostRepository postRepository

    /*def setup(){
        //postRepository.save();
    }

    def cleanup(){
        postRepository.deleteAll();
    }*/

    def "postGetTest"() {
        given:
        long userId = 1
        given(postRepository.findById(userId))
            .willReturn(Post.builder().content("test").build())
        when:
        PostResponse postResponse = postService.getPost(userId)

        then:
        postResponse.getContent() == "test"
    }
}
