package com.example.ProjectWithRelationshipOneToOne.service.footballer;

import com.example.ProjectWithRelationshipOneToOne.dto.FootballerDTO;
import com.example.ProjectWithRelationshipOneToOne.entity.Footballer;
import com.example.ProjectWithRelationshipOneToOne.mapper.footballerContract.FootballerMapper;
import com.example.ProjectWithRelationshipOneToOne.repository.FootballerRepository;
import com.example.ProjectWithRelationshipOneToOne.service.footballer.FootballerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
        return StreamSupport.stream(footballerRepository.findAll().spliterator(), false)
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
    public FootballerDTO get(Long id) {
        Footballer footballer = footballerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Footballer not found for id: " + id));

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
                .orElseThrow(() -> new EntityNotFoundException("Footballer not found for id: " + id));

        Footballer footballer = footballerMapper.footballerDTOToFootballer(footballerDTO);
        footballer.setId(id);

        return footballerDTO;
    }

    @Override
    public void delete(Long id) {
        footballerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Footballer not exist for id: " + id));

        footballerRepository.deleteById(id);
    }
}
