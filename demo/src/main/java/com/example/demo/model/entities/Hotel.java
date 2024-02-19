package com.example.demo.model.entities;

import com.example.demo.model.enums.Category;
import com.example.demo.model.enums.ReservationType;
import com.example.demo.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Table(name = "t_hotel")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
