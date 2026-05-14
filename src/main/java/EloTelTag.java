/**
 * Representa un dispositivo rastreable tipo EloTelTag dentro de la simulación.
 * Hereda de la clase {@link Equipo} e incluye un identificador único (nombre del ítem).
 */
public class EloTelTag extends Equipo {
    private final String name;

    /**
     * Construye un nuevo EloTelTag.
     *
     * @param owner El nombre del dueño del tag.
     * @param n     El nombre del ítem al que está adherido el tag (ej. "llaves", "mochila").
     * @param x     La posición inicial en el eje X (en pixeles).
     * @param y     La posición inicial en el eje Y (en pixeles).
     * @param r     La rapidez de movimiento (en pixeles/s).
     * @param theta El ángulo inicial de la velocidad.
     * @param dt    La variación del ángulo para el movimiento.
     */
    public EloTelTag(String owner, String n, double x, double y, double r, double theta, double dt) {
        super(owner, x, y, r, theta, dt);
        name = n;
    }

    /**
     * Obtiene el nombre del ítem rastreado.
     * @return String con el nombre del tag.
     */
    public String getName() {
        return name;
    }
}