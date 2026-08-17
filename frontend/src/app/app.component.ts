import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule, MatToolbarModule, MatButtonModule],
  template: `
    <mat-toolbar color="primary">
      <span>Estacionamiento Neology</span>
      <span style="flex: 1 1 auto;"></span>
      <button mat-button routerLink="/dashboard">Control de Acceso</button>
      <button mat-button routerLink="/reportes">Reportes</button>
    </mat-toolbar>
    <div style="padding: 20px;">
      <router-outlet></router-outlet>
    </div>
  `
})
export class AppComponent {
  title = 'frontend';
}