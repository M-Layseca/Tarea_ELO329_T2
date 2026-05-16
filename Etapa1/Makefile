# Makefile para la Etapa 1 - Tarea 2 (ELO329)
# Diseñado para compilar y ejecutar el proyecto Maven con JavaFX

MAIN_CLASS = Launcher

all: compile

compile:
	mvn clean compile

run:
	mvn exec:java -Dexec.mainClass="$(MAIN_CLASS)"

clean:
	mvn clean
