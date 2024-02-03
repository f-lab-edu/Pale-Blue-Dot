package com.luke.palebluedot.repository;

import com.luke.palebluedot.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String memberId);

    void deleteByMemberId(String memberId);
}
