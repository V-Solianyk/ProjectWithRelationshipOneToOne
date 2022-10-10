package com.example.ProjectWithRelationshipOneToOne.service.footballerContract;

import com.example.ProjectWithRelationshipOneToOne.dto.FootballerContractDTO;
import com.example.ProjectWithRelationshipOneToOne.entity.FootballerContract;
import com.example.ProjectWithRelationshipOneToOne.mapper.FootballerContractMapper;
import com.example.ProjectWithRelationshipOneToOne.repository.FootballerContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FootballerContractImpl implements FootballerContractService {
    private final FootballerContractRepository footballerContractRepository;
    private final FootballerContractMapper footballerContractMapper;

    @Autowired
    public FootballerContractImpl(FootballerContractRepository footballerContractRepository,
                                  FootballerContractMapper footballerContractMapper) {
        this.footballerContractRepository = footballerContractRepository;
        this.footballerContractMapper = footballerContractMapper;
    }

    @Override
    public List<FootballerContractDTO> getAll() {
        return StreamSupport.stream(footballerContractRepository.findAll().spliterator(), false)
                .map(footballerContractMapper::footballerContractToFootballerContractDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerContractDTO> getAllByDuration(Integer duration, Pageable pageable) {
        return footballerContractRepository.findByDuration(duration, pageable).stream()
                .map(footballerContractMapper::footballerContractToFootballerContractDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerContractDTO> getAllByAutomaticExtension(Boolean automaticExtension, Pageable pageable) {
        return footballerContractRepository.findByAutomaticExtension(automaticExtension, pageable).stream()
                .map(footballerContractMapper::footballerContractToFootballerContractDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FootballerContractDTO get(Long id) {
        FootballerContract footballerContract = footballerContractRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contract does not exist for this ID."));

        return footballerContractMapper.footballerContractToFootballerContractDTO(footballerContract);
    }

    @Override
    public FootballerContractDTO create(FootballerContractDTO footballerContractDTO) {
        FootballerContract footballerContract = footballerContractMapper
                .footballerContractDTOToFootballerContract(footballerContractDTO);

        footballerContractRepository.save(footballerContract);

        return footballerContractDTO;
    }

    @Override
    public FootballerContractDTO update(Long id, FootballerContractDTO footballerContractDTO) {
        footballerContractRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contract does not found for this ID."));

        FootballerContract footballerContract = footballerContractMapper
                .footballerContractDTOToFootballerContract(footballerContractDTO);

        footballerContract.setId(id);

        return footballerContractDTO;
    }

    @Override
    public void delete(Long id) {
        footballerContractRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contract does not exist for this ID."));

        footballerContractRepository.deleteById(id);

    }
}
