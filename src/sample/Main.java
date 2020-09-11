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
    ListView<String> namesListView;
    ListView<String> surnamesListView;
    ListView<String> fatherlandsListView;

    ObservableList<String> names;
    ObservableList<String> surnames;
    ObservableList<String> fatherlands;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FlowPane rootNode = new FlowPane(10, 10);
        rootNode.setAlignment(Pos.CENTER);

        primaryStage.setTitle("Some application");
        primaryStage.setScene(new Scene(rootNode, 600, 500));

        label = new Label("Text");

        Button fullNameEnterButton = new Button("Press here and enter your full name");
        fullNameEnterButton.setOnAction(event -> showInputTextDialog());

        names = FXCollections.observableArrayList();
        namesListView = new ListView<>(names);
        namesListView.setPrefSize(100, 300);

        surnames = FXCollections.observableArrayList();
        surnamesListView = new ListView<>(surnames);
        surnamesListView.setPrefSize(100, 300);

        fatherlands = FXCollections.observableArrayList();
        fatherlandsListView = new ListView<>(fatherlands);
        fatherlandsListView.setPrefSize(100, 300);

        rootNode.getChildren().addAll(fullNameEnterButton, namesListView, surnamesListView,
                fatherlandsListView, label);

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
        names.add(fullNameParts[1]);
        surnames.add(fullNameParts[0]);
        fatherlands.add(fullNameParts[2]);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
