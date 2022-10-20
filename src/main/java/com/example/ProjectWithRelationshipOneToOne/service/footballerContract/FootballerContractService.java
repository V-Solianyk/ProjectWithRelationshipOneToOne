package com.example.ProjectWithRelationshipOneToOne.service.footballerContract;

import com.example.ProjectWithRelationshipOneToOne.dto.FootballerContractDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FootballerContractService {
    List<FootballerContractDTO> getAll();

    List<FootballerContractDTO> getAllByDuration(Integer duration, Pageable pageable);

    List<FootballerContractDTO> getAllByAutomaticExtension(Boolean automaticExtension, Pageable pageable);

    List<FootballerContractDTO> getAllByAnnualSalary(Integer annualSalary, Pageable pageable);

    FootballerContractDTO get(Long id);

    FootballerContractDTO create(FootballerContractDTO footballerContractDTO);

    FootballerContractDTO update(Long id, FootballerContractDTO footballerContractDTO);

    void delete(Long id);
}
