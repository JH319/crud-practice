package com.example.crudpractice.repository;

import com.example.crudpractice.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findByIsDeletedFalse();
    Optional<Memo> findByIdAndIsDeletedFalse(Long id);
}
