package com.example.demo.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    Long id;
    String name;
    String lastName;
    String adr;
}
