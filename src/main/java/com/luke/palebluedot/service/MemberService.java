package com.luke.palebluedot.service;

import com.luke.palebluedot.domain.Member;
import com.luke.palebluedot.repository.MemberRepository;
import com.luke.palebluedot.request.MemberCreate;
import com.luke.palebluedot.request.MemberEdit;
import com.luke.palebluedot.response.MemberResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void createMember(MemberCreate memberCreate){
        Member member = Member.builder()
                .memberId(memberCreate.getMemberId())
                .password(memberCreate.getPassword())
                .memberName(memberCreate.getMemberName())
                .email(memberCreate.getEmail())
                .build();
    }

    public MemberResponse getMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new IllegalArgumentException("회원정보가 없습니다."));

        return MemberResponse.builder()
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .email(member.getEmail())
                .build();
    }


    public void editMember(Long memberId, MemberEdit memberEdit) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new IllegalArgumentException("아이디가 없습니다."));
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
