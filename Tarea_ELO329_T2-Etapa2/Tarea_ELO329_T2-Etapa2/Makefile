JAVAC = javac
JAVA = java
BIN = bin

SRC_DIR = src/main/java
SRC = $(wildcard $(SRC_DIR)/*.java)

PATH_TO_FX = /usr/share/openjfx/lib
FX_MODULES = --module-path $(PATH_TO_FX) --add-modules javafx.controls,javafx.graphics

all: compile run

compile:
	@echo "Creando el directorio de binarios en la raíz..."
	mkdir -p $(BIN)
	@echo "Compilando los archivos Java desde $(SRC_DIR)..."
	$(JAVAC) $(FX_MODULES) -d $(BIN) $(SRC)

run:
	@echo "Iniciando Simulation desde la raíz del proyecto..."
	$(JAVA) $(FX_MODULES) -cp $(BIN) Launcher

clean:
	@echo "Limpiando archivos compilados..."
	rm -rf $(BIN)