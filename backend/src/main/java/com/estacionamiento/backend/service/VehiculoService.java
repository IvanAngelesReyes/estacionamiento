package com.estacionamiento.backend.service;

import java.util.List;

import com.estacionamiento.backend.dto.BitacoraDTO;
import com.estacionamiento.backend.dto.EntradaVehiculoDTO;
import com.estacionamiento.backend.dto.VehiculoCreateDTO;
import com.estacionamiento.backend.dto.VehiculoGetDTO;
import com.estacionamiento.backend.dto.VehiculoSalidaDTO;

public interface VehiculoService {
    VehiculoGetDTO crearVehiculoOficial(VehiculoCreateDTO vehiculoCreateDTO);
    VehiculoGetDTO crearVehiculoResidente(VehiculoCreateDTO vehiculoCreateDTO);
    VehiculoGetDTO registrarEntradaVehiculo(EntradaVehiculoDTO entradaVehiculoDTO);
    VehiculoSalidaDTO registrarSalidaVehiculo(EntradaVehiculoDTO entradaVehiculoDTO);
    VehiculoGetDTO crearVehiculoNoResidente(VehiculoCreateDTO vehiculoCreateDTO);  
    List<BitacoraDTO> generarInformePagos(); 
    void reiniciarMes();
    List<VehiculoGetDTO> listarVehiculos();
    void procesarPago(String placa);
}
