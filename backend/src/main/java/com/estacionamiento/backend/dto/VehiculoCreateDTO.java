package com.estacionamiento.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VehiculoCreateDTO {
    @NotBlank(message = "La placa es obligatoria")
    private String placa;
    @NotBlank(message = "La marca es obligatoria")
    private String marca;
    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;
    @NotBlank(message = "El color es obligatorio")
    private String color;
    @NotNull(message = "El tipo de vehículo es obligatorio")
    private Integer tipoVehiculoId;
}
