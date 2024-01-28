package com.luke.palebluedot.controller;

import com.luke.palebluedot.request.PostCreate;
import com.luke.palebluedot.request.PostEdit;
import com.luke.palebluedot.response.PostResponse;
import com.luke.palebluedot.service.PostService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public void createPost(@RequestBody @Valid PostCreate request, @PathVariable Long memberId){
        postService.write(request, memberId);
    }


    @GetMapping("/{postId}")
    public PostResponse getPost(@PathVariable Long postId){
        return postService.getPost(postId);
    }

    @GetMapping
    public List<PostResponse> getPosts(@PageableDefault Pageable pageable){
        return postService.getPosts(pageable);
    }

    @PatchMapping("/{postId}")
    public void editPost(@PathVariable Long postId, @RequestBody @Valid PostEdit request) {
        postService.postEdit(postId, request);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }

}
