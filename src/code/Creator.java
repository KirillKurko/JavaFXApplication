package code;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.util.HashMap;
import java.util.Map;


public class Creator {

    public Map<Type, Label> createLabels() {
        Map<Type, Label> labels = new HashMap<>();
        for (Type type: Type.values()) {
            labels.put(type, createLabel(type.toString(), 100, 50));
        }
        return labels;
    }

    public Map<Type, ListView<String>> createListViews(Map<Type, ObservableList<String>> observableLists) {
        Map<Type, ListView<String>> listViews = new HashMap<>();
        for (Type type: Type.values()) {
            listViews.put(type, createListView(observableLists.get(type), 100, 300));
        }
        return listViews;
    }

    public Map<Type, ObservableList<String>> createObservableLists() {
        Map<Type, ObservableList<String>> observableLists = new HashMap<>();
        for (Type type: Type.values()) {
            observableLists.put(type, FXCollections.observableArrayList());
        }
        return observableLists;
    }

    public Label createLabel(String text, double width, double height) {
        Label label = new Label(text);
        label.setMaxSize(width, height);
        return label;
    }

    public ListView<String> createListView(ObservableList<String> observableList, double width, double height) {
        ListView<String> listView = new ListView<>(observableList);
        listView.setPrefSize(width, height);
        return listView;
    }

    public TextInputDialog createTextInputDialog(String title, String headerText, String contentText) {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle(title);
        textInputDialog.setHeaderText(headerText);
        textInputDialog.setContentText(contentText);
        return textInputDialog;
    }

    public Button createButton(String text, double width, double height) {
        Button button = new Button(text);
        button.setMaxSize(width, height);
        return button;
    }
}