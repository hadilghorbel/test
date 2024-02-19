package com.example.demo.model.dto;

import com.example.demo.model.enums.Category;
import com.example.demo.model.enums.ReservationType;
import com.example.demo.model.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelDto {

    Long id;
    String name;
    Integer ratings;
    Integer stock;
    String image;
    ReservationType reservationType;
    Status status;
    BigDecimal price;
    Category category;


}
