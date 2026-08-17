package com.estacionamiento.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacionamiento.backend.entity.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    Optional<Vehiculo> findByPlaca(String placa);
    List<Vehiculo> findAllByTipoVehiculoId(int tipoVehiculoId);
}
