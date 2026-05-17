import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Representa la vista gráfica de un dispositivo EloTelTag.
 * Se encarga de dibujar el marcador en el mapa y animar el radar
 * de búsqueda emitiendo un sonido.
 */
public class EloTelTagView extends Group {
    private final EloTelTag tag;
    private final Circle circle;
    private final Text label;
    private AudioClip bipSound;

    /**
     * Constructor para inicializar la vista estática del tag (Etapas 1 y 2).
     * @param tag El modelo EloTelTag que contiene los datos de posición y nombre.
     */
    public EloTelTagView(EloTelTag tag) {
        this.tag = tag;
        circle = new Circle(5, Color.ORCHID);
        label = new Text(tag.getName());
        circle.centerXProperty().bind(tag.xProperty());
        circle.centerYProperty().bind(tag.yProperty());
        label.xProperty().bind(tag.xProperty().add(7));
        label.yProperty().bind(tag.yProperty().add(7));
        this.getChildren().addAll(circle, label);

        // Intentar cargar el sonido del radar (Asegúrate de tener el archivo en resources/sonidos/)
        try {
            var resource = getClass().getResource("/sonidos/bip.mp3");
            if (resource != null) {
                bipSound = new AudioClip(resource.toExternalForm());
            }
        } catch (Exception e) {
            System.out.println("No se pudo cargar el archivo de audio. Se usará el bip del sistema.");
        }
    }

    /**
     * Constructor para inicializar la vista con radar activo (Etapas 3 y 4).
     * @param tag El modelo EloTelTag a representar.
     * @param nube Instancia de ETNube para reportar las detecciones.
     * @param territory Instancia de Territory para buscar los celulares.
     */
    public EloTelTagView(EloTelTag tag, ETNube nube, Territory territory) {
        this(tag);

        Circle radar = new Circle(0);
        radar.setStroke(Color.ORCHID);
        radar.setFill(Color.TRANSPARENT);
        radar.centerXProperty().bind(tag.xProperty());
        radar.centerYProperty().bind(tag.yProperty());
        this.getChildren().add(0, radar);

        Timeline radarTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> {
                    radar.setRadius(0);
                    // Emitir sonido al iniciar la expansión del radar
                    if (bipSound != null) {
                        bipSound.play();
                    } else {
                        // Plan B: Bip por defecto del sistema si no hay archivo
                        java.awt.Toolkit.getDefaultToolkit().beep();
                    }
                }),
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