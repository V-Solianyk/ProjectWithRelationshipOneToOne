package com.example.ProjectWithRelationshipOneToOne.contoller.footballerContract;

import com.example.ProjectWithRelationshipOneToOne.controller.FootballerContractController;
import com.example.ProjectWithRelationshipOneToOne.dto.FootballerContractDTO;
import com.example.ProjectWithRelationshipOneToOne.service.footballerContract.FootballerContractService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class FootballerContractControllerTest {
    FootballerContractService footballerContractService = Mockito.mock(FootballerContractService.class);
    FootballerContractController footballerContractController = new FootballerContractController(footballerContractService);

    @Test
    void getAll() {
        Mockito.when(footballerContractService.getAll())
                .thenReturn(List.of(new FootballerContractDTO(), new FootballerContractDTO()));

        ResponseEntity<List<FootballerContractDTO>> response = footballerContractController.getAll();

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void get() {
        Long id = 11L;

        Mockito.when(footballerContractService.get(id)).thenReturn(new FootballerContractDTO());

        ResponseEntity<FootballerContractDTO> response = footballerContractController.get(id);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllByDuration() {
        Integer duration = 5;

        Pageable pageable = PageRequest.of(0, 100);

        FootballerContractDTO footballerContractDTO1 = new FootballerContractDTO();
        footballerContractDTO1.setDuration(duration);

        FootballerContractDTO footballerContractDTO2 = new FootballerContractDTO();
        footballerContractDTO2.setDuration(duration);

        Mockito.when(footballerContractService.getAllByDuration(duration, pageable))
                .thenReturn(List.of(footballerContractDTO1, footballerContractDTO2));

        ResponseEntity<List<FootballerContractDTO>> response = footballerContractController.getAllByDuration(duration,
                pageable);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllByAutomaticExtension() {
        FootballerContractDTO footballerContractDTO1 = new FootballerContractDTO();

        FootballerContractDTO footballerContractDTO2 = new FootballerContractDTO();

        Pageable pageable = PageRequest.of(0, 999);

        Mockito.when(footballerContractService.getAllByAutomaticExtension(false, pageable))
                .thenReturn(List.of(footballerContractDTO1, footballerContractDTO2));

        ResponseEntity<List<FootballerContractDTO>> response = footballerContractController
                .getAllByAutomaticExtension(false, pageable);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllByAnnualSalary() {
        int annualSalary = 999999;

        FootballerContractDTO footballerContractDTO1 = new FootballerContractDTO();
        footballerContractDTO1.setAnnualSalary(annualSalary);

        FootballerContractDTO footballerContractDTO2 = new FootballerContractDTO();
        footballerContractDTO2.setAnnualSalary(annualSalary);

        Pageable pageable = PageRequest.of(0, 999);

        Mockito.when(footballerContractService.getAllByAnnualSalary(annualSalary, pageable))
                .thenReturn(List.of(footballerContractDTO1, footballerContractDTO2));

        ResponseEntity<List<FootballerContractDTO>> response = footballerContractController.
                getAnnualSalary(annualSalary, pageable);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void create() {
        Mockito.when(footballerContractService.create(new FootballerContractDTO()))
                .thenReturn(new FootballerContractDTO());

        ResponseEntity<FootballerContractDTO> response = footballerContractController.create(new FootballerContractDTO());

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void update() {
        Long id = 555L;

        Mockito.when(footballerContractService.update(id, new FootballerContractDTO()))
                .thenReturn(new FootballerContractDTO());

        ResponseEntity<FootballerContractDTO> response = footballerContractController
                .update(id, new FootballerContractDTO());

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void delete() {
        Long id = 333L;

        ResponseEntity<FootballerContractDTO> response = footballerContractController.delete(id);
        Mockito.verify(footballerContractService).delete(id);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }
}


