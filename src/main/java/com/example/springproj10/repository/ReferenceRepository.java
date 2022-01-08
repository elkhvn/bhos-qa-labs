package com.example.springproj10.repository;

import com.example.springproj10.model.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferenceRepository extends JpaRepository<Reference, Long> {
}