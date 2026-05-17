import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Locale;

public class Stage4 extends Application {
    private Territory territory;
    private TerritoryView territoryView;
    private double timeStep;
    private ETNube nube;

    @Override
    public void start(Stage primaryStage) {
        nube = new ETNube();
        Scanner configFile = openConfig(primaryStage);

        territory = new Territory();
        territoryView = new TerritoryView(territory, configFile.next());
        timeStep = configFile.nextDouble();

        BorderPane scenePane = new BorderPane();
        scenePane.setTop(createMenuBar());
        scenePane.setCenter(territoryView);

        setupSimulator(configFile);

        Scene scene = new Scene(scenePane, 1000, 700);
        // Título actualizado para la Etapa 4
        primaryStage.setTitle("EloTelTag Simulation: Stage 4");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu simulMenu = new Menu("Simulation");
        MenuItem playMenuItem = new MenuItem("Play");
        MenuItem pauseMenuItem = new MenuItem("Pause");
        simulMenu.getItems().addAll(playMenuItem, pauseMenuItem);
        menuBar.getMenus().add(simulMenu);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(1000 * timeStep), e -> territory.moveAll(timeStep))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);

        playMenuItem.setOnAction(e -> timeline.play());
        pauseMenuItem.setOnAction(e -> timeline.pause());

        return menuBar;
    }

    private void setupSimulator(Scanner in) {
        int personNumber = in.nextInt();
        for (int i = 0; i < personNumber; i++)
            setupPersonEquipment(in);
    }

    private void setupPersonEquipment(Scanner in) {
        String personName = in.next();
        int tagNumber = in.nextInt();
        boolean isThereTablet = in.nextInt() == 1;

        double x = in.nextFloat(); double y = in.nextFloat();
        double r = in.nextFloat(); double theta = Math.toRadians(in.nextFloat());
        double dt = Math.toRadians(in.nextFloat());

        Cellular cellular = new Cellular(personName, x, y, r, theta, dt);
        CellularView cView = new CellularView(cellular, nube);
        territory.addEquipment(cellular);
        territoryView.add(cView);

        for (int j = 0; j < tagNumber; j++)
            setupEloTags(in, personName);

        if (isThereTablet) {
            double tx = in.nextFloat(); double ty = in.nextFloat();
            double tr = in.nextFloat(); double tt = Math.toRadians(in.nextFloat());
            double tdt = Math.toRadians(in.nextFloat());

            Tablet tablet = new Tablet(personName, tx, ty, tr, tt, tdt);
            TabletView tView = new TabletView(tablet, nube, territory);
            territory.addEquipment(tablet);
            territoryView.add(tView);
        }
    }

    private void setupEloTags(Scanner in, String personName) {
        String tagName = in.next();
        double x = in.nextFloat(); double y = in.nextFloat();
        double r = in.nextFloat(); double theta = in.nextFloat();
        double dt = in.nextFloat();

        EloTelTag tag = new EloTelTag(personName, tagName, x, y, r, theta, dt);
        EloTelTagView tagView = new EloTelTagView(tag, nube, territory);
        territory.addEquipment(tag);
        territoryView.add(tagView);
    }

    private Scanner openConfig(Stage stage) {
        Scanner configFile;
        do {
            try {
                File file = fileChooser(stage);
                configFile = new Scanner(file);
                configFile.useLocale(Locale.US);
            } catch (FileNotFoundException e) { configFile = null; }
        } while (configFile == null);
        return configFile;
    }

    private File fileChooser(Stage stage) {
        FileChooser fChooser = new FileChooser();
        fChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text file", "*.txt"));
        return fChooser.showOpenDialog(stage);
    }

    public static void main(String[] args) { launch(args); }
}