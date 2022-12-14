package com.example.ProjectWithRelationshipOneToOne.controller;

import com.example.ProjectWithRelationshipOneToOne.dto.FootballerDTO;
import com.example.ProjectWithRelationshipOneToOne.service.footballer.FootballerService;
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
@RequestMapping("/footballers")
public class FootballerController {
    private final FootballerService footballerService;

    @Autowired
    public FootballerController(FootballerService footballerService) {
        this.footballerService = footballerService;
    }

    @GetMapping
    public ResponseEntity<List<FootballerDTO>> getAll() {
        return ResponseEntity.ok(footballerService.getAll());
    }

    @GetMapping("/age")
    public ResponseEntity<List<FootballerDTO>> getAllByAge(@RequestParam("age") int age, Pageable pageable) {
        return ResponseEntity.ok(footballerService.getAllByAge(age, pageable));
    }

    @GetMapping("/rating")
    public ResponseEntity<List<FootballerDTO>> getAllByRating(@RequestParam("rating") int rating, Pageable pageable) {
        return ResponseEntity.ok(footballerService.getAllByRating(rating, pageable));
    }

    @GetMapping("/keyword")
    public ResponseEntity<List<FootballerDTO>> getAllByPersonalDataLike(@RequestParam("keyword") String keyword,
                                                                Pageable pageable) {
        return ResponseEntity.ok(footballerService.getAllByPersonalDataContainsIgnoreCase(keyword, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FootballerDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(footballerService.get(id));
    }

    @PostMapping
    public ResponseEntity<FootballerDTO> create(@RequestBody FootballerDTO footballerDTO) {
        return new ResponseEntity<FootballerDTO>(footballerService.create(footballerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FootballerDTO> update(@PathVariable Long id, @RequestBody FootballerDTO footballerDTO) {
        return ResponseEntity.ok(footballerService.update(id, footballerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FootballerDTO> deleteById(@PathVariable Long id) {
        footballerService.delete(id);

        return ResponseEntity.noContent().build();
    }


}
