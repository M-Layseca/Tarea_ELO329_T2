import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class EloTelTagView extends Group {
    private final EloTelTag tag;
    private final Circle circle;
    private final Text label;

    // Constructor para Etapa 1 y 2
    public EloTelTagView(EloTelTag tag) {
        this.tag = tag;
        circle = new Circle(5, Color.ORCHID);
        label = new Text(tag.getName());
        circle.centerXProperty().bind(tag.xProperty());
        circle.centerYProperty().bind(tag.yProperty());
        label.xProperty().bind(tag.xProperty().add(7));
        label.yProperty().bind(tag.yProperty().add(7));
        this.getChildren().addAll(circle, label);
    }

    public EloTelTagView(EloTelTag tag, ETNube nube, Territory territory) {
        this(tag);

        Circle radar = new Circle(0);
        radar.setStroke(Color.ORCHID);
        radar.setFill(Color.TRANSPARENT);
        radar.centerXProperty().bind(tag.xProperty());
        radar.centerYProperty().bind(tag.yProperty());
        this.getChildren().add(0, radar);

        Timeline radarTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> radar.setRadius(0)),
                new KeyFrame(Duration.seconds(1), e -> {
                    radar.setRadius(50);
                    territory.detectarCelularesCercanos(tag, nube);
                }),
                new KeyFrame(Duration.seconds(4), e -> radar.setRadius(0))
        );
        radarTimeline.setCycleCount(Timeline.INDEFINITE);
        radarTimeline.play();
    }
}