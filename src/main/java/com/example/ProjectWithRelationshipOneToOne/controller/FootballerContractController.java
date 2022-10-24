package com.example.ProjectWithRelationshipOneToOne.controller;

import com.example.ProjectWithRelationshipOneToOne.dto.FootballerContractDTO;
import com.example.ProjectWithRelationshipOneToOne.service.footballerContract.FootballerContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/footballerContracts")
public class FootballerContractController {
    private final FootballerContractService footballerContractService;

    @Autowired
    public FootballerContractController(FootballerContractService footballerContractService) {
        this.footballerContractService = footballerContractService;
    }

    @GetMapping
     public ResponseEntity<List<FootballerContractDTO>> getAll() {
        return ResponseEntity.ok(footballerContractService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FootballerContractDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(footballerContractService.get(id));
    }

    @GetMapping("/duration")
    public ResponseEntity<List<FootballerContractDTO>> getAllByDuration(@RequestParam("duration") Integer duration,
                                                                 Pageable pageable) {
        return ResponseEntity.ok(footballerContractService.getAllByDuration(duration, pageable));
    }

    @GetMapping("/automaticExtension")
    public ResponseEntity<List<FootballerContractDTO>> getAllByAutomaticExtension(
            @RequestParam("automaticExtension") Boolean automaticExtension, Pageable pageable) {
        return ResponseEntity.ok(footballerContractService.getAllByAutomaticExtension(automaticExtension, pageable));
    }

    @GetMapping("/annualSalary")
    public ResponseEntity<List<FootballerContractDTO>> getAnnualSalary(@RequestParam("annualSalary") Integer annualSalary,
                                                                Pageable pageable) {
        return ResponseEntity.ok(footballerContractService.getAllByAnnualSalary(annualSalary, pageable));
    }

    @PostMapping
    public ResponseEntity<FootballerContractDTO> create(@RequestBody FootballerContractDTO footballerContractDTO) {
        return new ResponseEntity<FootballerContractDTO>(footballerContractService.create(footballerContractDTO),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FootballerContractDTO> update(@PathVariable Long id,
                                                 @RequestBody FootballerContractDTO footballerContractDTO) {
        return ResponseEntity.ok(footballerContractService.update(id, footballerContractDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FootballerContractDTO> delete(@PathVariable Long id) {
        footballerContractService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
