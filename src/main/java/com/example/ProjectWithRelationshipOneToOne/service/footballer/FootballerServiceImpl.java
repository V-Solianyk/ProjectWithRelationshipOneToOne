package com.example.ProjectWithRelationshipOneToOne.service.footballer;

import com.example.ProjectWithRelationshipOneToOne.dto.FootballerDTO;
import com.example.ProjectWithRelationshipOneToOne.entity.Footballer;
import com.example.ProjectWithRelationshipOneToOne.mapper.footballer.FootballerMapper;
import com.example.ProjectWithRelationshipOneToOne.repository.FootballerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FootballerServiceImpl implements FootballerService {
    private final FootballerRepository footballerRepository;
    private final FootballerMapper footballerMapper;

    @Autowired
    public FootballerServiceImpl(FootballerRepository footballerRepository, FootballerMapper footballerMapper) {
        this.footballerRepository = footballerRepository;
        this.footballerMapper = footballerMapper;
    }

    @Override
    public List<FootballerDTO> getAll() {
        return footballerRepository.findAll().stream()
                .map(footballerMapper::footballerToFootballerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerDTO> getAllByAge(Integer age, Pageable pageable) {
        return footballerRepository.findAllByAge(age, pageable).stream()
                .map(footballerMapper::footballerToFootballerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerDTO> getAllByRating(Integer rating, Pageable pageable) {
        return footballerRepository.findAllByRating(rating, pageable).stream()
                .map(footballerMapper::footballerToFootballerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerDTO> getAllByPersonalDataContainsIgnoreCase(String keyword, Pageable pageable) {
        return footballerRepository.findAllByPersonalDataContainsIgnoreCase(keyword, pageable).stream()
                .map(footballerMapper::footballerToFootballerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FootballerDTO get(Long id) {
        Footballer footballer = footballerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The footballer not found for id: " + id));

        return footballerMapper.footballerToFootballerDTO(footballer);
    }

    @Override
    public FootballerDTO create(FootballerDTO footballerDTO) {
        Footballer footballer = footballerMapper.footballerDTOToFootballer(footballerDTO);
        footballerRepository.save(footballer);

        return footballerDTO;

    }

    @Override
    public FootballerDTO update(Long id, FootballerDTO footballerDTO) {
        footballerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The footballer does not exist for id: " + id));

        Footballer footballer = footballerMapper.footballerDTOToFootballer(footballerDTO);
        footballer.setId(id);
        footballerRepository.save(footballer);

        return footballerDTO;
    }

    @Override
    public void delete(Long id) {
        footballerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The footballer does not exist for id: " + id));

        footballerRepository.deleteById(id);
    }
}
