package com.example.demo.services;

import com.example.demo.model.dto.HotelDto;
import com.example.demo.request.MessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HotelService {

    List<HotelDto> findAll();
    MessageResponse bookHotel(Long id);


}
