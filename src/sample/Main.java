package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.*;


public class Main extends Application {

    ListView<String> namesListView;
    ListView<String> surnamesListView;
    ListView<String> fatherlandsListView;
    ListView<String> sortedSelectedSurnamesListView;

    ObservableList<String> names = FXCollections.observableArrayList();
    ObservableList<String> surnames;
    ObservableList<String> fatherlands;
    ObservableList<String> sortedSelectedSurnames;

    Label nameLabel;
    Label surnameLabel;
    Label fatherlandLabel;
    Label sortedSurnamesLabel;

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane rootNode = new GridPane();
        rootNode.setAlignment(Pos.CENTER);

        primaryStage.setTitle("Names");
        primaryStage.setScene(new Scene(rootNode, 500, 400));

        nameLabel = Helper.createLabel("Name", 100, 50);
        surnameLabel = Helper.createLabel("Surname", 100, 50);
        fatherlandLabel = Helper.createLabel("Fatherland", 100, 50);
        sortedSurnamesLabel = Helper.createLabel("Processed", 100, 50);


        Button fullNameEnterButton = new Button("Enter name");
        fullNameEnterButton.setOnAction(event -> showInputTextDialog());
        fullNameEnterButton.setMaxSize(100, 25);

        names = FXCollections.observableArrayList();
        namesListView = Helper.createListView(names, 100, 300);

        surnames = FXCollections.observableArrayList();
        surnamesListView = Helper.createListView(surnames, 100, 300);

        fatherlands = FXCollections.observableArrayList();
        fatherlandsListView = Helper.createListView(surnames, 100, 300);

        sortedSelectedSurnames = FXCollections.observableArrayList();
        sortedSelectedSurnamesListView = Helper.createListView(sortedSelectedSurnames, 100, 300);

        Button processSelectedSurnamesButton = new Button("Process");
        processSelectedSurnamesButton.setMaxSize(100, 25);
        processSelectedSurnamesButton.setOnAction(event -> processSelectedSurnames());

        rootNode.add(fullNameEnterButton, 0, 0);
        rootNode.add(processSelectedSurnamesButton, 3, 0);

        rootNode.add(surnameLabel, 0, 1);
        rootNode.add(nameLabel, 1, 1);
        rootNode.add(fatherlandLabel, 2, 1);
        rootNode.add(sortedSurnamesLabel, 3, 1);

        rootNode.add(surnamesListView, 0, 2);
        rootNode.add(namesListView, 1, 2);
        rootNode.add(fatherlandsListView, 2, 2);
        rootNode.add(sortedSelectedSurnamesListView, 3, 2);

        primaryStage.show();
    }

    private void showInputTextDialog() {
        TextInputDialog textInputDialog = Helper.createTextInputDialog("Name", "Enter name", "Name: ");
        Optional<String> result = textInputDialog.showAndWait();
        result.ifPresent(this::processFullName);
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
