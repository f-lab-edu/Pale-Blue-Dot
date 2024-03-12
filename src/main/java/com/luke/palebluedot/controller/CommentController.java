package com.luke.palebluedot.controller;

import com.luke.palebluedot.request.CommentCreate;
import com.luke.palebluedot.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public void createComment(@RequestBody CommentCreate request, Long feedId, Long memberId){
        commentService.createComment(request, feedId, memberId);
    }

}
