import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { VehiculoService } from '../../services/vehiculo.service';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule, MatTableDataSource } from '@angular/material/table'; 

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule, ReactiveFormsModule, MatCardModule, MatFormFieldModule, 
    MatInputModule, MatSelectModule, MatButtonModule, MatSnackBarModule, 
    MatTableModule
  ],
  templateUrl: './dashboard.component.html',
  styles: [`
    .container { padding: 20px; }
    mat-form-field { width: 100%; margin-bottom: 15px; }
    mat-card { margin-bottom: 20px; }
  `]
})
export class DashboardComponent implements OnInit {
  registroForm: FormGroup;
  estanciaForm: FormGroup;

  vehiculos = new MatTableDataSource<any>([]);
  displayedColumns: string[] = ['placa', 'marca', 'modelo', 'color'];

  constructor(
    private fb: FormBuilder,
    private vehiculoService: VehiculoService,
    private snackBar: MatSnackBar
  ) {
    this.registroForm = this.fb.group({
      placa: ['', [Validators.required, Validators.pattern(/^[A-Z0-9-]+$/)]],
      marca: ['', Validators.required],
      modelo: ['', Validators.required],
      color: ['', Validators.required],
      tipo: ['OFICIAL', Validators.required]
    });

    this.estanciaForm = this.fb.group({
      placa: ['', [Validators.required, Validators.pattern(/^[A-Z0-9-]+$/)]]
    });
  }

  ngOnInit(): void {
    this.cargarVehiculos();
  }

  registrarVehiculo() {
    if (this.registroForm.invalid) return;
    
    const data = { ...this.registroForm.value };
    const tipo = data.tipo;
    delete data.tipo;

    let request;
    if (tipo === 'OFICIAL') request = this.vehiculoService.registrarOficial(data);
    else if (tipo === 'RESIDENTE') request = this.vehiculoService.registrarResidente(data);
    else request = this.vehiculoService.registrarNoResidente(data);

    request.subscribe({
      next: () => {
        this.mostrarMensaje('Vehículo registrado con éxito');
        this.registroForm.reset({ tipo: 'OFICIAL' }); 
        this.cargarVehiculos();
      },
      error: (e) => this.mostrarMensaje(e.error?.message || 'Error al registrar vehículo')
    });
  }

  registrarEntrada() {
    if (this.estanciaForm.invalid) return;
    this.vehiculoService.registrarEntrada(this.estanciaForm.value).subscribe({
      next: () => {
        this.mostrarMensaje('Entrada registrada');
        this.estanciaForm.reset();
      },
      error: (e) => this.mostrarMensaje(e.error?.message || 'Error en entrada')
    });
  }

  registrarSalida() {
    if (this.estanciaForm.invalid) return;
    this.vehiculoService.registrarSalida(this.estanciaForm.value).subscribe({
      next: (res: any) => {
        this.mostrarMensaje(`Salida registrada. Total a pagar: $${res?.costoTotal || 0}`);
        this.estanciaForm.reset();
      },
      error: (e) => this.mostrarMensaje(e.error?.message || 'Error en salida')
    });
  }

  reiniciarMes() {
    this.vehiculoService.reiniciarMes().subscribe({
      next: () => this.mostrarMensaje('Mes reiniciado correctamente'),
      error: (e) => this.mostrarMensaje(e.error?.message || 'Error al reiniciar mes')
    });
  }

  private mostrarMensaje(msg: string) {
    this.snackBar.open(msg, 'Cerrar', { duration: 4000 });
  }

  cargarVehiculos() {
    this.vehiculoService.listarVehiculos().subscribe({
      next: (data) => {
        this.vehiculos.data = data; 
      },
      error: (e) => console.error('Error al cargar vehículos', e)
    });
  }
}