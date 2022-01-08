package com.example.springproj10.repository;

import com.example.springproj10.model.PostWithReferenceLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostWithReferenceLinkRepository extends JpaRepository<PostWithReferenceLink, Long> {
}