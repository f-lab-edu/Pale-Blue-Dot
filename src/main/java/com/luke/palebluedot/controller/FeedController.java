package com.luke.palebluedot.controller;

import com.luke.palebluedot.domain.Feed;
import com.luke.palebluedot.request.FeedCreateRequest;
import com.luke.palebluedot.request.FeedEditRequest;
import com.luke.palebluedot.response.FeedResponse;
import com.luke.palebluedot.service.FeedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/feeds")
public class FeedController {

    private final FeedService feedService;

    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }
    @PostMapping
    public ResponseEntity<FeedCreateRequest> createFeed(@RequestBody @Valid FeedCreateRequest request, Long memberId, List<MultipartFile> files) throws IOException {
        return ResponseEntity.ok(feedService.createFeed(request, memberId, files));
    }


    @GetMapping("/{feedId}")
    @Operation(summary = "Get Feed", description = "특정 게시글 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = Feed.class))}),
            @ApiResponse(responseCode = "404", description = "해당 ID의 게시글이 존재하지 않습니다."),
    })
    public ResponseEntity<FeedResponse> getFeed(@PathVariable Long feedId, @RequestParam int size, @RequestBody Long lastCommentId){
        return ResponseEntity.ok(feedService.getFeed(feedId, size, lastCommentId));
    }

    @GetMapping
    public ResponseEntity<List<Feed>> findMoreFeeds(@RequestParam int size, Long lastFeedId){
        return ResponseEntity.ok(feedService.findMoreFeeds(size, lastFeedId));
    }

    @GetMapping("/myFeeds/{memberId}")
    public ResponseEntity<List<Feed>> getMyFeeds(@RequestParam int size, @PathVariable Long memberId){
        return ResponseEntity.ok(feedService.getMyFeeds(size, memberId));
    }


    @PatchMapping("/{feedId}")
    public ResponseEntity<FeedEditRequest> editFeed(@PathVariable Long feedId, @RequestBody @Valid FeedEditRequest request) {
        return ResponseEntity.ok(feedService.editFeed(feedId, request));
    }

    @DeleteMapping("/{feedId}")
    public void deleteFeed(@PathVariable Long feedId) {
        feedService.deleteFeed(feedId);
    }

}
