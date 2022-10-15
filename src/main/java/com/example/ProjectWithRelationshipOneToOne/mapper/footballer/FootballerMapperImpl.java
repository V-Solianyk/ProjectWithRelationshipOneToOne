package com.example.ProjectWithRelationshipOneToOne.mapper.footballer;

import com.example.ProjectWithRelationshipOneToOne.dto.FootballerDTO;
import com.example.ProjectWithRelationshipOneToOne.entity.Footballer;
import org.springframework.stereotype.Component;

@Component
public class FootballerMapperImpl implements FootballerMapper {
    @Override
    public Footballer footballerDTOToFootballer(FootballerDTO footballerDTO) {
        Footballer footballer = new Footballer();
        footballer.setSurname(footballerDTO.getSurname());
        footballer.setAge(footballerDTO.getAge());
        footballer.setRating(footballerDTO.getRating());
        footballer.setPersonalData(footballerDTO.getPersonalData());

        return footballer;
    }

    @Override
    public FootballerDTO footballerToFootballerDTO(Footballer footballer) {
        FootballerDTO footballerDTO = new FootballerDTO();
        footballerDTO.setSurname(footballer.getSurname());
        footballerDTO.setAge(footballer.getAge());
        footballerDTO.setRating(footballer.getRating());
        footballerDTO.setFootballerContractId(footballer.getFootballerContract().getId());

        return footballerDTO;
    }
}
