package com.estacionamiento.backend.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BitacoraDTO {
    private VehiculoGetDTO vehiculo;
    private LocalDateTime fechaHoraEntrada;
    private LocalDateTime fechaHoraSalida;
    private Double tiempoEstacionado;
    private Double costoTotal;
    private Boolean pagoRealizado;
}
