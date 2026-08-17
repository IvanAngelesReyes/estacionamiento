package com.estacionamiento.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.estacionamiento.backend.dto.BitacoraDTO;
import com.estacionamiento.backend.entity.Bitacora;

@Mapper(componentModel = "spring")
public abstract class BitacoraMapper {
    
    @Mapping(target = "pagoRealizado", source = "pagoRealizado")
    public abstract BitacoraDTO toDTO(Bitacora bitacora);

}
