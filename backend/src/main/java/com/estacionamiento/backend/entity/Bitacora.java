package com.estacionamiento.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "bitacoras")
@Data
@EqualsAndHashCode(callSuper = true)
public class Bitacora extends BaseEntity {
    
    private LocalDateTime fechaHoraEntrada;
    private LocalDateTime fechaHoraSalida;
    private Double tiempoEstacionado;
    private Double costoTotal;
    private Boolean pagoRealizado;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;
    
}