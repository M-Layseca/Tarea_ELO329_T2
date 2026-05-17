# Makefile para la Etapa 2 - Tarea 2 (ELO329)
# Diseñado para compilar y ejecutar el proyecto Maven con JavaFX

MAIN_CLASS = Stage2

all: compile

compile:
	mvn clean compile

run:
	mvn exec:java -Dexec.mainClass="$(MAIN_CLASS)"

javadoc:
	mvn javadoc:javadoc

clean:
	mvn clean
