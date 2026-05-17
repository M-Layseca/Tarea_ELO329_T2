# Makefile para la Etapa 4 - Tarea 2 (ELO329)
# Diseñado para compilar y ejecutar el proyecto Maven con JavaFX

MAIN_CLASS = Stage4

all: compile

compile:
	mvn clean compile

run:
	mvn exec:java -Dexec.mainClass="$(MAIN_CLASS)"

javadoc:
	mvn javadoc:javadoc

clean:
	mvn clean