import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import java.io.File;

public class TerritoryView extends ScrollPane {
    private Territory territory;
    private Pane pane;  // to place each piece of equipment
    public static double WIDTH;
    public static double HEIGHT;

    public TerritoryView(Territory territory, String imageName) {
        this.territory = territory;

        File imageFile = new File(imageName);
        Image image = new Image(imageFile.toURI().toString());

        ImageView mapView = new ImageView(image);
        WIDTH = image.getWidth();
        HEIGHT = image.getHeight();

        pane = new Pane();
        StackPane territoryPane = new StackPane();
        territoryPane.getChildren().addAll(mapView, pane);

        this.setContent(territoryPane);
    }
    public void add(Node equipo) {

        pane.getChildren().add(equipo);
    }
}
