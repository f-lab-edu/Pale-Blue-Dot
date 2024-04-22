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
import org.springframework.http.MediaType;
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
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public FeedResponse createFeed(@RequestPart FeedCreateRequest request, @RequestPart List<MultipartFile> files) throws IOException {
        return feedService.createFeed(request,files);
    }


    @GetMapping("/{feedId}")
    @Operation(summary = "Get Feed", description = "특정 게시글 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = Feed.class))}),
            @ApiResponse(responseCode = "404", description = "해당 ID의 게시글이 존재하지 않습니다."),
    })
    public FeedResponse getFeed(@PathVariable Long feedId, @RequestParam int size, @RequestParam Long lastCommentId){
        return feedService.getFeed(feedId, size, lastCommentId);
    }

    @GetMapping
    public List<Feed> findMoreFeeds(@RequestParam int size, @RequestParam Long lastFeedId){
        return feedService.findMoreFeeds(size, lastFeedId);
    }

    @GetMapping("/myFeeds/{memberId}")
    public List<Feed> getMyFeeds(@RequestParam int size, @PathVariable Long memberId){
        return feedService.getMyFeeds(size, memberId);
    }


    @PatchMapping("/{feedId}")
    public FeedResponse editFeed(@PathVariable Long feedId, @RequestBody @Valid FeedEditRequest request) {
        return feedService.editFeed(feedId, request);
    }

    @DeleteMapping("/{feedId}")
    public void deleteFeed(@PathVariable Long feedId) {
        feedService.deleteFeed(feedId);
    }

}
