import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class EloTelTagView extends Group {
    private final EloTelTag tag;
    private final Circle circle;
    private final Text label;

    public EloTelTagView(EloTelTag tag) {
        this.tag = tag;
        circle = new Circle(5, Color.ORCHID); // Radio 5, color púrpura
        label = new Text(tag.getName());

        // Vincular posición del círculo al Tag
        circle.centerXProperty().bind(tag.xProperty());
        circle.centerYProperty().bind(tag.yProperty());

        // Etiqueta un poco desplazada
        label.xProperty().bind(tag.xProperty().add(7));
        label.yProperty().bind(tag.yProperty().add(7));

        this.getChildren().addAll(circle, label);
    }

}
