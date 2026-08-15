package com.estacionamiento.backend.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "tipo_vehiculos")
@Data
@EqualsAndHashCode(callSuper = true)
public class TipoVehiculo extends BaseEntity {
    
    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "tipoVehiculo")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Vehiculo> vehiculos = new ArrayList<>();

    // Para la relación con Vehículos
    public void addVehiculo(Vehiculo vehiculo) {
        this.vehiculos.add(vehiculo);
        vehiculo.setTipoVehiculo(this);
    }

    public void removeVehiculo(Vehiculo vehiculo) {
        this.vehiculos.remove(vehiculo);
        vehiculo.setTipoVehiculo(null);
    }
}