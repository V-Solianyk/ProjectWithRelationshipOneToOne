package com.example.ProjectWithRelationshipOneToOne.footballerService;

import com.example.ProjectWithRelationshipOneToOne.dto.FootballerDTO;
import com.example.ProjectWithRelationshipOneToOne.entity.Footballer;
import com.example.ProjectWithRelationshipOneToOne.mapper.footballer.FootballerMapper;
import com.example.ProjectWithRelationshipOneToOne.repository.FootballerRepository;
import com.example.ProjectWithRelationshipOneToOne.service.footballer.FootballerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

public class FootballerServiceImplTest {
    FootballerRepository footballerRepository = Mockito.mock(FootballerRepository.class);
    FootballerMapper footballerMapper = Mockito.mock(FootballerMapper.class);
    FootballerServiceImpl footballerService = new FootballerServiceImpl(footballerRepository, footballerMapper);

    @Test
    void getAll() {
        Footballer footballer1 = new Footballer();
        Footballer footballer2 = new Footballer();

        Mockito.when(footballerRepository.findAll())
                .thenReturn(Arrays.asList(footballer1, footballer2));

        Mockito.when(footballerMapper.footballerToFootballerDTO(Mockito.any(Footballer.class)))
                .thenReturn(new FootballerDTO());

        List<FootballerDTO> result = footballerService.getAll();

        Assertions.assertEquals(2, result.size());
        Assertions.assertNotNull(result.get(0));
        Assertions.assertNotNull(result.get(1));
    }

    @Test
    void getAllByAge() {
        Footballer footballer1 = new Footballer();
        Footballer footballer2 = new Footballer();

        Pageable pageable = PageRequest.of(0, 777);

        FootballerDTO footballerDTO = new FootballerDTO();
        footballerDTO.setAge(33);

        Mockito.when(footballerRepository.findAllByAge(33, pageable))
                .thenReturn(Arrays.asList(footballer1, footballer2));

        Mockito.when(footballerMapper.footballerToFootballerDTO(Mockito.any(Footballer.class)))
                .thenReturn(footballerDTO);

        List<FootballerDTO> allByAge = footballerService.getAllByAge(33, pageable);

        Assertions.assertEquals(2, allByAge.size());
        Assertions.assertEquals(33, allByAge.get(0).getAge());
        Assertions.assertEquals(33, allByAge.get(1).getAge());
    }

    @Test
    void getAllBeRating() {
        Footballer footballer1 = new Footballer();
        Footballer footballer2 = new Footballer();

        Pageable pageable = PageRequest.of(0, 99);

        FootballerDTO footballerDTO = new FootballerDTO();
        footballerDTO.setRating(90);

        Mockito.when(footballerRepository.findAllByRating(90, pageable))
                .thenReturn(Arrays.asList(footballer1, footballer2));

        Mockito.when(footballerMapper.footballerToFootballerDTO(Mockito.any(Footballer.class)))
                .thenReturn(footballerDTO);

        List<FootballerDTO> allByRating = footballerService.getAllByRating(90, pageable);

        Assertions.assertEquals(2, allByRating.size());
        Assertions.assertEquals(90, allByRating.get(0).getRating());
        Assertions.assertEquals(90, allByRating.get(1).getRating());
    }

}
