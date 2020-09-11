package sample;

import javafx.application.Application;
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

    private Map<Type, Label> labels;
    private Map<Type, ObservableList<String>> observableLists;
    private Map<Type, ListView<String>> listViews;

    private Creator creator;

    @Override
    public void start(Stage primaryStage) throws Exception {

        creator = new Creator();

        GridPane rootNode = new GridPane();
        rootNode.setAlignment(Pos.CENTER);

        primaryStage.setTitle("Names");
        primaryStage.setScene(new Scene(rootNode, 500, 400));

        labels = creator.createLabels();
        observableLists = creator.createObservableLists();
        listViews = creator.createListViews(observableLists);
        listViews.get(Type.SURNAME).getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Button fullNameEnterButton = creator.createButton("Enter name", 100, 25);
        fullNameEnterButton.setOnAction(event -> showInputTextDialog());

        Button processSelectedSurnamesButton = creator.createButton("Process", 100, 25);
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

    private void showInputTextDialog() {
        TextInputDialog textInputDialog = creator.createTextInputDialog("Name", "Enter name", "Name: ");
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
