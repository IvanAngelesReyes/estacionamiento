# Usa la imagen oficial de OpenJDK 21
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

### PARA DESARROLLO:
# Copiamos los archivos necesarios
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .
COPY src ./src

# Convierte formato Windows->Linux y da permisos durante la construcción:
RUN sed -i -e 's/\r$//' mvnw && \
    chmod +x mvnw && \
    ./mvnw dependency:go-offline

EXPOSE 8080

# COMANDO ESTRATÉGICO MULTIPLATAFORMA:
# Al montar el volumen desde Windows, el host sobrescribe el archivo mvnw con saltos de línea CRLF.
# Aquí volvemos a forzar el formato Linux (LF) y damos permisos justo antes de arrancar.
CMD ["sh", "-c", "sed -i -e 's/\\r$//' mvnw && chmod +x mvnw && ./mvnw spring-boot:run"]

