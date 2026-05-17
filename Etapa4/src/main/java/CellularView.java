import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CellularView extends Group {
    private final Cellular cellular;
    private final Rectangle rect;
    private final Text label;

    public CellularView(Cellular cellular) {
        this.cellular = cellular;
        double width = 12;
        double height = 24;
        rect = new Rectangle(width, height);
        rect.setFill(Color.DODGERBLUE);
        rect.setArcWidth(4);
        rect.setArcHeight(4);
        label = new Text(cellular.getOwnerName());
        configurarPosicion(width, height);
        this.getChildren().addAll(rect, label);
    }

    public CellularView(Cellular cellular, ETNube nube) {
        this(cellular);

        this.setOnMouseClicked(e -> {
            ContextMenu contextMenu = new ContextMenu();
            MenuItem findMyItem = new MenuItem("Find My");

            findMyItem.setOnAction(event -> {
                Stage infoStage = new Stage();
                ListView<String> listView = new ListView<>();

                // Función encapsulada para refrescar los datos de la nube
                Runnable actualizarDatos = () -> {
                    listView.getItems().clear();
                    listView.getItems().add("Bienes de " + cellular.getOwnerName());
                    listView.getItems().addAll(nube.obtenerReportes(cellular.getOwnerName()));
                };

                // Ejecutar la primera vez manualmente
                actualizarDatos.run();

                Scene infoScene = new Scene(listView, 250, 200);
                infoStage.setTitle("Find My f...");
                infoStage.setScene(infoScene);

                // Crear el Timeline para actualizar cada 1 segundo (Etapa 4)
                Timeline actualizador = new Timeline(
                        new KeyFrame(Duration.seconds(1), t -> actualizarDatos.run())
                );
                actualizador.setCycleCount(Timeline.INDEFINITE);
                actualizador.play();

                // Detener el Timeline al cerrar la ventana para liberar recursos
                infoStage.setOnCloseRequest(closeEvent -> actualizador.stop());

                infoStage.show();
            });

            contextMenu.getItems().add(findMyItem);
            contextMenu.show(this, e.getScreenX(), e.getScreenY());
        });
    }

    private void configurarPosicion(double width, double height) {
        rect.xProperty().bind(cellular.xProperty().subtract(width / 2));
        rect.yProperty().bind(cellular.yProperty().subtract(height / 2));
        label.xProperty().bind(cellular.xProperty().add(width / 2 + 4));
        label.yProperty().bind(cellular.yProperty().add(height / 2 + 4));
    }
}