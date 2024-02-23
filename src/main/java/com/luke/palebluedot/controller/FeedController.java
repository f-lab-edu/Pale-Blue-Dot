package com.luke.palebluedot.controller;

import com.luke.palebluedot.domain.Feed;
import com.luke.palebluedot.request.FeedCreate;
import com.luke.palebluedot.request.FeedEdit;
import com.luke.palebluedot.response.FeedResponse;
import com.luke.palebluedot.service.FeedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/feeds")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @PostMapping
    public void createFeed(@RequestBody @Valid FeedCreate request, @PathVariable String memberName){
        feedService.createFeed(request, memberName);
    }


    @GetMapping("/{feedId}")
    @Operation(summary = "Get Feed", description = "특정 게시글 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = Feed.class))}),
            @ApiResponse(responseCode = "404", description = "해당 ID의 게시글이 존재하지 않습니다."),
    })
    public FeedResponse getFeed(@PathVariable Long feedId){
        return feedService.getFeed(feedId);
    }

    @GetMapping
    public List<Feed> getAllFeeds(@RequestParam int size){
        return feedService.getAllFeeds(size);
    }

    @GetMapping("/myFeeds/{memberName}")
    public List<Feed> getMyFeeds(@RequestParam int size, String memberName){
        return feedService.getMyFeeds(size,memberName);
    }


    @PatchMapping("/{feedId}")
    public void editFeed(@PathVariable Long feedId, @RequestBody @Valid FeedEdit request) {
        feedService.editFeed(feedId, request);
    }

    @DeleteMapping("/{feedId}")
    public void deleteFeed(@PathVariable Long feedId) {
        feedService.deleteFeed(feedId);
    }

}
