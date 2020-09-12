package code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent rootNode = FXMLLoader.load(getClass().getResource("/resources/fxml/layout.fxml"));
        Scene scene = new Scene(rootNode);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Names");
        primaryStage.setWidth(500);
        primaryStage.setHeight(400);

        primaryStage.show();
    }
}
