package com.estacionamiento.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.estacionamiento.backend.dto.BitacoraDTO;
import com.estacionamiento.backend.dto.EntradaVehiculoDTO;
import com.estacionamiento.backend.dto.VehiculoCreateDTO;
import com.estacionamiento.backend.dto.VehiculoGetDTO;
import com.estacionamiento.backend.dto.VehiculoSalidaDTO;
import com.estacionamiento.backend.service.VehiculoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/neo")
@RequiredArgsConstructor
public class VehiculoController {
    private final VehiculoService vehiculoService;

    @Operation(summary = "Alta de vehiculos oficiales")
    @ApiResponse(responseCode = "201", description = "Vehiculo oficial dado de alta correctamente")
    @PostMapping("/vehiculos/oficiales")
    public ResponseEntity<VehiculoGetDTO> crearVehiculoOficial(@Valid @RequestBody VehiculoCreateDTO vehiculoCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoService.crearVehiculoOficial(vehiculoCreateDTO));
    }

    @Operation(summary = "Alta de vehiculos residentes")
    @ApiResponse(responseCode = "201", description = "Vehiculo residente dado de alta correctamente")
    @PostMapping("/vehiculos/residentes")
    public ResponseEntity<VehiculoGetDTO> crearVehiculoResidente(@Valid @RequestBody VehiculoCreateDTO vehiculoCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoService.crearVehiculoResidente(vehiculoCreateDTO));
    }

    @Operation(summary = "Alta de vehiculos no residentes")
    @ApiResponse(responseCode = "201", description = "Vehiculo no residente dado de alta correctamente")
    @PostMapping("/vehiculos/no-residentes")
    public ResponseEntity<VehiculoGetDTO> crearVehiculoNoResidente(@Valid @RequestBody VehiculoCreateDTO vehiculoCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoService.crearVehiculoNoResidente(vehiculoCreateDTO));
    }

    @Operation(summary = "Registrar entrada de vehículo")
    @ApiResponse(responseCode = "201", description = "Vehiculo registrado correctamente")
    @PostMapping("/estancias/entrada")
    public ResponseEntity<VehiculoGetDTO> registrarEntradaVehiculo(@Valid @RequestBody EntradaVehiculoDTO entradaVehiculoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoService.registrarEntradaVehiculo(entradaVehiculoDTO));
    }

    @Operation(summary = "Registrar salida de vehículo")
    @ApiResponse(responseCode = "201", description = "Vehiculo registrado correctamente")
    @PostMapping("/estancias/salida")
    public ResponseEntity<VehiculoSalidaDTO> registrarSalidaVehiculo(@Valid @RequestBody EntradaVehiculoDTO entradaVehiculoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoService.registrarSalidaVehiculo(entradaVehiculoDTO));
    }

    @Operation(summary = "Generar informe de pagos")
    @ApiResponse(responseCode = "200", description = "Informe generado correctamente")
    @GetMapping("/residentes/pagos")
    public ResponseEntity<List<BitacoraDTO>> generarInformePagos() {
        return ResponseEntity.ok(vehiculoService.generarInformePagos());
    }

    @Operation(summary = "Reiniciar mes")
    @ApiResponse(responseCode = "200", description = "Mes reiniciado correctamente")
    @PostMapping("/mes/iniciar")
    public ResponseEntity<String> reiniciarMes() {
        vehiculoService.reiniciarMes();
        return ResponseEntity.ok("Mes reiniciado exitosamente. Se han reseteado los tiempos y estancias.");
    }

}
