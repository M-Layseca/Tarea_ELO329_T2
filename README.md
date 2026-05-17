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
    * `Cellular.java`, `EloTelTag.java`, `Tablet.java`: Clases que heredan de `Equipo` (Modelo).
    * `CellularView.java`, `EloTelTagView.java`, `TabletView.java`: Clases que representan gráficamente a los equipos (Vista) y manejan las animaciones de radar y sonido.
    * `ETNube.java`: Simula la base de datos o servidor en la nube para el registro de posiciones de los dispositivos detectados.
* **Archivos de Configuración y Recursos:**
    * `pom.xml`: Archivo de configuración de Maven para la gestión de dependencias (JavaFX y javafx-media).
    * `config.txt`: Archivo de texto con los parámetros iniciales de la simulación.
    * `Makefile`: Script para automatizar la compilación, ejecución y generación de Javadoc.
    * `src/main/resources/sonidos/`: Directorio que contiene el archivo de audio para el radar.

## Cómo compilar y ejecutar la tarea

El proyecto está configurado para ejecutarse fácilmente delegando el proceso a Maven, dependiendo de su entorno de trabajo. 

### Opción A: Entornos Linux / Ubuntu
Abra la terminal en el directorio raíz del proyecto y utilice los siguientes comandos proporcionados por el `Makefile`:
* **Para compilar y ejecutar la simulación:**
  ```bash
  make clean
  make
  make run
  ```

### Opción B: Entornos Windows (PowerShell)
Abra su consola PowerShell en la raíz del proyecto y ejecute el Wrapper de Maven:
   ```bash
   .\mvnw.cmd clean compile exec:java '-Dexec.mainClass=Stage4'
   ```
### Opción C: Utilizar IntelliJ
Abra el proyecto en la aplicacion, navegar a la clase `Launcher` y ejecutarla (Run 'Launcher.main()').

## Generación de Documentación (Javadoc)

Este proyecto está configurado para generar automáticamente la documentación técnica del código fuente utilizando Javadoc a través de Maven.

Para generar la documentación, siga estos pasos:
1. Abra una terminal en el directorio del proyecto.
2. Ejecute el comando: `make javadoc` (o alternativamente, ejecute `mvn javadoc:javadoc`).
3. El proceso creará una nueva carpeta llamada `target` (si no existía previamente).
4. Para visualizar la documentación, navegue a la ruta `target/reports/apidocs/` y abra el archivo `index.html` en su navegador web de preferencia.


## Diagrama de clases:

```mermaid

classDiagram
    %% Relación de herencia para Modelos
    Equipo <|-- Cellular
    Equipo <|-- EloTelTag
    Equipo <|-- Tablet

    %% Relación de herencia para Vistas
    EquipoView <|-- CellularView
    EquipoView <|-- EloTelTagView
    EquipoView <|-- TabletView

    %% Relación Modelo/Vista
    Cellular "1" -- "1" CellularView : tiene
    EloTelTag "1" -- "1" EloTelTagView : tiene
    Tablet "1" -- "1" TabletView : tiene
    Territory "1" -- "1" TerritoryView : tiene

    %% Relaciones del Motor (Controlador/Agregación)
    Territory "1" *-- "*" Equipo : administra
    Territory ..> ETNube : envía reportes a
    
    %% Relación de los Menús Emergentes y Ventanas (Etapa 3 y 4)
    CellularView ..> PopupMenu : invoca
    TabletView ..> PopupMenu : invoca
    PopupMenu ..> FindMyMenu : abre
    PopupMenu ..> GFindMyMenu : abre

    class Equipo {
        <<abstract>>
        -String owner
        -double x
        -double y
        -double speed
        -double angle
        +move(double deltaT)
        +getX()
        +getY()
    }

    class Cellular {
        +reportToCloud()
    }

    class EloTelTag {
        -String name
        +scan(Territory t)
    }

    class Tablet {
        +scan(Territory t)
    }

    class EquipoView {
        <<abstract>>
        -Node javafxNode
        +updatePosition(double x, double y)
    }

    class CellularView {
        +onMouseClick()
    }

    class EloTelTagView {
        +drawRadarPulse()
        +playRadarSound()
    }

    class TabletView {
        +drawRadarPulse()
        +playRadarSound()
        +onMouseClick()
    }

    class Territory {
        -List~Equipo~ devices
        -double width
        -double height
        +moveAll(double deltaT)
        +detectarCelularesCercanos()
    }

    class TerritoryView {
        -Image background
        +render()
    }

    class ETNube {
        <<Instancia única>>
        -Map~String, List~Report~~ data
        +receiveReport(String owner, String device, double x, double y)
        +getReportsFor(String owner)
    }
    
    class PopupMenu {
        +showOptions(String owner)
    }

    class FindMyMenu {
        -Stage window
        +showData(String owner)
        +startAutoUpdate(int delay)
    }
    
    class GFindMyMenu {
        <<Mismo fondo de mapa de simulación>>
        -Stage window
        -Image background
        +showGraphicData(String owner)
        +startAutoUpdate(int delay)
    }

```
