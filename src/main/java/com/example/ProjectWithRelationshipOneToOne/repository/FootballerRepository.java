package com.example.ProjectWithRelationshipOneToOne.repository;

import com.example.ProjectWithRelationshipOneToOne.entity.Footballer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FootballerRepository extends PagingAndSortingRepository<Footballer, Long> {
    List<Footballer> findAllByAge(Integer age, Pageable pageable);

    List<Footballer> findAllByRating(Integer rating, Pageable pageable);

}
