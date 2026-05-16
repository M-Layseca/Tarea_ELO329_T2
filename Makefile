JAVAFX_LIB = "C:/Users/joaqu/OneDrive/Escritorio/javafx-sdk-21.0.11/lib"
MODULES = javafx.controls,javafx.fxml,javafx.graphics

SRC_DIR = src/main/java
OUT_DIR = out
SOURCES = $(wildcard $(SRC_DIR)/*.java)


all: compile

compile:
	@mkdir -p $(OUT_DIR)
	javac -d $(OUT_DIR) --module-path $(JAVAFX_LIB) --add-modules $(MODULES) $(SOURCES)

run: compile
	java --module-path $(JAVAFX_LIB) --add-modules $(MODULES) -cp $(OUT_DIR) Etapa3

clean:
	rm -rf $(OUT_DIR)