package com.example.ProjectWithRelationshipOneToOne.service.footballerContract;

import com.example.ProjectWithRelationshipOneToOne.dto.FootballerContractDTO;
import com.example.ProjectWithRelationshipOneToOne.entity.Footballer;
import com.example.ProjectWithRelationshipOneToOne.entity.FootballerContract;
import com.example.ProjectWithRelationshipOneToOne.mapper.footballerContract.FootballerContractMapper;
import com.example.ProjectWithRelationshipOneToOne.repository.FootballerContractRepository;
import com.example.ProjectWithRelationshipOneToOne.repository.FootballerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FootballerContractServiceImpl implements FootballerContractService {
    private final FootballerContractRepository footballerContractRepository;
    private final FootballerContractMapper footballerContractMapper;
    private final FootballerRepository footballerRepository;

    @Autowired
    public FootballerContractServiceImpl(FootballerContractRepository footballerContractRepository,
                                         FootballerContractMapper footballerContractMapper,
                                         FootballerRepository footballerRepository) {
        this.footballerContractRepository = footballerContractRepository;
        this.footballerContractMapper = footballerContractMapper;
        this.footballerRepository = footballerRepository;
    }

    @Override
    public List<FootballerContractDTO> getAll() {
        return footballerContractRepository.findAll().stream()
                .map(footballerContractMapper::footballerContractToFootballerContractDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerContractDTO> getAllByDuration(Integer duration, Pageable pageable) {
        return footballerContractRepository.findAllByDuration(duration, pageable).stream()
                .map(footballerContractMapper::footballerContractToFootballerContractDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerContractDTO> getAllByAutomaticExtension(Boolean automaticExtension, Pageable pageable) {
        return footballerContractRepository.findAllByAutomaticExtension(automaticExtension, pageable).stream()
                .map(footballerContractMapper::footballerContractToFootballerContractDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerContractDTO> getAllByAnnualSalary(Integer annualSalary, Pageable pageable) {
        return footballerContractRepository.findAllByAnnualSalary(annualSalary, pageable).stream()
                .map(footballerContractMapper::footballerContractToFootballerContractDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FootballerContractDTO get(Long id) {
        FootballerContract footballerContract = footballerContractRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The contract does not exist for this ID."));

        return footballerContractMapper.footballerContractToFootballerContractDTO(footballerContract);
    }

    @Override
    public FootballerContractDTO create(FootballerContractDTO footballerContractDTO) {
        Footballer footballer = footballerRepository.findById(footballerContractDTO.getFootballerId())
                .orElseThrow(() -> new EntityNotFoundException("The footballer does not exist for this Id"));

        FootballerContract footballerContract = footballerContractMapper
                .footballerContractDTOToFootballerContract(footballerContractDTO);
        footballerContract.setFootballer(footballer);

        footballerContractRepository.save(footballerContract);
        footballer.setFootballerContract(footballerContract);
        footballerRepository.save(footballer);

        return footballerContractDTO;
    }

    @Override
    public FootballerContractDTO update(Long id, FootballerContractDTO footballerContractDTO) {
        footballerContractRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The contract does not found for this ID."));

        FootballerContract footballerContract = footballerContractMapper
                .footballerContractDTOToFootballerContract(footballerContractDTO);

        footballerContract.setId(id);
        footballerContractRepository.save(footballerContract);

        return footballerContractDTO;
    }

    @Override
    public void delete(Long id) {
        footballerContractRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The contract does not exist for this ID."));

        footballerContractRepository.deleteById(id);
    }
}
