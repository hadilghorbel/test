package com.example.demo.services;

import com.example.demo.mapper.HotelMapper;
import com.example.demo.model.dto.HotelDto;
import com.example.demo.repositories.HotelRepository;
import com.example.demo.request.MessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public List<HotelDto> findAll() {
        return HotelMapper.INSTANCE.fromEntitiesToDtos(
                this.hotelRepository.findAll()
        );
    }

    @Override
    public MessageResponse bookHotel(Long id) {
        return null;
    }
}
