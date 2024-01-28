package com.luke.palebluedot.controller;

import com.luke.palebluedot.request.MemberCreate;
import com.luke.palebluedot.request.MemberEdit;
import com.luke.palebluedot.response.MemberResponse;
import com.luke.palebluedot.service.MemberService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping
    public void createMember(@RequestBody @Valid MemberCreate request) {
        memberService.createMember(request);
    }

    @GetMapping("/{memberId}")
    public MemberResponse getMember(@PathVariable Long memberId) {
        return memberService.getMember(memberId);
    }

    @PatchMapping("/{memberId}")
    public void editMember(@PathVariable Long memberId, @RequestBody @Valid MemberEdit request) {
        memberService.editMember(memberId, request);
    }

    @DeleteMapping("/{memberId}")
    public void deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
    }
}
