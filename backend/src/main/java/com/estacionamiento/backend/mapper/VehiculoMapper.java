package com.estacionamiento.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.estacionamiento.backend.dto.VehiculoCreateDTO;
import com.estacionamiento.backend.dto.VehiculoGetDTO;
import com.estacionamiento.backend.dto.VehiculoSalidaDTO;
import com.estacionamiento.backend.entity.Bitacora;
import com.estacionamiento.backend.entity.TipoVehiculo;
import com.estacionamiento.backend.entity.Vehiculo;
import com.estacionamiento.backend.repository.TipoVehiculoRepository;

@Mapper(componentModel = "spring")
public abstract class VehiculoMapper {

    @Autowired
    protected TipoVehiculoRepository tipoVehiculoRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "statusId", ignore = true)
    @Mapping(target = "auditUser", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "bitacoras", ignore = true) 
    @Mapping(target = "estacionado", constant = "false")
    @Mapping(target = "tipoVehiculo", source = "tipoVehiculoId") 
    public abstract Vehiculo toEntity(VehiculoCreateDTO vehiculoCreateDTO);

    public abstract VehiculoGetDTO toDTO(Vehiculo vehiculo);

    protected TipoVehiculo mapTipoVehiculo(Integer id) {
        if (id == null) {
            return null;
        }

        return tipoVehiculoRepository.getReferenceById(id); 
    }

    @Mapping(target = "placa", source = "vehiculo.placa")
    @Mapping(target = "costoTotal", source = "bitacora.costoTotal")
    public abstract VehiculoSalidaDTO toSalidaDTO(Vehiculo vehiculo, Bitacora bitacora);
}