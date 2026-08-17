package com.estacionamiento.backend.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "vehiculos")
@Data
@EqualsAndHashCode(callSuper = true)
public class Vehiculo extends BaseEntity {
    
    private String placa;
    private String marca;
    private String modelo;
    private String color;
    private boolean estacionado;

    @ManyToOne
    @JoinColumn(name = "tipo_vehiculo_id")
    private TipoVehiculo tipoVehiculo;

    @OneToMany(mappedBy = "vehiculo")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Bitacora> bitacoras = new ArrayList<>();

    // Para la relación con Bitácoras
    public void addBitacora(Bitacora bitacora) {
        this.bitacoras.add(bitacora);
        bitacora.setVehiculo(this);
    }

    public void removeBitacora(Bitacora bitacora) {
        this.bitacoras.remove(bitacora);
        bitacora.setVehiculo(null);
    }
}