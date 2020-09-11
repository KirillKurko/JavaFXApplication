package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.*;


public class Main extends Application {

    Map<Type, Label> labels;
    Map<Type, ObservableList<String>> observableLists;
    Map<Type, ListView<String>> listViews;

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane rootNode = new GridPane();
        rootNode.setAlignment(Pos.CENTER);

        primaryStage.setTitle("Names");
        primaryStage.setScene(new Scene(rootNode, 500, 400));

        labels = createLabels();
        observableLists = createObservableLists();
        listViews = createListViews();
        listViews.get(Type.SURNAME).getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Button fullNameEnterButton = new Button("Enter name");
        fullNameEnterButton.setOnAction(event -> showInputTextDialog());
        fullNameEnterButton.setMaxSize(100, 25);

        Button processSelectedSurnamesButton = new Button("Process");
        processSelectedSurnamesButton.setMaxSize(100, 25);
        processSelectedSurnamesButton.setOnAction(event -> processSelectedSurnames());

        rootNode.add(fullNameEnterButton, 0, 0);
        rootNode.add(processSelectedSurnamesButton, 3, 0);

        addLabelsToRootNode(rootNode, 1);
        addListViewsToRootNode(rootNode, 2);

        primaryStage.show();
    }

    public void addLabelsToRootNode(GridPane rootNode, int rowIndex) {
        for (Type type: Type.values()) {
            rootNode.add(labels.get(type), type.ordinal(), rowIndex);
        }
    }

    public void addListViewsToRootNode(GridPane rootNode, int rowIndex) {
        for (Type type: Type.values()) {
            rootNode.add(listViews.get(type), type.ordinal(), rowIndex);
        }
    }

    public Map<Type, Label> createLabels() {
        Map<Type, Label> labels = new HashMap<>();
        for (Type type: Type.values()) {
            labels.put(type, Helper.createLabel(type.toString(), 100, 50));
        }
        return labels;
    }

    public Map<Type, ObservableList<String>> createObservableLists() {
        Map<Type, ObservableList<String>> observableLists = new HashMap<>();
        for (Type type: Type.values()) {
            observableLists.put(type, FXCollections.observableArrayList());
        }
        return observableLists;
    }

    public Map<Type, ListView<String>> createListViews() {
        Map<Type, ListView<String>> listViews = new HashMap<>();
        for (Type type: Type.values()) {
            listViews.put(type, Helper.createListView(observableLists.get(type), 100, 300));
        }
        return listViews;
    }

    private void showInputTextDialog() {
        TextInputDialog textInputDialog = Helper.createTextInputDialog("Name", "Enter name", "Name: ");
        Optional<String> result = textInputDialog.showAndWait();
        result.ifPresent(this::processFullName);
    }

    private void processFullName(String fullName) {
        String[] fullNameParts = fullName.split(" ");
        observableLists.get(Type.NAME).add(fullNameParts[1]);
        observableLists.get(Type.SURNAME).add(fullNameParts[0]);
        observableLists.get(Type.FATHERLAND).add(fullNameParts[2]);
    }

    private void processSelectedSurnames() {
        ArrayList<String> selectedSurnames = new ArrayList<>(listViews.get(Type.SURNAME).getSelectionModel().getSelectedItems());
        selectedSurnames.sort(Comparator.naturalOrder());
        observableLists.get(Type.SORTED_SURNAME).clear();
        observableLists.get(Type.SORTED_SURNAME).addAll(selectedSurnames);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
