package com.luke.palebluedot.service;

import com.luke.palebluedot.domain.Member;
import com.luke.palebluedot.repository.MemberRepository;
import com.luke.palebluedot.request.MemberCreate;
import com.luke.palebluedot.request.MemberEdit;
import com.luke.palebluedot.response.MemberResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Transactional
    public void createMember(MemberCreate memberCreate){
        Member member = Member.builder()
                .password(memberCreate.getPassword())
                .memberName(memberCreate.getMemberName())
                .email(memberCreate.getEmail())
                .build();
        memberRepository.save(member);
    }
    @Transactional
    public MemberResponse getMember(String memberName) {
        Member member = memberRepository.findByMemberName(memberName)
                .orElseThrow(()->new IllegalArgumentException("회원정보가 없습니다."));

        return MemberResponse.builder()
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .email(member.getEmail())
                .build();
    }

    @Transactional
    public void editMember(String memberName, MemberEdit memberEdit) {
        Optional<Member> searchMember = memberRepository.findByMemberName(memberName);
        if(searchMember.isPresent()){
            Member changedMember = Member.builder()
                    .memberName(memberEdit.getMemberName())
                    .password(memberEdit.getPassword())
                    .email(memberEdit.getEmail())
                    .build();
            memberRepository.save(changedMember);
        }else{
            throw new  IllegalArgumentException("회원정보가 없습니다.");
        }
    }
    @Transactional
    public void deleteMember(String memberName) {
        memberRepository.deleteByMemberName(memberName);
    }
}
