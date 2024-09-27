import boardifier.control.Logger;
import boardifier.control.StageFactory;
import boardifier.model.Model;
import control.GameController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.StageModel;
import view.BattleShipRootPane;
import view.GameView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main extends Application {
    /**
     * Launch the game
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Model model = new Model();
        StageFactory.registerModelAndView("bataille_navale", "model.StageModel", "view.BattleShipStageView");
        BattleShipRootPane rootPane = new BattleShipRootPane();
        GameView view = new GameView(model, primaryStage, rootPane);
        GameController control = new GameController(model, view);
        primaryStage.setTitle("Bataille Navale");
        control.setFirstStageName("bataille_navale");
        primaryStage.setScene(rootPane.getScene());
        primaryStage.show();
    }
}
