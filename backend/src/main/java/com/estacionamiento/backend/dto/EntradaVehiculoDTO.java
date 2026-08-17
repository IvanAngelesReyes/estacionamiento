package com.estacionamiento.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EntradaVehiculoDTO {
    @NotBlank(message = "La placa es obligatoria")
    private String placa;
}
