package com.estacionamiento.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
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
    @JsonIgnore
    private Integer tipoVehiculoId;
}
