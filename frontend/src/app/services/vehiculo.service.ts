import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { VehiculoCreateDTO, EntradaVehiculoDTO, VehiculoSalidaDTO, BitacoraDTO } from '../models/vehiculo.model';

interface AppConfig {
  apiUrl: string;
}

@Injectable({
  providedIn: 'root'
})
export class VehiculoService {
  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  registrarOficial(data: VehiculoCreateDTO): Observable<any> {
    return this.http.post(`${this.baseUrl}/vehiculos/oficiales`, data);
  }
  registrarResidente(data: VehiculoCreateDTO): Observable<any> {
    return this.http.post(`${this.baseUrl}/vehiculos/residentes`, data);
  }
  registrarNoResidente(data: VehiculoCreateDTO): Observable<any> {
    return this.http.post(`${this.baseUrl}/vehiculos/no-residentes`, data);
  }
  registrarEntrada(data: EntradaVehiculoDTO): Observable<any> {
    return this.http.post(`${this.baseUrl}/estancias/entrada`, data);
  }
  registrarSalida(data: EntradaVehiculoDTO): Observable<VehiculoSalidaDTO> {
    return this.http.post<VehiculoSalidaDTO>(`${this.baseUrl}/estancias/salida`, data);
  }
  obtenerReporteResidentes(): Observable<BitacoraDTO[]> {
    return this.http.get<BitacoraDTO[]>(`${this.baseUrl}/residentes/pagos`);
  }
  reiniciarMes(): Observable<string> {
    return this.http.post(`${this.baseUrl}/mes/iniciar`, {}, { responseType: 'text' });
  }
  listarVehiculos(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/vehiculos`);
  }
  procesarPago(placa: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/vehiculos/${placa}/pagar`, {}, { responseType: 'text' });
  }
}