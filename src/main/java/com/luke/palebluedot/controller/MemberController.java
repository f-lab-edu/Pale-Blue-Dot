package com.luke.palebluedot.controller;

import com.luke.palebluedot.request.MemberCreateRequest;
import com.luke.palebluedot.request.MemberEditRequest;
import com.luke.palebluedot.response.MemberResponse;
import com.luke.palebluedot.service.MemberService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberCreateRequest> createMember(@RequestBody @Valid MemberCreateRequest request) {
        return ResponseEntity.ok(memberService.createMember(request));
    }

    @GetMapping("/{memberId}")
    public MemberResponse getMember(@PathVariable Long memberId) {
        return memberService.getMember(memberId);
    }


    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberEditRequest> editMember(@PathVariable Long memberId, @RequestBody @Valid MemberEditRequest request) {
        return ResponseEntity.ok(memberService.editMember(memberId, request));
    }

    @DeleteMapping("/{memberId}")
    public void deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
    }
}
