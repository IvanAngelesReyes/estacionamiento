package com.estacionamiento.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.estacionamiento.backend.dto.VehiculoCreateDTO;
import com.estacionamiento.backend.dto.VehiculoGetDTO;
import com.estacionamiento.backend.service.VehiculoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/neo/vehiculos")
@RequiredArgsConstructor
public class VehiculoController {
    private final VehiculoService vehiculoService;

    @Operation(summary = "Alta de vehiculos oficiales")
    @ApiResponse(responseCode = "201", description = "Vehiculo oficial dado de alta correctamente")
    @PostMapping("/oficiales")
    public ResponseEntity<VehiculoGetDTO> crearVehiculoOficial(@Valid @RequestBody VehiculoCreateDTO vehiculoCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoService.crearVehiculoOficial(vehiculoCreateDTO));
    }

}
