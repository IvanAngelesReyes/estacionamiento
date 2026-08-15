package com.estacionamiento.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacionamiento.backend.entity.TipoVehiculo;

public interface TipoVehiculoRepository extends JpaRepository<TipoVehiculo, Long> {
    TipoVehiculo getReferenceById(Integer id);
}
