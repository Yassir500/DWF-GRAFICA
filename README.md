# DWF-GRAFICA

🚀 Sistema de Monitoreo y Observabilidad - Grupo 04

Este proyecto implementa un stack de observabilidad utilizando **Spring Boot 3**, **Prometheus** y **Grafana** bajo contenedores de Docker. Permite monitorear métricas en tiempo real como el uso de CPU, memoria JVM y peticiones HTTP.

## 📋 Requisitos Previos

Antes de empezar, asegúrate de tener instalado:
* **Java 17** o superior.
* **Maven** (para compilar el proyecto).
* **Docker Desktop** (con Docker Compose activo).


Endpoint de métricas
http://localhost:8080/actuator/prometheus

🐳 Configuración con Docker
🔹 1. docker-compose.yml
version: '3.8'

services:

  prometheus:
    image: prom/prometheus
    container_name: prometheus
    ports:
      - "9090:9090"
    volumes:
      - ./prometheus.yml:/etc/prometheus/prometheus.yml
    extra_hosts:
      - "host.docker.internal:host-gateway"

  grafana:
    image: grafana/grafana
    container_name: grafana
    ports:
      - "3000:3000"
    depends_on:
      - prometheus
      
🔹 2. Archivo prometheus.yml
global:
  scrape_interval: 5s

scrape_configs:
  - job_name: 'spring-boot-app'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['host.docker.internal:8080']
      - 
🔹 3. Levantar contenedores
docker-compose up -d
🔍 Verificación
Prometheus
http://localhost:9090/targets

Estado esperado:

UP
Grafana
http://localhost:3000

Credenciales por defecto:

user: admin
password: admin
📊 Configuración de Grafana
Ir a Settings → Data Sources
Agregar Prometheus
URL:
http://prometheus:9090
Guardar y probar conexión
📈 Métricas utilizadas
🔹 CPU
system_cpu_usage
process_cpu_usage
🔹 Memoria
jvm_memory_used_bytes{area="heap"}
🔹 Requests por segundo
rate(http_server_requests_seconds_count[1m])
🔹 Tiempo de respuesta
http_server_requests_seconds_sum / http_server_requests_seconds_count
🔹 Threads
jvm_threads_live_threads
🔹 Disco
disk_free_bytes
🧠 Tipos de Métricas
Gauge → valores actuales (CPU, memoria)
Counter → acumulativos (usar rate())
🚨 Problemas Comunes
❌ Prometheus no conecta
Verificar host.docker.internal
Revisar puerto 8080 activo
❌ Grafana sin datos
Verificar Data Source
Revisar targets en Prometheus
🎯 Resultados

✔ Monitoreo en tiempo real
✔ Visualización de métricas clave
✔ Integración completa con Docker
✔ Dashboard funcional en Grafana
