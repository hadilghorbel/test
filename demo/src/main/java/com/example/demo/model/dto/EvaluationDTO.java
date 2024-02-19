package com.example.demo.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import com.example.demo.model.entities.Evaluation;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EvaluationDTO {

    Long id;
    int value;
    String competenceLibelle;
    Long noteManager;
    Long projectID;
    Long userID;
    Long competenceID;

    public static EvaluationDTO toDto(Evaluation entity)
    {
        return EvaluationDTO.builder()
                .id(entity.getId())
                .value(entity.getValue())
                .projectID(entity.getProject().getId())
                .userID(entity.getUser().getId())
                .competenceID(entity.getCompetence().getId())
                .build();
    }
}
