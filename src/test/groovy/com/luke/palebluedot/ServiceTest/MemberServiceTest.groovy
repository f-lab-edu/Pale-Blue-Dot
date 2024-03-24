package com.luke.palebluedot.ServiceTest

import com.luke.palebluedot.domain.Member
import com.luke.palebluedot.repository.MemberRepository
import com.luke.palebluedot.request.MemberCreateRequest
import com.luke.palebluedot.request.MemberEditRequest
import com.luke.palebluedot.service.MemberService
import spock.lang.Specification


public class MemberServiceTest extends Specification {

    MemberRepository memberRepository = Mock()
    MemberService memberService = new MemberService(memberRepository)

    def createMember(Long memberId) {
        Member member = new Member(
                memberId: memberId,
                memberName: "testMember",
                password: "testPassword",
                email: "test@example.com"
        )
        return member
    }


    def "createMember - 정상작동확인"(){
        given:
        Long memberId = 1L
        Member joinMember = createMember(memberId)
        MemberCreateRequest reMember = MemberCreateRequest.toDTO(joinMember)

        when:
        memberService.createMember(reMember)

        then:
        1*memberRepository.save(_) >> {args ->
            def param = args[0] as Member
        }
    }


    def "editMember - 정상작동확인"(){
        given:
        Long memberId = 1L
        Member existMember = createMember(memberId)
        memberRepository.findById(memberId) >> Optional.of(existMember)
        memberRepository.save(_ as Member) >> { Member savedMember -> savedMember }

        MemberEditRequest memberEdit = MemberEditRequest.builder()
            .memberName("editname")
            .email("editmail")
            .password("editpassword")
            .build()

        when:
        Member result = memberService.editMember(memberId, memberEdit)

        then:
        result != null
        result.memberName == memberEdit.memberName
    }
}
