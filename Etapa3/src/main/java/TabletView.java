import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TabletView extends Group {
    private final Tablet tablet;
    private final Rectangle rect;
    private final Text label;

    public TabletView(Tablet tablet) {
        this.tablet = tablet;
        double width = 12;
        double height = 24;
        rect = new Rectangle(width, height);
        rect.setFill(Color.RED);
        rect.setArcWidth(4);
        rect.setArcHeight(4);
        label = new Text("Tablet " + tablet.getOwnerName());

        rect.xProperty().bind(tablet.xProperty().subtract(width / 2));
        rect.yProperty().bind(tablet.yProperty().subtract(height / 2));
        label.xProperty().bind(tablet.xProperty().add(10));
        label.yProperty().bind(tablet.yProperty().add(10));

        this.getChildren().addAll(rect, label);
    }

    public TabletView(Tablet tablet, ETNube nube, Territory territory) {
        this(tablet);

        Circle radar = new Circle(0);
        radar.setStroke(Color.RED);
        radar.setFill(Color.TRANSPARENT);
        radar.centerXProperty().bind(tablet.xProperty());
        radar.centerYProperty().bind(tablet.yProperty());
        this.getChildren().add(0, radar);

        Timeline radarTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> radar.setRadius(0)),
                new KeyFrame(Duration.seconds(1), e -> {
                    radar.setRadius(50);
                    territory.detectarCelularesCercanos(tablet, nube);
                }),
                new KeyFrame(Duration.seconds(5), e -> radar.setRadius(0))
        );
        radarTimeline.setCycleCount(Timeline.INDEFINITE);
        radarTimeline.play();

        this.setOnMouseClicked(e -> {
            ContextMenu contextMenu = new ContextMenu();
            MenuItem findMyItem = new MenuItem("Find My");

            findMyItem.setOnAction(event -> {
                Stage infoStage = new Stage();
                ListView<String> listView = new ListView<>();

                listView.getItems().add("Bienes de " + tablet.getOwnerName());
                listView.getItems().addAll(nube.obtenerReportes(tablet.getOwnerName()));

                Scene infoScene = new Scene(listView, 250, 200);
                infoStage.setTitle("Find My f...");
                infoStage.setScene(infoScene);
                infoStage.show();
            });

            contextMenu.getItems().add(findMyItem);
            contextMenu.show(this, e.getScreenX(), e.getScreenY());
        });
    }
}