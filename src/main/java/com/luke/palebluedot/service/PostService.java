package com.luke.palebluedot.service;

import com.luke.palebluedot.domain.Member;
import com.luke.palebluedot.domain.Post;
import com.luke.palebluedot.repository.PostRepository;
import com.luke.palebluedot.request.PostCreate;
import com.luke.palebluedot.request.PostEdit;
import com.luke.palebluedot.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PostService {

    private PostRepository postRepository;

    public void write(PostCreate postCreate, Long memberId) {
        Member member = Member.builder().memberId(memberId).build();

        Post post = Post.builder()
                .content(postCreate.getContent())
                .member(member)
                .build();
        postRepository.save(post);

    }

    public PostResponse getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("게시글이 없습니다."));

        return PostResponse.builder()
                .postId(post.getPostId())
                .content(post.getContent())
                .build();
    }

    public List<PostResponse> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable).stream()
                .map(post -> PostResponse.builder()
                        .postId(post.getPostId())
                        .content(post.getContent())
                        .build())
                .collect(Collectors.toList());
    }

    public void postEdit(Long postId, PostEdit postEdit){
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("아이디가 없습니다."));

        //어떻게 setter 없이 수정된 데이터를 넘겨줄 수 있을까?
        //post.setContent(postEdit.getContent());
        //postRepository.save(post);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
