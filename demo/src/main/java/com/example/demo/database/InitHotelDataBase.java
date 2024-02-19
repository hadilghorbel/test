package com.example.demo.database;

import com.example.demo.model.entities.Hotel;
import com.example.demo.model.enums.Category;
import com.example.demo.model.enums.ReservationType;
import com.example.demo.model.enums.Status;
import com.example.demo.repositories.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class InitHotelDataBase implements CommandLineRunner {
    private final HotelRepository hotelRepository;
    @Override
    public void run(String... args) throws Exception {
        List<Hotel> savedHotels = new ArrayList<>();
        savedHotels.add(
                Hotel.builder()
                        .name("La Badira")
                        .stock(50)
                        .ratings(3)
                        .status(Status.AVAILABLE)
                        .reservationType(ReservationType.TOUS_COMPRIS)
                        .price(BigDecimal.valueOf(65))
                        .category(Category.TOURISTIQUE)
                        .build()
        );
        savedHotels.add(
                Hotel.builder()
                        .name("Four Seasons Hotel Tunis")
                        .stock(50)
                        .ratings(4)
                        .status(Status.AVAILABLE)
                        .reservationType(ReservationType.RESORT_MARINE_SPA)
                        .category(Category.TOURISTIQUE)
                        .price(BigDecimal.valueOf(72))
                        .build()
        );
        savedHotels.add(
                Hotel.builder()
                        .name("Movenpick Sousse")
                        .stock(50)
                        .ratings(5)
                        .status(Status.AVAILABLE)
                        .reservationType(ReservationType.RESORT_MARINE_SPA)
                        .category(Category.TOURISTIQUE)
                        .price(BigDecimal.valueOf(150))
                        .build()

        );
        this.hotelRepository.saveAll(savedHotels);
        log.info("Hotels saved Successfully");
    }
}
