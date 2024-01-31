package com.luke.palebluedot.controller;

import com.luke.palebluedot.domain.Post;
import com.luke.palebluedot.request.PostCreate;
import com.luke.palebluedot.request.PostEdit;
import com.luke.palebluedot.response.PostResponse;
import com.luke.palebluedot.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get Post", description = "특정 게시글 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = Post.class))}),
            @ApiResponse(responseCode = "404", description = "해당 ID의 게시글이 존재하지 않습니다."),
    })
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
