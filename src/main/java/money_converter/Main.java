package money_converter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane root = new GridPane();
        root.setMinSize(500, 500);
        root.setHgap(10);
        root.setVgap(12);

        // Create the convert button
        Button convertButton = new Button("Convert");
        convertButton.setId("convertBtn");
        convertButton.setMaxWidth(Double.MAX_VALUE);
        //convertButton.setOnAction();

        // Create textField
        TextField fromText = new TextField();
        TextField toText = new TextField();

        // Create ComboBoxes
        ComboBox<String> fromBox = new ComboBox<>();
        ComboBox<String> toBox = new ComboBox<>();

        // Create labels
        Label fromLabel = new Label("From");
        fromLabel.setLabelFor(fromText);
        Label toLabel = new Label("To");
        toLabel.setLabelFor(toText);
        Label resultLabel = new Label("Money converted");
        GridPane.setFillWidth(resultLabel, true);
        resultLabel.setMaxWidth(Double.MAX_VALUE);
        resultLabel.setAlignment(Pos.CENTER);

        root.add(fromLabel, 0, 0);
        root.add(fromText, 1, 0);
        root.add(fromBox, 2, 0);
        root.add(toLabel, 0, 1);
        root.add(toText, 1, 1);
        root.add(toBox, 2, 1);
        root.add(resultLabel, 0, 2, 3, 1);
        root.add(convertButton, 0, 3, 3, 1);
        root.setAlignment(Pos.CENTER);

        primaryStage.setTitle("Money Converter");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
