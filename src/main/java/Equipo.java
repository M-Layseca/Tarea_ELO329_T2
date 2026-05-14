import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Representa la superclase base para todos los dispositivos que participan en la simulación.
 * Maneja la posición espacial, el propietario y la lógica de movimiento en el territorio.
 */
public class Equipo {
    protected final String ownerName;
    protected DoubleProperty x = new SimpleDoubleProperty();
    protected DoubleProperty y = new SimpleDoubleProperty();

    private double r, theta, dtheta;

    /**
     * Construye un nuevo dispositivo base.
     *
     * @param owner  El nombre del dueño del equipo.
     * @param _x     La posición inicial en el eje X (en pixeles).
     * @param _y     La posición inicial en el eje Y (en pixeles).
     * @param r      La rapidez de movimiento (magnitud de la velocidad en pixeles/s).
     * @param theta  El ángulo inicial de la velocidad (en radianes).
     * @param dtheta La variación máxima del ángulo para el movimiento aleatorio (en radianes).
     */
    public Equipo(String owner, double _x, double _y, double r, double theta, double dtheta) {
        ownerName = owner;
        x.set(_x);
        y.set(_y);
        this.r = r;
        this.theta = theta;
        this.dtheta = dtheta;
    }

    /**
     * Obtiene la propiedad observable de la posición X.
     * @return DoubleProperty correspondiente a la coordenada X.
     */
    public DoubleProperty xProperty() { return x; }

    /**
     * Obtiene la propiedad observable de la posición Y.
     * @return DoubleProperty correspondiente a la coordenada Y.
     */
    public DoubleProperty yProperty() { return y; }

    /**
     * Obtiene el nombre del dueño del equipo.
     * @return String con el nombre del propietario.
     */
    public String getOwnerName() { return ownerName; }

    /**
     * Calcula y actualiza la nueva posición del equipo en base al tiempo transcurrido.
     *
     * @param dt El delta de tiempo (en segundos) en el que avanza la simulación.
     */
    public void move(double dt) {
        theta += dtheta * dt;
        x.set(x.get() + r * Math.cos(theta) * dt);
        y.set(y.get() + r * Math.sin(theta) * dt);
    }
}