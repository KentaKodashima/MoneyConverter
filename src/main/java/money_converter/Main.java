package money_converter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

        // Create textField
        TextField fromText = new TextField();

        // Create ComboBoxes
        MoneyAPI api = new MoneyAPI();
        api.connectToAPI();

        ObservableList<String> usdOption = FXCollections.observableArrayList("USD");
        ObservableList<String> currencyNames = FXCollections.observableArrayList(api.getCountries());
        ComboBox<String> fromBox = new ComboBox<>(usdOption);
        ComboBox<String> toBox = new ComboBox<>(currencyNames);

        // Create labels
        Label fromLabel = new Label("From");
        fromLabel.setLabelFor(fromText);
        Label toLabel = new Label("To");
        Label resultLabel = new Label("Money converted");
        GridPane.setFillWidth(resultLabel, true);
        resultLabel.setMaxWidth(Double.MAX_VALUE);
        resultLabel.setAlignment(Pos.CENTER);

        // Setting the convert button an action to calculate
        convertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Double result;
                if (toBox.getValue().equals("JPY")) {
                    result = convert(Double.parseDouble(fromText.getText()), api.getRates().get(0));
                    resultLabel.setText("It's ¥ " + String.valueOf(result));
                } else if (toBox.getValue().equals("CAD")) {
                    result = convert(Double.parseDouble(fromText.getText()), api.getRates().get(1));
                    resultLabel.setText("It's $ " + String.valueOf(result));
                } else {
                    result = convert(Double.parseDouble(fromText.getText()), api.getRates().get(2));
                    resultLabel.setText("It's $ " + String.valueOf(result));
                }
            }
        });

        root.add(fromLabel, 0, 0);
        root.add(fromText, 1, 0);
        root.add(fromBox, 2, 0);
        root.add(toLabel, 0, 1);
        root.add(toBox, 2, 1);
        root.add(resultLabel, 0, 2, 3, 1);
        root.add(convertButton, 0, 3, 3, 1);
        root.setAlignment(Pos.CENTER);

        primaryStage.setTitle("Money Converter");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public double convert(double input, double rate) {
        return input * rate;
    }

    public static void main(String[] args) {
      launch(args);
    }
}
