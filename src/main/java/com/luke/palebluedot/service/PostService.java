package com.luke.palebluedot.service;

import com.luke.palebluedot.domain.Post;
import com.luke.palebluedot.repository.PostRepository;
import com.luke.palebluedot.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate) {
        //postcreate를 entity로 변경 필요
        Post post = Post.builder()
                .content(postCreate.getContent())
                .filePath(postCreate.getFilePath())
                .build();

        postRepository.save(post);

    }
}
