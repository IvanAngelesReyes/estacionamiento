import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { VehiculoService } from '../../services/vehiculo.service';
import { BitacoraDTO } from '../../models/vehiculo.model';

@Component({
  selector: 'app-reporte',
  standalone: true,
  imports: [
    CommonModule, 
    MatTableModule, 
    MatPaginatorModule, 
    MatFormFieldModule, 
    MatInputModule, 
    MatButtonModule,
    MatSnackBarModule
  ],
  templateUrl: './reporte.component.html',
  styles: [`
    .reporte-container { padding: 20px; }
    mat-form-field { width: 100%; margin-bottom: 20px; }
    .badge-pagado { 
      color: #155724; 
      background-color: #d4edda; 
      padding: 5px 10px; 
      border-radius: 20px; 
      font-weight: 500; 
      font-size: 12px;
      display: inline-block;
    }
  `]
})
export class ReporteComponent implements OnInit {
  displayedColumns: string[] = ['placa', 'tiempoEstacionado', 'costoTotal', 'acciones'];
  
  // 1. Inicializamos el dataSource vacío desde el principio
  dataSource = new MatTableDataSource<BitacoraDTO>([]);

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private vehiculoService: VehiculoService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.cargarReporte();
  }

  cargarReporte() {
    this.vehiculoService.obtenerReporteResidentes().subscribe((data) => {
      this.dataSource.data = data;
      
      if (!this.dataSource.paginator) {
        this.dataSource.paginator = this.paginator;
      }
      
      this.dataSource.filterPredicate = (data: BitacoraDTO, filter: string) => {
        return data.vehiculo.placa.toLowerCase().includes(filter);
      };
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  pagarRegistro(element: BitacoraDTO) {
    this.vehiculoService.procesarPago(element.vehiculo.placa).subscribe({
      next: () => {
        this.snackBar.open('¡Pago guardado exitosamente en BD!', 'Cerrar', { duration: 3000 });
        
        element.pagoRealizado = true;

        this.dataSource.data = [...this.dataSource.data]; 
      },
      error: (e) => {
        console.error('Error al realizar el pago:', e);
        this.snackBar.open('Error de conexión al procesar el pago', 'Cerrar', { duration: 3000 });
      }
    });
  }
}