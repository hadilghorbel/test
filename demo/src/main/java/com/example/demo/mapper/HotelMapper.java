package com.example.demo.mapper;

import com.example.demo.model.dto.HotelDto;
import com.example.demo.model.entities.Hotel;
import org.hibernate.mapping.Map;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(componentModel = "spring")
public interface HotelMapper {
    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);
    @Mapping(source = "name", target = "name")
    HotelDto fromEntityToDto (Hotel hotel);
    List<HotelDto> fromEntitiesToDtos (List<Hotel> hotels);
}
