package sample;

import javafx.scene.control.Label;

public class Helper {

    public static Label createLabel(String text, double width, double height) {
        Label label = new Label(text);
        label.setMaxSize(width, height);
        return label;
    }
}
