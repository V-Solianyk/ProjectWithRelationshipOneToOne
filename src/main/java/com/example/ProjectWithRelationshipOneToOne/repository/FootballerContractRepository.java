package com.example.ProjectWithRelationshipOneToOne.repository;

import com.example.ProjectWithRelationshipOneToOne.entity.FootballerContract;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FootballerContractRepository extends JpaRepository<FootballerContract, Long> {
    List<FootballerContract> findAllByDuration(Integer duration, Pageable pageable);

    List<FootballerContract> findAllByAutomaticExtension(Boolean automaticExtension, Pageable pageable);

    List<FootballerContract> findAllByAnnualSalary(Integer annualSalary, Pageable pageable);
}
