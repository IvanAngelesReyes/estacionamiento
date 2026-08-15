package com.estacionamiento.backend.dto;

import lombok.Data;

@Data
public class VehiculoGetDTO {
    private String placa;
    private String marca;
    private String modelo;
    private String color;
    private boolean estacionado;
    private TipoVehiculoGetDTO tipoVehiculo;
}
