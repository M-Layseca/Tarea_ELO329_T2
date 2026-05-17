import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ETNube {
    private Map<String, List<String>> reportesPorPersona = new HashMap<>();

    public void reportarDispositivo(String dueno, String nombreDispositivo, double x, double y) {
        String info = String.format("%s: %.0f, %.0f", nombreDispositivo, x, y);

        reportesPorPersona.putIfAbsent(dueno, new ArrayList<>());
        reportesPorPersona.get(dueno).add(info);
        System.out.println("Nube recibió reporte para " + dueno + " -> " + info);
    }

    public List<String> obtenerReportes(String dueno) {
        return reportesPorPersona.getOrDefault(dueno, new ArrayList<>());
    }
}