package com.example.ProjectWithRelationshipOneToOne.repository;

import com.example.ProjectWithRelationshipOneToOne.entity.FootballerContract;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FootballerContractRepository extends PagingAndSortingRepository<FootballerContract, Long> {
    List<FootballerContract> findByDuration(Integer duration, Pageable pageable);

    List<FootballerContract> findByAutomaticExtension(Boolean automaticExtension, Pageable pageable);
}
