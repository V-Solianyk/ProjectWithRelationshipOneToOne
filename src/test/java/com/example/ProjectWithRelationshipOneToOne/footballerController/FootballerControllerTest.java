package com.example.ProjectWithRelationshipOneToOne.footballerController;

import com.example.ProjectWithRelationshipOneToOne.controller.FootballerController;
import com.example.ProjectWithRelationshipOneToOne.dto.FootballerDTO;
import com.example.ProjectWithRelationshipOneToOne.service.footballer.FootballerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
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
    void get(){}
}

