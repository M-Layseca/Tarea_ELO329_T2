# Makefile para la Etapa 3 - Tarea 2 (ELO329)
# Diseñado para compilar y ejecutar el proyecto Maven con JavaFX

MAIN_CLASS = Stage3

all: compile

compile:
	./mvnw clean compile

run:
	./mvnw exec:java -Dexec.mainClass="$(MAIN_CLASS)"

javadoc:
	./mvnw javadoc:javadoc

clean:
	./mvnw clean