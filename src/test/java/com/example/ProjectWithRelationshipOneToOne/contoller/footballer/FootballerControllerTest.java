package com.example.ProjectWithRelationshipOneToOne.contoller.footballer;

import com.example.ProjectWithRelationshipOneToOne.controller.FootballerController;
import com.example.ProjectWithRelationshipOneToOne.dto.FootballerDTO;
import com.example.ProjectWithRelationshipOneToOne.service.footballer.FootballerService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class FootballerControllerTest {
    FootballerService footballerService = Mockito.mock(FootballerService.class);
    FootballerController footballerController = new FootballerController(footballerService);

    @Test
    void getAll() {
        Mockito.when(footballerService.getAll())
                .thenReturn(List.of(new FootballerDTO(), new FootballerDTO()));

        ResponseEntity<List<FootballerDTO>> response = footballerController.getAll();

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllByAge() {
        PageRequest pageRequest = PageRequest.of(0, 100);
        int age = 33;

        FootballerDTO footballerDTO1 = new FootballerDTO();
        footballerDTO1.setAge(age);

        FootballerDTO footballerDTO2 = new FootballerDTO();
        footballerDTO2.setAge(age);

        Mockito.when(footballerService.getAllByAge(age, pageRequest))
                .thenReturn(List.of(footballerDTO1, footballerDTO2));

        ResponseEntity<List<FootballerDTO>> allByAge = footballerController.getAllByAge(age, pageRequest);

        Assertions.assertNotNull(allByAge.getBody());
        Assertions.assertEquals(2, allByAge.getBody().size());
        Assertions.assertEquals(age, allByAge.getBody().get(0).getAge());
        Assertions.assertEquals(age, allByAge.getBody().get(1).getAge());
        Assertions.assertEquals(HttpStatus.OK, allByAge.getStatusCode());
    }

    @Test
    void getAllByRating() {
        PageRequest pageRequest = PageRequest.of(0, 50);
        int rating = 77;

        FootballerDTO footballerDTO1 = new FootballerDTO();
        footballerDTO1.setRating(rating);

        FootballerDTO footballerDTO2 = new FootballerDTO();
        footballerDTO2.setRating(rating);

        Mockito.when(footballerService.getAllByRating(rating, pageRequest))
                .thenReturn(List.of(footballerDTO1, footballerDTO2));

        ResponseEntity<List<FootballerDTO>> allByRating = footballerController.getAllByRating(rating, pageRequest);

        Assertions.assertNotNull(allByRating.getBody());
        Assertions.assertEquals(2, allByRating.getBody().size());
        Assertions.assertEquals(rating, allByRating.getBody().get(0).getRating());
        Assertions.assertEquals(rating, allByRating.getBody().get(1).getRating());
        Assertions.assertEquals(HttpStatus.OK, allByRating.getStatusCode());
    }

    @Test
    void getAllByPersonalDataLike() {
        Pageable pageable = PageRequest.of(0, 100);

        String keyword = "PsG";

        FootballerDTO footballerDTO1 = new FootballerDTO();
        footballerDTO1.setPersonalData("I am married and playing for PSG");

        FootballerDTO footballerDTO2 = new FootballerDTO();
        footballerDTO2.setPersonalData("I am the best player in the world and I am playing for PSG");

        Mockito.when(footballerService.getAllByPersonalDataContainsIgnoreCase(keyword, pageable))
                .thenReturn(List.of(footballerDTO1, footballerDTO2));

        ResponseEntity<List<FootballerDTO>> allByPersonalDataLike = footballerController
                .getAllByPersonalDataLike(keyword, pageable);

        Assertions.assertNotNull(allByPersonalDataLike.getBody());
        Assertions.assertEquals(2, allByPersonalDataLike.getBody().size());
        Assertions.assertTrue(StringUtils.containsIgnoreCase(allByPersonalDataLike
                .getBody().get(0).getPersonalData(), keyword));
        Assertions.assertTrue(StringUtils.containsIgnoreCase(allByPersonalDataLike
                .getBody().get(1).getPersonalData(), keyword));
        Assertions.assertEquals(HttpStatus.OK, allByPersonalDataLike.getStatusCode());
    }

    @Test
    void get() {
        Long id = 11L;

        FootballerDTO footballerDTO = new FootballerDTO();

        Mockito.when(footballerService.get(id)).thenReturn(footballerDTO);

        ResponseEntity<FootballerDTO> response = footballerController.get(id);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void create() {
        FootballerDTO footballerDTO = new FootballerDTO();

        Mockito.when(footballerService.create(footballerDTO)).thenReturn(footballerDTO);

        ResponseEntity<FootballerDTO> response = footballerController.create(footballerDTO);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void update() {
        Long id = 100L;

        FootballerDTO footballerDTO = new FootballerDTO();

        Mockito.when(footballerService.update(id, footballerDTO)).thenReturn(footballerDTO);

        ResponseEntity<FootballerDTO> response = footballerController.update(id, footballerDTO);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void delete() {
        Long id = 99L;

        ResponseEntity<FootballerDTO> response = footballerController.deleteById(id);

        Mockito.verify(footballerService).delete(id);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}

