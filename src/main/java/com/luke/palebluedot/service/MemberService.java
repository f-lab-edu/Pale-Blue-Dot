package com.luke.palebluedot.service;

import com.luke.palebluedot.domain.Member;
import com.luke.palebluedot.repository.MemberRepository;
import com.luke.palebluedot.request.RequestMemberCreate;
import com.luke.palebluedot.request.RequestMemberEdit;
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
    public RequestMemberCreate createMember(RequestMemberCreate requestMemberCreate){
        Member member = requestMemberCreate.toEntity(requestMemberCreate);
        Member savedMember = memberRepository.save(member);
        return RequestMemberCreate.toDTO(savedMember);
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
    public RequestMemberEdit editMember(Long memberId, RequestMemberEdit requestMemberEdit) {
        Member existMember = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        existMember.update(requestMemberEdit);
        return RequestMemberEdit.toDTO(existMember);
    }
    @Transactional(readOnly=true)
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
