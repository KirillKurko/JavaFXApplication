package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

public class Helper {

    public static Label createLabel(String text, double width, double height) {
        Label label = new Label(text);
        label.setMaxSize(width, height);
        return label;
    }

    public static ListView<String> createListView(ObservableList<String> observableList, double width, double height) {
        ListView<String> listView = new ListView<>(observableList);
        listView.setPrefSize(width, height);
        return listView;
    }

    public static TextInputDialog createTextInputDialog(String title, String headerText, String contentText) {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle(title);
        textInputDialog.setHeaderText(headerText);
        textInputDialog.setContentText(contentText);
        return textInputDialog;
    }
}
