package com.example.crudpractice.repository;

import com.example.crudpractice.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Optional<Wishlist> findByIdAndIsDeletedFalse(Long wishlistId);

    List<Wishlist> findByIsDeletedFalse();
}
