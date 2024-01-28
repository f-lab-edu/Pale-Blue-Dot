package com.luke.palebluedot.repository;

import com.luke.palebluedot.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
