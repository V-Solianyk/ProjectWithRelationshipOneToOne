package com.example.ProjectWithRelationshipOneToOne.footballerService;

import com.example.ProjectWithRelationshipOneToOne.dto.FootballerDTO;
import com.example.ProjectWithRelationshipOneToOne.entity.Footballer;
import com.example.ProjectWithRelationshipOneToOne.mapper.footballer.FootballerMapper;
import com.example.ProjectWithRelationshipOneToOne.repository.FootballerRepository;
import com.example.ProjectWithRelationshipOneToOne.service.footballer.FootballerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FootballerServiceImplTest {
    FootballerRepository footballerRepository = Mockito.mock(FootballerRepository.class);
    FootballerMapper footballerMapper = Mockito.mock(FootballerMapper.class);
    FootballerServiceImpl footballerService = new FootballerServiceImpl(footballerRepository, footballerMapper);

    private Footballer footballer;
    private FootballerDTO footballerDTO;

    @BeforeEach
    void beforeEach() {
        footballer = new Footballer();
        footballer.setAge(26);
        footballer.setSurname("Rooney");
        footballer.setRating(91);
        footballer.setPersonalData("I am the best player in the world");

        footballerDTO = new FootballerDTO();
        footballerDTO.setAge(26);
        footballerDTO.setSurname("Rooney");
        footballerDTO.setRating(91);
        footballerDTO.setPersonalData("I am the best player in the world");
    }

    @Test
    void getAll() {
        Mockito.when(footballerRepository.findAll())
                .thenReturn(Arrays.asList(footballer, footballer));

        Mockito.when(footballerMapper.footballerToFootballerDTO(Mockito.any(Footballer.class)))
                .thenReturn(footballerDTO);

        List<FootballerDTO> result = footballerService.getAll();

        Assertions.assertEquals(2, result.size());
        Assertions.assertNotNull(result.get(0));
        Assertions.assertNotNull(result.get(1));
    }

    @Test
    void getAllByAge() {
        Pageable pageable = PageRequest.of(0, 777);

        Mockito.when(footballerRepository.findAllByAge(26, pageable))
                .thenReturn(Arrays.asList(footballer, footballer));

        Mockito.when(footballerMapper.footballerToFootballerDTO(Mockito.any(Footballer.class)))
                .thenReturn(footballerDTO);

        List<FootballerDTO> allByAge = footballerService.getAllByAge(26, pageable);

        Assertions.assertEquals(2, allByAge.size());
        Assertions.assertEquals(26, allByAge.get(0).getAge());
        Assertions.assertEquals(26, allByAge.get(1).getAge());
    }

    @Test
    void getAllBeRating() {
        Pageable pageable = PageRequest.of(0, 99);

        Mockito.when(footballerRepository.findAllByRating(91, pageable))
                .thenReturn(Arrays.asList(footballer, footballer));

        Mockito.when(footballerMapper.footballerToFootballerDTO(Mockito.any(Footballer.class)))
                .thenReturn(footballerDTO);

        List<FootballerDTO> allByRating = footballerService.getAllByRating(91, pageable);

        Assertions.assertEquals(2, allByRating.size());
        Assertions.assertEquals(91, allByRating.get(0).getRating());
        Assertions.assertEquals(91, allByRating.get(1).getRating());
    }

    @Test
    void getNotExistsId() {
        Long notExistId = 2L;
        String expected = "The footballer not found for id: " + notExistId;

        Mockito.when(footballerRepository.findById(notExistId))
                .thenReturn(Optional.empty());

        EntityNotFoundException notFoundException = Assertions.assertThrows(EntityNotFoundException.class,
                () -> footballerService.get(notExistId));

        Assertions.assertEquals(expected, notFoundException.getMessage());
    }

    @Test
    void getExistsId() {
        Long existId = 10L;

        Mockito.when(footballerRepository.findById(existId))
                .thenReturn(Optional.of(footballer));

        Mockito.when(footballerMapper.footballerToFootballerDTO(footballer))
                .thenReturn(footballerDTO);

        FootballerDTO footballerDTOResult = footballerService.get(existId);

        Assertions.assertEquals(footballerDTOResult.getAge(), footballer.getAge());
        Assertions.assertEquals(footballerDTOResult.getRating(), footballer.getRating());
        Assertions.assertEquals(footballerDTOResult.getPersonalData(), footballer.getPersonalData());
        Assertions.assertEquals(footballerDTOResult.getSurname(), footballer.getSurname());
    }

    @Test
    void create() {
        Mockito.when(footballerMapper.footballerDTOToFootballer(footballerDTO))
                .thenReturn(footballer);

        Mockito.when(footballerRepository.save(Mockito.any(Footballer.class))).thenReturn(footballer);

        FootballerDTO result = footballerService.create(footballerDTO);

        Assertions.assertEquals(result.getAge(), footballer.getAge());
        Assertions.assertEquals(result.getRating(), footballer.getRating());
        Assertions.assertEquals(result.getPersonalData(), footballer.getPersonalData());
        Assertions.assertEquals(result.getSurname(), footballer.getSurname());
    }

    @Test
    void updateNotExistsId() {
        Long notExistId = 1111L;
        String expected = "The footballer not found for id: " + notExistId;

        Mockito.when(footballerRepository.findById(notExistId))
                .thenReturn(Optional.empty());

        EntityNotFoundException notFoundException = Assertions.assertThrows(EntityNotFoundException.class,
                () -> footballerService.update(notExistId, footballerDTO));

        Assertions.assertEquals(expected, notFoundException.getMessage());
    }

    @Test
    void updateExistsId() {
        Long existsId = 10L;

        Mockito.when(footballerRepository.findById(existsId))
                .thenReturn(Optional.of(footballer));

        Mockito.when(footballerMapper.footballerDTOToFootballer(footballerDTO))
                .thenReturn(footballer);

        Mockito.when(footballerRepository.save(Mockito.any(Footballer.class))).thenReturn(footballer);

        FootballerDTO updateResult = footballerService.update(existsId, footballerDTO);

        Assertions.assertEquals(footballerDTO.getSurname(), updateResult.getSurname());
        Assertions.assertEquals(footballerDTO.getPersonalData(), updateResult.getPersonalData());
        Assertions.assertEquals(footballerDTO.getAge(), updateResult.getAge());
        Assertions.assertEquals(footballerDTO.getRating(), updateResult.getRating());
    }

    @Test
    void deleteNotExistsId() {
        Long notExistsId = 99999L;
        String expectedMessage = "The footballer does not exist for id: " + notExistsId;

        Mockito.when(footballerRepository.findById(notExistsId))
                .thenReturn(Optional.empty());

        EntityNotFoundException notFoundException = Assertions.assertThrows(EntityNotFoundException.class,
                () -> footballerService.update(notExistsId, footballerDTO));

        Assertions.assertEquals(expectedMessage, notFoundException.getMessage());
    }

    @Test
    void deleteExistId() {
        Long existId = 22L;

        Mockito.when(footballerRepository.findById(existId))
                .thenReturn(Optional.of(footballer));

        footballerRepository.deleteById(existId);
        Mockito.verify(footballerRepository).deleteById(existId);
    }
}
