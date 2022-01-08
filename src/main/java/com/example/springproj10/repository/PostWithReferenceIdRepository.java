package com.example.springproj10.repository;

import com.example.springproj10.model.PostWithReferenceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostWithReferenceIdRepository extends JpaRepository<PostWithReferenceId, Long> {
}