package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.Optional;


public class Main extends Application {

    Label label;
    ListView<String> names;
    ListView<String> surnames;
    ListView<String> fatherland;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FlowPane rootNode = new FlowPane(10, 10);
        rootNode.setAlignment(Pos.CENTER);

        primaryStage.setTitle("Some application");
        primaryStage.setScene(new Scene(rootNode, 500, 300));

        label = new Label("Text");

        Button fullNameEnterButton = new Button("Press here and enter your full name");
        fullNameEnterButton.setOnAction(event -> showInputTextDialog());

        rootNode.getChildren().addAll(fullNameEnterButton, label);

        primaryStage.show();
    }


    private void showInputTextDialog() {

        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("Enter Name");
        textInputDialog.setHeaderText("Enter your name:");
        textInputDialog.setContentText("Name:");

        Optional<String> result = textInputDialog.showAndWait();

        result.ifPresent(name -> processFullName(name));
    }


    private void processFullName(String fullName) {
        String[] fullNameParts = fullName.split(" ");


    }

    private ListView<String> createList() {
        ObservableList<String> someList = FXCollections.observableArrayList();
        ListView<String> listView = new ListView<>(someList);
        listView.setPrefSize(100, 300);
        return listView;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
