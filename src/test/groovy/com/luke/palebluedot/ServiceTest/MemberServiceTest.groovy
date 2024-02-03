package com.luke.palebluedot.ServiceTest

import com.luke.palebluedot.domain.Member
import com.luke.palebluedot.repository.MemberRepository
import com.luke.palebluedot.request.MemberCreate
import com.luke.palebluedot.service.MemberService
import spock.lang.Specification


public class MemberServiceTest extends Specification {

    MemberService memberService
    MemberRepository memberRepository = Mock()

    def setup(){
        memberService = new MemberService(memberRepository)
    }

    def "createMember - 정상작동확인"(){
        given:
        MemberCreate member = new MemberCreate()
        member.setMemberId("testId")
        member.setMemberName("testName")
        member.setPassword("testpwd")
        member.setEmail("test@mail.com")

        when:
        memberService.createMember(member)

        then:
        1*memberRepository.save(_) >> {args ->
            def param = args[0] as Member
        }
    }
}
