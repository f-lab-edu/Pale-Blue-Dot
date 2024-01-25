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
    public void posts(@RequestBody @Valid PostCreate request){
        postService.write(request);
    }


    @GetMapping("/{postId}")
    public PostResponse getPost(@PathVariable Long postId){
        return postService.getPost(postId);
    }

    @GetMapping
    public List<PostResponse> getList(@PageableDefault Pageable pageable){
        return postService.getPosts(pageable);
    }

    @PatchMapping("/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit request) {
        postService.edit(postId, request);
    }

}
