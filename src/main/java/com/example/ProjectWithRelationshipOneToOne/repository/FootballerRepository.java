package com.example.ProjectWithRelationshipOneToOne.repository;

import com.example.ProjectWithRelationshipOneToOne.entity.Footballer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FootballerRepository extends JpaRepository<Footballer, Long> {
    List<Footballer> findAllByAge(Integer age, Pageable pageable);

    List<Footballer> findAllByRating(Integer rating, Pageable pageable);

    List<Footballer> findAllByTextContainsIgnoreCase(String keyword, Pageable pageable);

}
