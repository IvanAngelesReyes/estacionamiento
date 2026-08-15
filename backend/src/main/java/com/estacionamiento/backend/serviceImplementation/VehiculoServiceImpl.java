package com.estacionamiento.backend.serviceImplementation;

import org.springframework.stereotype.Service;

import com.estacionamiento.backend.dto.VehiculoCreateDTO;
import com.estacionamiento.backend.dto.VehiculoGetDTO;
import com.estacionamiento.backend.entity.Vehiculo;
import com.estacionamiento.backend.mapper.VehiculoMapper;
import com.estacionamiento.backend.repository.VehiculoRepository;
import com.estacionamiento.backend.service.VehiculoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehiculoServiceImpl implements VehiculoService {
    private final VehiculoRepository vehiculoRepository;
    private final VehiculoMapper vehiculoMapper; 

    @Override
    public VehiculoGetDTO crearVehiculoOficial(VehiculoCreateDTO vehiculoCreateDTO) {
        Vehiculo vehiculo = vehiculoMapper.toEntity(vehiculoCreateDTO);
        vehiculo = vehiculoRepository.save(vehiculo);

        return vehiculoMapper.toDTO(vehiculo);
    }
}
