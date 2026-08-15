package com.estacionamiento.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacionamiento.backend.entity.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

}
