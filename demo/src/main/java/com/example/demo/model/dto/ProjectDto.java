package com.example.demo.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import com.example.demo.model.entities.Project;
import com.example.demo.model.enums.PStatus;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectDto {

    Long id;
    String name;
    String description;
    Date startDate;
    Date deadline;
    PStatus status;

    public static ProjectDto toDto(Project entity)
    {
        return ProjectDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .startDate(entity.getStartDate())
                .deadline(entity.getDeadline())
                .status(entity.getStatus())
                .build();
    }

}
