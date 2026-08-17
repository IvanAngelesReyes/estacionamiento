package com.estacionamiento.backend.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estacionamiento.backend.dto.BitacoraDTO;
import com.estacionamiento.backend.dto.EntradaVehiculoDTO;
import com.estacionamiento.backend.dto.VehiculoCreateDTO;
import com.estacionamiento.backend.dto.VehiculoGetDTO;
import com.estacionamiento.backend.dto.VehiculoSalidaDTO;
import com.estacionamiento.backend.entity.Bitacora;
import com.estacionamiento.backend.entity.Vehiculo;
import com.estacionamiento.backend.mapper.BitacoraMapper;
import com.estacionamiento.backend.mapper.VehiculoMapper;
import com.estacionamiento.backend.repository.BitacoraRepository;
import com.estacionamiento.backend.repository.VehiculoRepository;
import com.estacionamiento.backend.service.VehiculoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehiculoServiceImpl implements VehiculoService {
    private final VehiculoRepository vehiculoRepository;
    private final BitacoraRepository bitacoraRepository;
    private final VehiculoMapper vehiculoMapper;
    private final BitacoraMapper bitacoraMapper;

    @Override
    public VehiculoGetDTO crearVehiculoOficial(VehiculoCreateDTO vehiculoCreateDTO) {
        vehiculoCreateDTO.setTipoVehiculoId(1);
        Vehiculo vehiculo = vehiculoMapper.toEntity(vehiculoCreateDTO);
        vehiculo = vehiculoRepository.save(vehiculo);

        return vehiculoMapper.toDTO(vehiculo);
    }

    @Override
    public VehiculoGetDTO crearVehiculoResidente(VehiculoCreateDTO vehiculoCreateDTO) {
        vehiculoCreateDTO.setTipoVehiculoId(2);
        Vehiculo vehiculo = vehiculoMapper.toEntity(vehiculoCreateDTO);
        vehiculo = vehiculoRepository.save(vehiculo);

        return vehiculoMapper.toDTO(vehiculo);
    }

    @Override
    public VehiculoGetDTO crearVehiculoNoResidente(VehiculoCreateDTO vehiculoCreateDTO) {
        vehiculoCreateDTO.setTipoVehiculoId(3);
        Vehiculo vehiculo = vehiculoMapper.toEntity(vehiculoCreateDTO);
        vehiculo = vehiculoRepository.save(vehiculo);

        return vehiculoMapper.toDTO(vehiculo);
    }

    @Override
    @Transactional
    public VehiculoGetDTO registrarEntradaVehiculo(EntradaVehiculoDTO entradaVehiculoDTO) {
        Vehiculo vehiculo = vehiculoRepository.findByPlaca(entradaVehiculoDTO.getPlaca())
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
        if (vehiculo.isEstacionado()) {
            throw new RuntimeException("El vehículo ya está estacionado");
        }

        vehiculo.setEstacionado(true);

        Bitacora bitacora = new Bitacora();
        bitacora.setVehiculo(vehiculo);
        bitacora.setFechaHoraEntrada(java.time.LocalDateTime.now());
        bitacora.setPagoRealizado(false);

        bitacora = bitacoraRepository.save(bitacora);

        vehiculo.getBitacoras().add(bitacora);
        vehiculo = vehiculoRepository.save(vehiculo);

        return vehiculoMapper.toDTO(vehiculo);
    }

    @Override
    @Transactional
    public VehiculoSalidaDTO registrarSalidaVehiculo(EntradaVehiculoDTO entradaVehiculoDTO) {
        Vehiculo vehiculo = vehiculoRepository.findByPlaca(entradaVehiculoDTO.getPlaca())
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
        if (!vehiculo.isEstacionado()) {
            throw new RuntimeException("El vehículo no está estacionado");
        }

        Bitacora bitacora = vehiculo.getBitacoras().stream()
                .filter(b -> b.getFechaHoraSalida() == null)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró una entrada activa para este vehículo"));

        bitacora.setFechaHoraSalida(java.time.LocalDateTime.now());
        bitacora.setTiempoEstacionado(
                java.time.Duration.between(bitacora.getFechaHoraEntrada(), bitacora.getFechaHoraSalida()).toMinutes()
                        / 60.0);
        bitacora.setCostoTotal(bitacora.getTiempoEstacionado() * vehiculo.getTipoVehiculo().getTarifaMinutoDouble());

        boolean pagoRealizado = false;
        if (vehiculo.getTipoVehiculo().getId() == 1) {
            pagoRealizado = true;
        } else if (vehiculo.getTipoVehiculo().getId() == 2) {
            pagoRealizado = false;
        } else if (vehiculo.getTipoVehiculo().getId() == 3) {
            pagoRealizado = true;
        }
        bitacora.setPagoRealizado(pagoRealizado);
        bitacoraRepository.save(bitacora);

        vehiculo.setEstacionado(false);
        vehiculo = vehiculoRepository.save(vehiculo);

        return vehiculoMapper.toSalidaDTO(vehiculo, bitacora);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BitacoraDTO> generarInformePagos() {
        return bitacoraRepository.findAllByVehiculo_TipoVehiculoIdAndStatusId(2, true).stream()
                .map(bitacora -> {
                    BitacoraDTO dto = bitacoraMapper.toDTO(bitacora);
                    
                    dto.setPagoRealizado(bitacora.getPagoRealizado()); 
                    
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void reiniciarMes() {
        List<Bitacora> bitacoras = bitacoraRepository.findAll();
        if (bitacoras.isEmpty()) {
            throw new RuntimeException("No hay bitácoras para reiniciar");
        }
        bitacoras.forEach(b -> {
            b.setStatusId(false);
        });
        bitacoraRepository.saveAll(bitacoras);

        List<Vehiculo> vehiculosEstacionados = vehiculoRepository.findAll().stream()
                .filter(Vehiculo::isEstacionado)
                .collect(Collectors.toList());

        vehiculosEstacionados.forEach(v -> v.setEstacionado(false));

        vehiculoRepository.saveAll(vehiculosEstacionados);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculoGetDTO> listarVehiculos() {
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
        return vehiculos.stream()
                .map(vehiculoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void procesarPago(String placa) {
        List<Bitacora> bitacorasPendientes = bitacoraRepository.findByVehiculoPlacaAndPagoRealizadoFalse(placa);

        if (bitacorasPendientes.isEmpty()) {
            System.out.println("No se encontraron bitácoras pendientes para la placa: " + placa);
        }

        for (Bitacora bitacora : bitacorasPendientes) {
            bitacora.setPagoRealizado(true);
            System.out.println("Marcando como pagada la bitácora del vehículo: " + placa);
        }

        bitacoraRepository.saveAll(bitacorasPendientes);
    }

}
