package com.estacionamiento.backend.config;

import com.estacionamiento.backend.entity.TipoVehiculo;
import com.estacionamiento.backend.repository.TipoVehiculoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(TipoVehiculoRepository tipoVehiculoRepository) {
        return args -> {
            if (tipoVehiculoRepository.count() == 0) {
                
                TipoVehiculo residente = new TipoVehiculo();
                residente.setNombre("Oficial");
                residente.setDescripcion("Vehículos oficiales no pagan.");

                TipoVehiculo visitante = new TipoVehiculo();
                visitante.setNombre("Residente");
                visitante.setDescripcion("Residentes pagan $0.05/minuto (acumulado).");

                TipoVehiculo proveedor = new TipoVehiculo();
                proveedor.setNombre("No Residente");
                proveedor.setDescripcion("No residentes pagan $0.5/minuto al salir.");

                tipoVehiculoRepository.saveAll(List.of(residente, visitante, proveedor));
                
                System.out.println("::Catálogo de Tipos de Vehículo inicializado correctamente.::");
            }
        };
    }
}