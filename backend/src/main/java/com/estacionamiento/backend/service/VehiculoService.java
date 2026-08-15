package com.estacionamiento.backend.service;

import com.estacionamiento.backend.dto.VehiculoCreateDTO;
import com.estacionamiento.backend.dto.VehiculoGetDTO;

public interface VehiculoService {
    VehiculoGetDTO crearVehiculoOficial(VehiculoCreateDTO vehiculoCreateDTO);
}
