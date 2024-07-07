package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Startet das Spiel "Tic Tac Toe"
 * @author Anton Schmitter, Joshua-Scott Schoettke Gruppe 21
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Userinterface.fxml"));
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setMinHeight(840);
        primaryStage.setMinWidth(1000);
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
