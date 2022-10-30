package com.example.ProjectWithRelationshipOneToOne.service.footballerContract;

import com.example.ProjectWithRelationshipOneToOne.dto.FootballerContractDTO;
import com.example.ProjectWithRelationshipOneToOne.entity.Footballer;
import com.example.ProjectWithRelationshipOneToOne.entity.FootballerContract;
import com.example.ProjectWithRelationshipOneToOne.mapper.footballerContract.FootballerContractMapper;
import com.example.ProjectWithRelationshipOneToOne.repository.FootballerContractRepository;
import com.example.ProjectWithRelationshipOneToOne.repository.FootballerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public class FootballerContractServiceImplTest {
    FootballerContractRepository footballerContractRepository = Mockito.mock(FootballerContractRepository.class);
    FootballerContractMapper footballerContractMapper = Mockito.mock(FootballerContractMapper.class);
    FootballerRepository footballerRepository = Mockito.mock(FootballerRepository.class);
    FootballerContractServiceImpl footballerContractService = new FootballerContractServiceImpl
            (footballerContractRepository, footballerContractMapper, footballerRepository);

    private Footballer footballer;
    private FootballerContract footballerContract;
    private FootballerContractDTO footballerContractDTO;
    private Pageable pageable;
    private final int annualSalary = 1000000;
    private final int duration = 5;
    private final boolean automaticExtension = true;

    @BeforeEach
    void beforeEach() {
        footballer = new Footballer();
        footballer.setId(10L);

        footballerContract = new FootballerContract();
        footballerContract.setId(footballer.getId());
        footballerContract.setAnnualSalary(annualSalary);
        footballerContract.setDuration(duration);
        footballerContract.setAutomaticExtension(automaticExtension);

        footballerContractDTO = new FootballerContractDTO();
        footballerContractDTO.setFootballerId(footballer.getId());
        footballerContractDTO.setAnnualSalary(annualSalary);
        footballerContractDTO.setDuration(duration);
        footballerContractDTO.setAutomaticExtension(automaticExtension);

        pageable = PageRequest.of(0, 500);
    }

    @Test
    void getAll() {
        Mockito.when(footballerContractRepository.findAll())
                .thenReturn(List.of(footballerContract, footballerContract));

        Mockito.when(footballerContractMapper
                        .footballerContractToFootballerContractDTO(Mockito.any(FootballerContract.class)))
                .thenReturn(footballerContractDTO);

        List<FootballerContractDTO> responseAllContracts = footballerContractService.getAll();

        Assertions.assertEquals(2, responseAllContracts.size());
        Assertions.assertNotNull(responseAllContracts.get(0));
        Assertions.assertNotNull(responseAllContracts.get(1));
    }

    @Test
    void getAllByDuration() {
        Mockito.when(footballerContractRepository.findAllByDuration(duration, pageable))
                .thenReturn(List.of(footballerContract, footballerContract));

        Mockito.when(footballerContractMapper
                        .footballerContractToFootballerContractDTO(Mockito.any(FootballerContract.class)))
                .thenReturn(footballerContractDTO);

        List<FootballerContractDTO> allByDuration = footballerContractService.getAllByDuration(duration, pageable);

        Assertions.assertEquals(2, allByDuration.size());
        Assertions.assertEquals(duration, allByDuration.get(0).getDuration());
        Assertions.assertEquals(duration, allByDuration.get(1).getDuration());
    }

    @Test
    void getAllByAutomaticExtension() {
        Mockito.when(footballerContractRepository.findAllByAutomaticExtension(automaticExtension, pageable))
                .thenReturn(List.of(footballerContract, footballerContract));

        Mockito.when(footballerContractMapper
                        .footballerContractToFootballerContractDTO(Mockito.any(FootballerContract.class)))
                .thenReturn(footballerContractDTO);

        List<FootballerContractDTO> listContractDTO = footballerContractService
                .getAllByAutomaticExtension(automaticExtension, pageable);

        Assertions.assertEquals(2, listContractDTO.size());
        Assertions.assertEquals(automaticExtension, listContractDTO.get(0).getAutomaticExtension());
        Assertions.assertEquals(automaticExtension, listContractDTO.get(1).getAutomaticExtension());
    }

    @Test
    void getAllByAnnualSalary() {
        Mockito.when(footballerContractRepository.findAllByAnnualSalary(annualSalary, pageable))
                .thenReturn(List.of(footballerContract, footballerContract));

        Mockito.when(footballerContractMapper
                        .footballerContractToFootballerContractDTO(Mockito.any(FootballerContract.class)))
                .thenReturn(footballerContractDTO);

        List<FootballerContractDTO> listSalary = footballerContractService.getAllByAnnualSalary(annualSalary, pageable);

        Assertions.assertEquals(2, listSalary.size());
        Assertions.assertEquals(annualSalary, listSalary.get(0).getAnnualSalary());
        Assertions.assertEquals(annualSalary, listSalary.get(1).getAnnualSalary());
    }

    @Test
    void getNotExistId() {
        Long notExistId = 999L;

        String expectedMassage = "Contract does not exist for this ID.";

        Mockito.when(footballerContractRepository.findById(notExistId)).thenReturn(Optional.empty());

        EntityNotFoundException notFoundException = Assertions.assertThrows(EntityNotFoundException.class,
                () -> footballerContractService.get(notExistId));
        Assertions.assertEquals(expectedMassage, notFoundException.getMessage());
    }

    @Test
    void getExistId() {
        Long id = 10L;

        Mockito.when(footballerContractRepository.findById(id)).thenReturn(Optional.of(footballerContract));

        Mockito.when(footballerContractMapper.footballerContractToFootballerContractDTO(footballerContract))
                .thenReturn(footballerContractDTO);

        FootballerContractDTO footballerContractDTOResult = footballerContractService.get(id);

        Assertions.assertEquals(id, footballerContractDTOResult.getFootballerId());
        Assertions.assertEquals(annualSalary, footballerContractDTOResult.getAnnualSalary());
        Assertions.assertEquals(duration, footballerContractDTOResult.getDuration());
        Assertions.assertEquals(automaticExtension, footballerContractDTOResult.getAutomaticExtension());
    }

    @Test
    void createNotExistFootballer() {
        Long notExistId = 888L;

        String massage = "The footballer does not exist for this Id";

        Mockito.when(footballerRepository.findById(notExistId)).thenReturn(Optional.empty());

        EntityNotFoundException notFoundException = Assertions.assertThrows(EntityNotFoundException.class,
                () -> footballerContractService.create(footballerContractDTO));

        Assertions.assertEquals(massage, notFoundException.getMessage());

    }

    @Test
    void createExistFootballer() {
        Long id = 10L;

        Mockito.when(footballerRepository.findById(id)).thenReturn(Optional.of(footballer));

        Mockito.when(footballerContractMapper.footballerContractDTOToFootballerContract(footballerContractDTO))
                .thenReturn(footballerContract);

        Mockito.when(footballerContractRepository.save(Mockito.any(FootballerContract.class)))
                .thenReturn(footballerContract);

        Mockito.when(footballerRepository.save(Mockito.any(Footballer.class)))
                .thenReturn(footballer);

        FootballerContractDTO dtoResult = footballerContractService.create(footballerContractDTO);

        Assertions.assertEquals(id, dtoResult.getFootballerId());
        Assertions.assertEquals(annualSalary, dtoResult.getAnnualSalary());
        Assertions.assertEquals(duration, dtoResult.getDuration());
        Assertions.assertEquals(automaticExtension, dtoResult.getAutomaticExtension());
    }

    @Test
    void updateNotExists() {
        Long notExistId = 777L;

        String expectedMassage = "The contract does not found for this ID.";

        Mockito.when(footballerContractRepository.findById(notExistId)).thenReturn(Optional.empty());

        EntityNotFoundException notFoundException = Assertions.assertThrows(EntityNotFoundException.class,
                () -> footballerContractService.update(notExistId, footballerContractDTO));

        Assertions.assertEquals(expectedMassage, notFoundException.getMessage());
    }

    @Test
    void updateExists() {
        Long id = 10L;

        Mockito.when(footballerContractRepository.findById(id)).thenReturn(Optional.of(footballerContract));

        Mockito.when(footballerContractMapper.footballerContractDTOToFootballerContract(footballerContractDTO))
                .thenReturn(footballerContract);

        Mockito.when(footballerContractRepository.save(Mockito.any(FootballerContract.class)))
                .thenReturn(footballerContract);

        FootballerContractDTO update = footballerContractService.update(id, footballerContractDTO);

        Assertions.assertEquals(id, update.getFootballerId());
        Assertions.assertEquals(annualSalary, update.getAnnualSalary());
        Assertions.assertEquals(duration, update.getDuration());
        Assertions.assertEquals(automaticExtension, update.getAutomaticExtension());
    }

    @Test
    void deleteNotExistId() {
        Long notExistId = 444L;

        String expectedMassage = "The contract does not exist for this ID.";

        Mockito.when(footballerContractRepository.findById(notExistId)).thenReturn(Optional.empty());

        EntityNotFoundException notFoundException = Assertions.assertThrows(EntityNotFoundException.class,
                () -> footballerContractService.delete(notExistId));

        Assertions.assertEquals(expectedMassage, notFoundException.getMessage());
    }

    @Test
    void deleteExistId() {
        Long existId = 100L;

        Mockito.when(footballerContractRepository.findById(existId)).thenReturn(Optional.of(footballerContract));

        footballerContractService.delete(existId);

        Mockito.verify(footballerContractRepository).deleteById(existId);
    }
}
