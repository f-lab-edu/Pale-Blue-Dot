package com.luke.palebluedot.service;

import com.luke.palebluedot.domain.Member;
import com.luke.palebluedot.repository.MemberRepository;
import com.luke.palebluedot.request.MemberCreateRequest;
import com.luke.palebluedot.request.MemberEditRequest;
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
    public MemberCreateRequest createMember(MemberCreateRequest memberCreateRequest){
        Member member = MemberCreateRequest.toEntity(memberCreateRequest);
        Member savedMember = memberRepository.save(member);
        return MemberCreateRequest.toDTO(savedMember);
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
    public MemberEditRequest editMember(Long memberId, MemberEditRequest memberEditRequest) {
        Member existMember = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        existMember.update(memberEditRequest);
        return MemberEditRequest.toDTO(existMember);
    }
    @Transactional(readOnly=true)
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
