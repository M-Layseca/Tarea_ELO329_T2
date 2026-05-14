import java.util.ArrayList;

public class Territory {
    private ArrayList<Equipo> equipment = new ArrayList<>();

    public void addEquipment(Equipo eq) {
        equipment.add(eq);
    }

    public void moveAll(double timeStep) {
        for (Equipo eq : equipment) {
            eq.move(timeStep);
        }
    }

    public void detectarCelularesCercanos(Equipo emisor, ETNube nube) {
        double rMaxima = 50.0;
        double x1 = emisor.xProperty().get();
        double y1 = emisor.yProperty().get();

        for (Equipo eq : equipment) {
            if (eq instanceof Cellular) {
                double x2 = eq.xProperty().get();
                double y2 = eq.yProperty().get();

                double distancia = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

                if (distancia <= rMaxima) {
                    String nombreDispositivo = (emisor instanceof EloTelTag) ?
                            ((EloTelTag) emisor).getName() : "Tablet";

                    nube.reportarDispositivo(emisor.getOwnerName(), nombreDispositivo, x2, y2);
                }
            }
        }
    }
}