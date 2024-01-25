package com.luke.palebluedot.service;

import com.luke.palebluedot.domain.Post;
import com.luke.palebluedot.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class PostServiceTest {
    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;
    @Test
    void getPost() {
        //given
        Long userId = 1L;

        //when
        Post post = postRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("아이디가 없습니다."));
        //then
        Assertions.assertThat(post.getContent()).isEqualTo("test");
    }
}

