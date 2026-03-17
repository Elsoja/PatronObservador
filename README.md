# Weather Station — Patrón de Diseño Observer

Implementación del **patrón de diseño Observer** aplicado a una estación meteorológica. Desarrollado para la materia de **Arquitectura de Software** — CETYS Universidad, Semestre 8.

---

## ¿De qué trata el proyecto?

El sistema simula una **estación meteorológica** donde un objeto central (`WeatherData`) almacena mediciones del clima (temperatura, humedad y presión) y **notifica automáticamente** a múltiples elementos de visualización cada vez que los datos cambian.

El objetivo es demostrar el patrón **Observer** en la práctica:
- El `Subject` (observable) no necesita saber *quién* lo observa.
- Los `Observers` reaccionan de forma **independiente** ante el mismo evento.
- Se pueden agregar o quitar displays **sin modificar** el código existente.

---

## Estructura del Proyecto

```
PatronObservador/
│
├── subject.java                  # Interfaz Subject (Observable)
├── observer.java                 # Interfaz Observer
├── displayelement.java           # Interfaz DisplayElement
│
├── weatherdata.java              # Subject principal (estación meteorológica)
│
├── currentconditionsdisplay.java # Display: condiciones actuales
├── statisticsdisplay.java        # Display: estadísticas históricas
├── forecastdisplay.java          # Display: pronóstico del tiempo
├── HeatIndexDisplay.java         # Display: índice de calor
├── ThirdPartyDisplay.java        # Display: panel externo de terceros
│
└── WeatherStation.java           # Clase Main / simulador
```

---

## Interfaces

### `Subject`
Define el contrato del objeto observable. Cualquier clase que quiera ser observada debe implementarla.

| Método | Descripción |
|---|---|
| `registerObserver(Observer o)` | Suscribe un observer para recibir notificaciones |
| `removeObserver(Observer o)` | Desuscribe un observer |
| `notifyObservers()` | Notifica a todos los observers suscritos |

### `Observer`
Define el contrato de los objetos que quieren ser notificados.

| Método | Descripción |
|---|---|
| `update(float temp, float humidity, float pressure)` | Recibe los nuevos datos del Subject cuando cambian |

### `DisplayElement`
Define el contrato para mostrar información en pantalla.

| Método | Descripción |
|---|---|
| `display()` | Imprime la información del display en consola |

---

## Clases Principales

### `WeatherData` — Subject Principal

Es el **núcleo del sistema**. Almacena las mediciones y gestiona la lista de observers.

| Método | Descripción |
|---|---|
| `setMeasurements(float temp, float humidity, float pressure)` | Actualiza los datos y dispara la notificación |
| `registerObserver(Observer o)` | Agrega un observer a la lista interna |
| `removeObserver(Observer o)` | Elimina un observer de la lista |
| `notifyObservers()` | Itera la lista y llama `update()` en cada observer |
| `getTemperature()` | Retorna la temperatura actual |
| `getHumidity()` | Retorna la humedad actual |
| `getPressure()` | Retorna la presión actual |

---

## Displays (Observers)

### `CurrentConditionsDisplay`
Muestra las **condiciones actuales** en el momento exacto de la medición.

- **Datos usados:** temperatura (°F), humedad (%)
- **Salida:** `Current conditions: 80.0F degrees and 65.0% humidity`

---

### `StatisticsDisplay`
Acumula el **historial de temperaturas** y calcula estadísticas desde el inicio del programa.

- **Datos usados:** temperatura (°F)
- **Calcula:** promedio, máximo y mínimo de todas las mediciones recibidas
- **Salida:** `Avg/Max/Min temperature = 81.0/82.0/80.0`

---

### `ForecastDisplay`
Predice el clima comparando la **presión actual vs la anterior**.

- **Datos usados:** presión atmosférica (inHg)
- **Lógica:**
  - Presión **sube** → `"Improving weather on the way!"`
  - Presión **igual** → `"More of the same."`
  - Presión **baja** → `"Watch out for cooler, rainy weather"`
- **Salida:** `Forecast: Improving weather on the way!`

---

### `HeatIndexDisplay`
Calcula el **índice de calor** (sensación térmica real) usando la fórmula de regresión de Rothfusz.

- **Datos usados:** temperatura (°F), humedad (%)
- **Fórmula:** polinomio de 16 términos que combina T y RH
- **Salida:** `Heat index is 82.95535`

> La temperatura debe estar en **Fahrenheit (°F)** para que la fórmula sea correcta.

---

### `ThirdPartyDisplay`
Simula un **panel externo** (app o servicio de terceros) que también se suscribe a los datos.

- **Datos usados:** temperatura, humedad y presión (los 3 valores)
- **Función extra:** `unsubscribe()` — permite desconectarse dinámicamente del Subject
- **Salida:** formato de tarjeta con los 3 valores

---

## Cómo Ejecutar

```bash
# 1. Compilar todos los archivos Java
javac *.java

# 2. Ejecutar el simulador
java WeatherStation
```

### Salida esperada

```
Current conditions: 80.0F degrees and 65.0% humidity
Avg/Max/Min temperature = 80.0/80.0/80.0
Forecast: Watch out for cooler, rainy weather
Heat index is 82.95535

Current conditions: 82.0F degrees and 70.0% humidity
Avg/Max/Min temperature = 81.0/82.0/80.0
Forecast: Watch out for cooler, rainy weather
Heat index is 86.90123

Current conditions: 78.0F degrees and 90.0% humidity
Avg/Max/Min temperature = 80.0/82.0/78.0
Forecast: More of the same.
Heat index is 83.64967
```

---

## Ventajas del Patrón Observer en este Sistema

| Principio | Cómo se aplica |
|---|---|
| **Bajo acoplamiento** | `WeatherData` no conoce las clases concretas de los displays |
| **Open/Closed (OCP)** | Se pueden agregar nuevos displays sin modificar código existente |
| **Single Responsibility** | Cada display tiene una sola responsabilidad de visualización |
| **Segregación de interfaces** | `Observer` y `DisplayElement` son interfaces separadas |

---

## Referencia

Basado en el capítulo 2 de:
> Freeman, E. & Robson, E. (2020). *Head First Design Patterns* (2nd ed.). O'Reilly Media.
