import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

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
        label = new Text("Tablet "+tablet.getOwnerName());

        rect.xProperty().bind(tablet.xProperty().subtract(width / 2));
        rect.yProperty().bind(tablet.yProperty().subtract(height / 2));

        label.xProperty().bind(tablet.xProperty().add(10));
        label.yProperty().bind(tablet.yProperty().add(10));

        this.getChildren().addAll(rect, label);
    }
}