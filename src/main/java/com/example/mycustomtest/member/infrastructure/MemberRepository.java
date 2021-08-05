package com.example.mycustomtest.member.infrastructure;

import com.example.mycustomtest.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
