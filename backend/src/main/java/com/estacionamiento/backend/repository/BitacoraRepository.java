package com.estacionamiento.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacionamiento.backend.entity.Bitacora;

public interface BitacoraRepository extends JpaRepository<Bitacora, Long> {
    List<Bitacora> findAllByVehiculo_TipoVehiculoIdAndStatusId(Integer tipoVehiculoId, Boolean statusId);
    List<Bitacora> findByVehiculoPlacaAndPagoRealizadoFalse(String placa);
}
