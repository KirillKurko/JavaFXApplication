package sample;

import com.sun.codemodel.internal.fmt.JTextFile;
import com.sun.tools.javac.comp.Flow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class Main extends Application {

    Label label;
    TextField textField;
    TextInputDialog textInputDialog;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FlowPane rootNode = new FlowPane(10, 10);
        rootNode.setAlignment(Pos.CENTER);

        primaryStage.setTitle("Some application");
        primaryStage.setScene(new Scene(rootNode, 500, 300));

        textInputDialog = new TextInputDialog("Enter your full name");
        Button fullNameEnterButton = new Button("Press here and enter your full name");
        fullNameEnterButton.setOnAction(event -> {
            textInputDialog.show();
        });

        rootNode.getChildren().addAll(fullNameEnterButton);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
