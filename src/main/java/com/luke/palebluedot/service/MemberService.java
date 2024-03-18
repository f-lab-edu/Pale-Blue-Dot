package com.luke.palebluedot.service;

import com.luke.palebluedot.domain.Member;
import com.luke.palebluedot.repository.MemberRepository;
import com.luke.palebluedot.request.MemberCreate;
import com.luke.palebluedot.request.MemberEdit;
import com.luke.palebluedot.response.MemberResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity createMember(MemberCreate memberCreate){
        Member member = Member.builder()
                .password(memberCreate.getPassword())
                .memberName(memberCreate.getMemberName())
                .email(memberCreate.getEmail())
                .build();
        return ResponseEntity.ok(memberRepository.save(member));
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
    public ResponseEntity editMember(Long memberId, MemberEdit memberEdit) {
        Member existMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        existMember.builder()
                    .memberName(memberEdit.getMemberName())
                    .password(memberEdit.getPassword())
                    .email(memberEdit.getEmail())
                    .build();
        return ResponseEntity.ok(memberRepository.save(existMember));
    }
    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
