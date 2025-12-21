package com.example.crudpractice.repository;

import com.example.crudpractice.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByIsDeletedFalse();

    Optional<Member> findByIdAndIsDeletedFalse(Long memberId);

}
