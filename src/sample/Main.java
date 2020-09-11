package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.*;


public class Main extends Application {

    ListView<String> namesListView;
    ListView<String> surnamesListView;
    ListView<String> fatherlandsListView;
    ListView<String> sortedSelectedSurnamesListView;

    ObservableList<String> names;
    ObservableList<String> surnames;
    ObservableList<String> fatherlands;
    ObservableList<String> sortedSelectedSurnames;


    @Override
    public void start(Stage primaryStage) throws Exception {

        FlowPane rootNode = new FlowPane(10, 10);
        rootNode.setAlignment(Pos.CENTER);

        primaryStage.setTitle("Some application");
        primaryStage.setScene(new Scene(rootNode, 800, 500));

        Button fullNameEnterButton = new Button("Press here and enter your full name");
        fullNameEnterButton.setOnAction(event -> showInputTextDialog());

        names = FXCollections.observableArrayList();
        namesListView = new ListView<>(names);
        namesListView.setPrefSize(100, 300);

        surnames = FXCollections.observableArrayList();
        surnamesListView = new ListView<>(surnames);
        surnamesListView.setPrefSize(100, 300);
        surnamesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        fatherlands = FXCollections.observableArrayList();
        fatherlandsListView = new ListView<>(fatherlands);
        fatherlandsListView.setPrefSize(100, 300);

        sortedSelectedSurnames = FXCollections.observableArrayList();
        sortedSelectedSurnamesListView = new ListView<>(sortedSelectedSurnames);
        sortedSelectedSurnamesListView.setPrefSize(100, 300);

        Button processSelectedSurnamesButton = new Button("Process");
        processSelectedSurnamesButton.setOnAction(event -> processSelectedSurnames());

        rootNode.getChildren().addAll(fullNameEnterButton, surnamesListView, namesListView,
                fatherlandsListView, sortedSelectedSurnamesListView, processSelectedSurnamesButton);

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

    private void processSelectedSurnames() {
        ArrayList<String> selectedSurnames = new ArrayList<>(surnamesListView.getSelectionModel().getSelectedItems());
        selectedSurnames.sort(Comparator.naturalOrder());
        sortedSelectedSurnames.clear();
        sortedSelectedSurnames.addAll(selectedSurnames);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
