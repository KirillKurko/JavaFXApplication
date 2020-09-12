package code.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextInputDialog;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;


public class MainController {

    private ObservableList<String> names = FXCollections.observableArrayList();
    private ObservableList<String> surnames = FXCollections.observableArrayList();
    private ObservableList<String> fatherlands = FXCollections.observableArrayList();
    private ObservableList<String> processedSurnames = FXCollections.observableArrayList();

    @FXML
    private ListView<String> surnameListView;

    @FXML
    private ListView<String> nameListView;

    @FXML
    private ListView<String> fatherlandListView;

    @FXML
    private ListView<String> processedListView;

    public void initialize() {
        surnameListView.setItems(surnames);
        surnameListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        nameListView.setItems(names);
        fatherlandListView.setItems(fatherlands);
        processedListView.setItems(processedSurnames);
    }

    @FXML
    private void showInputTextDialog() {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("Name");
        textInputDialog.setHeaderText("Enter name");
        textInputDialog.setContentText("Name: ");
        Optional<String> result = textInputDialog.showAndWait();
        result.ifPresent(name -> {
            String[] fullNameParts = name.split(" ");
            surnames.add(fullNameParts[0]);
            names.add(fullNameParts[1]);
            fatherlands.add(fullNameParts[2]);
        });
    }

    @FXML
    private void processSelectedSurnames() {
        ArrayList<String> selectedSurnames = new ArrayList<>(surnameListView.getSelectionModel().getSelectedItems());
        selectedSurnames.sort(Comparator.naturalOrder());
        processedSurnames.clear();
        processedSurnames.addAll(selectedSurnames);
    }
}
