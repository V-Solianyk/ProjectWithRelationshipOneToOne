package com.example.ProjectWithRelationshipOneToOne.service.footballerContract;

import com.example.ProjectWithRelationshipOneToOne.entity.FootballerContract;
import com.example.ProjectWithRelationshipOneToOne.mapper.footballerContract.FootballerContractMapper;
import com.example.ProjectWithRelationshipOneToOne.repository.FootballerContractRepository;
import com.example.ProjectWithRelationshipOneToOne.repository.FootballerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class FootballerContractImplTest {
    FootballerContractRepository footballerContractRepository = Mockito.mock(FootballerContractRepository.class);
    FootballerContractMapper footballerContractMapper = Mockito.mock(FootballerContractMapper.class);
    FootballerRepository footballerRepository = Mockito.mock(FootballerRepository.class);
    FootballerContractServiceImpl footballerContractService = new FootballerContractServiceImpl
            (footballerContractRepository, footballerContractMapper, footballerRepository);

    @Test
    void getAll() {
        FootballerContract footballerContract1 = new FootballerContract();

        FootballerContract footballerContract2 = new FootballerContract();

        Mockito.when(footballerContractRepository.findAll())
                .thenReturn(List.of(footballerContract1, footballerContract2));
    }
}
