package com.example.demo.controllers;

import com.example.demo.model.dto.HotelDto;
import com.example.demo.services.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelRestController {
    private final HotelService hotelService;
    @GetMapping("")
    public ResponseEntity<List<HotelDto>> findAll (){
        return new ResponseEntity<>(
                this.hotelService.findAll(), HttpStatus.OK
        );
    }

}
