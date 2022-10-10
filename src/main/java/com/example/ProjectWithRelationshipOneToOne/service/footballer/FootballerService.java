package com.example.ProjectWithRelationshipOneToOne.service.footballer;

import com.example.ProjectWithRelationshipOneToOne.dto.FootballerDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface FootballerService {
    List<FootballerDTO> getAll();

    List<FootballerDTO> getAllByAge(Integer age, Pageable pageable);

    List<FootballerDTO> getAllByRating(Integer rating, Pageable pageable);

    FootballerDTO get(Long id);

    FootballerDTO create(FootballerDTO footballer);

    FootballerDTO update(Long id, FootballerDTO footballer);

    void delete (Long id);




}
