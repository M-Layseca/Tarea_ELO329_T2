# Tarea_ELO329_T2

## Integrantes:
- Martin Layseca
- Diego Villanueva
- Joaquin Torres
- Eduardo Canales

# Simulador EloTelTag - Tarea 2

## Archivos de la Tarea (Etapa 4)
* **Código Fuente (`src/main/java/`):**
    * `Launcher.java`: Clase principal para iniciar la aplicación sorteando restricciones de módulos de JavaFX.
    * `Stage4.java`: Configura la ventana principal, el menú y lee el archivo de configuración.
    * `Territory.java` / `TerritoryView.java`: Clases para la lógica y representación gráfica del mapa (Modelo y Vista).
    * `Equipo.java`: Superclase que define las propiedades físicas comunes (x, y, velocidad).
    * `Cellular.java`, `Tag.java`, `Tablet.java`: Clases que heredan de `Equipo` (Modelo).
    * `CellularView.java`, `TagView.java`, `TabletView.java`: Clases que representan gráficamente a los equipos (Vista).
    * `ETnube.java`: Simula la base de datos o servidor en la nube para el registro de posiciones.
* **Archivos de Configuración:**
    * `pom.xml`: Archivo de configuración de Maven para la gestión de dependencias (JavaFX).
    * `config.txt`: Archivo de texto con los parámetros iniciales de la simulación.
    * `Placeres.jpg`: Imagen utilizada como fondo del territorio.

## Cómo compilar la tarea

Al utilizar **Maven**, puedes compilar el proyecto directamente desde la terminal de tu IDE o sistema operativo. Asegúrate de tener instalado Maven y configurada la variable de entorno de Java.

1. Abre la terminal en la raíz del proyecto (donde se encuentra el archivo `pom.xml`).
2. Ejecuta el siguiente comando para limpiar compilaciones previas y compilar el código actual:
   ```bash
   mvn clean compile


## Cómo ejecutar la tarea
1. Asegúrese de que el archivo de imagen (ej. `Placeres.jpg`) esté ubicado en la raíz del proyecto.
2. Desde su IDE (ej. IntelliJ), navegue hasta el archivo `Launcher.java`.
3. Ejecute la clase `Launcher` (Run 'Launcher.main()').
4. En el simulador, utilice el menú superior "Simulation" y seleccione un archivo de configuración válido para iniciar la ejecución.
