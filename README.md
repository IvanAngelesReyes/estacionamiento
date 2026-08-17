# Sistema de Control de Estacionamiento Neology

Sistema Full-Stack contenerizado para el control de accesos, gestión de bitácoras y procesamiento de pagos de un estacionamiento.

## Tecnologías
* **Backend:** Java 21, Spring Boot
* **Frontend:** Angular, Node.js
* **Base de Datos:** PostgreSQL
* **Infraestructura:** Docker y Docker Compose

## Guía de Levantamiento Local

### 1. Clonar el repositorio y cambiar de rama
Ejecuta el siguiente comando para descargar el proyecto:
```bash
git clone https://github.com/IvanAngelesReyes/estacionamiento.git
cd estacionamiento
```

### 2. Configurar variables de entorno (.env)
Crea un archivo llamado `.env` en la raíz del proyecto (al mismo nivel que `docker-compose.yml`) y pega la siguiente configuración exacta:

```env
COMPOSE_PROJECT_NAME=estacionamiento-dev
APP_PORT=8082
DB_PORT=5434
SPRING_PROFILE=dev
FRONT_PORT=5173
```

### 3. Construir y levantar contenedores
Ejecuta este comando para descargar dependencias, compilar y levantar toda la infraestructura:

```bash
docker compose up --build -d
```

### 4. Acceder a la aplicación
Una vez que los contenedores estén corriendo, accede desde tu navegador:

* **Frontend (App Web):** [http://localhost:5173](http://localhost:5173)
* **Backend (API REST):** [http://localhost:8082](http://localhost:8082)

---

## Cómo probar el sistema
1. Ingresa al **Frontend** (`http://localhost:5173`).
2. En la vista **Control de Acceso**, ingresa una placa (ej. `ABC-123`) y registra su **Entrada** seleccionando el tipo de vehículo (Oficial, Residente o No Residente).
3. Registra la **Salida** de ese mismo vehículo para que el sistema calcule el tiempo y costo total.
4. Navega a la pestaña de **Reportes**. Si el vehículo es "Residente", verás la opción de saldar la deuda pendiente.
5. Haz clic en **Pagar**; el estado se actualizará a "Pagado" en la base de datos en tiempo real.

---

## Comandos Útiles (Docker)
Si necesitas administrar los contenedores, utiliza estos comandos en la raíz del proyecto:

* **Detener los servicios (sin perder datos):**
  ```bash
  docker compose stop
  ```
* **Apagar los servicios y destruir la red local:**
  ```bash
  docker compose down
  ```
* **Ver los logs en tiempo real:**
  ```bash
  docker compose logs -f
  ```

---
*Desarrollado por Carlos Iván Angeles Reyes*
