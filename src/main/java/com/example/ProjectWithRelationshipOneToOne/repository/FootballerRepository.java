package com.example.ProjectWithRelationshipOneToOne.repository;

import com.example.ProjectWithRelationshipOneToOne.entity.Footballer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FootballerRepository extends JpaRepository<Footballer, Long> {
    List<Footballer> findAllByAge(int age, Pageable pageable);

    List<Footballer> findAllByRating(int rating, Pageable pageable);

    List<Footballer> findAllByPersonalDataContainsIgnoreCase(String keyword, Pageable pageable);

}
