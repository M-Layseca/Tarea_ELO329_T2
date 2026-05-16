# Makefile para la Etapa 1 - Tarea 2 (ELO329)
# Diseñado para compilar y ejecutar el proyecto Maven con JavaFX

MAIN_CLASS = Launcher

all: compile

compile:
	./mvnw clean compile

run:
	./mvnw exec:java -Dexec.mainClass="$(MAIN_CLASS)"

clean:
	./mvnw clean