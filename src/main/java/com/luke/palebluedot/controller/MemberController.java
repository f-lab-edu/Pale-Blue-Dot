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

    @GetMapping("/{memberName}")
    public MemberResponse getMember(@PathVariable String memberName) {
        return memberService.getMember(memberName);
    }


    @PatchMapping("/{memberName}")
    public void editMember(@PathVariable String memberName, @RequestBody @Valid MemberEdit request) {
        memberService.editMember(memberName, request);
    }

    @DeleteMapping("/{memberName}")
    public void deleteMember(@PathVariable String memberName) {
        memberService.deleteMember(memberName);
    }
}
