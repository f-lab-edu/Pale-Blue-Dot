package com.luke.palebluedot.service;

import com.luke.palebluedot.domain.Member;
import com.luke.palebluedot.repository.MemberRepository;
import com.luke.palebluedot.request.MemberCreate;
import com.luke.palebluedot.request.MemberEdit;
import com.luke.palebluedot.response.MemberResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Transactional
    public Member createMember(MemberCreate memberCreate){
        Member member = Member.toEntity(memberCreate);
        return memberRepository.save(member);
    }
    @Transactional(readOnly = true)
    public MemberResponse getMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new IllegalArgumentException("회원정보가 없습니다."));

        return MemberResponse.builder()
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .email(member.getEmail())
                .build();
    }

    @Transactional
    public Member editMember(Long memberId, MemberEdit memberEdit) {
        Member existMember = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        Member.toEntity(memberEdit);
        return existMember;
    }
    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
