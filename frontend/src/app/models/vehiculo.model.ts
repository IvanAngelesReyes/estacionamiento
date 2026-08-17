export interface VehiculoCreateDTO {
  placa: string;
  marca: string;
  modelo: string;
  color: string;
}

export interface EntradaVehiculoDTO {
  placa: string;
}

export interface VehiculoSalidaDTO {
  placa: string;
  costoTotal: number;
}

export interface BitacoraDTO {
  vehiculo: any;
  fechaHoraEntrada: string;
  fechaHoraSalida: string;
  tiempoEstacionado: number;
  costoTotal: number;
  pagoRealizado: boolean;
}