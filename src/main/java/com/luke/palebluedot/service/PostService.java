package com.luke.palebluedot.service;

import com.luke.palebluedot.domain.Post;
import com.luke.palebluedot.domain.PostEditor;
import com.luke.palebluedot.repository.PostRepository;
import com.luke.palebluedot.request.PostCreate;
import com.luke.palebluedot.request.PostEdit;
import com.luke.palebluedot.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate) {
        //postcreate를 entity로 변경 필요
        Post post = Post.builder()
                .content(postCreate.getContent())
                .build();

        postRepository.save(post);

    }

    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("아이디가 없습니다."));

        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .build();
    }

    public List<PostResponse> getList(Pageable pageable) {
        return postRepository.findAll(pageable).stream()
                .map(post -> PostResponse.builder()
                        .id(post.getId())
                        .content(post.getContent())
                        .build())
                .collect(Collectors.toList());
    }

    public void edit(Long id, PostEdit postEdit){
        Post post = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("아이디가 없습니다."));

        //어떻게 setter 없이 수정된 데이터를 넘겨줄 수 있을까?
        //post.setContent(postEdit.getContent());
        //postRepository.save(post);
    }
}
