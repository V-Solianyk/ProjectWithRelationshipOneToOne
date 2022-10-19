package com.example.ProjectWithRelationshipOneToOne.footballerMapper;

import com.example.ProjectWithRelationshipOneToOne.dto.FootballerDTO;
import com.example.ProjectWithRelationshipOneToOne.entity.Footballer;
import com.example.ProjectWithRelationshipOneToOne.mapper.footballer.FootballerMapperImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FootballerMapperImplTest {
    FootballerMapperImpl footballerMapper = new FootballerMapperImpl();

    @Test
    void footballerToFootballerDTO() {
        Footballer footballer = new Footballer();
        footballer.setPersonalData("I am married");
        footballer.setRating(88);
        footballer.setAge(27);
        footballer.setSurname("Shevchenko");

        FootballerDTO footballerDTO = footballerMapper.footballerToFootballerDTO(footballer);

        Assertions.assertEquals(footballer.getAge(), footballerDTO.getAge());
        Assertions.assertEquals(footballer.getRating(), footballerDTO.getRating());
        Assertions.assertEquals(footballer.getSurname(), footballerDTO.getSurname());
        Assertions.assertEquals(footballer.getPersonalData(), footballerDTO.getPersonalData());
    }

    @Test
    void footballerDTOToFootballer() {
        FootballerDTO footballerDTO = new FootballerDTO();
        footballerDTO.setAge(20);
        footballerDTO.setRating(85);
        footballerDTO.setPersonalData("I am playing for Barcelona.");
        footballerDTO.setSurname("Pedri");

        Footballer footballer = footballerMapper.footballerDTOToFootballer(footballerDTO);

        Assertions.assertEquals(footballerDTO.getPersonalData(), footballer.getPersonalData());
        Assertions.assertEquals(footballerDTO.getRating(), footballer.getRating());
        Assertions.assertEquals(footballerDTO.getSurname(), footballer.getSurname());
        Assertions.assertEquals(footballerDTO.getAge(), footballer.getAge());
    }
}
