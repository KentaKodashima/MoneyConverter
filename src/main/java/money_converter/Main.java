package money_converter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
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
        Label resultLabel = new Label("Convert USD to your currency");
        resultLabel.setFont(new Font("Futura", 18));
        GridPane.setFillWidth(resultLabel, true);
        resultLabel.setMaxWidth(Double.MAX_VALUE);
        resultLabel.setAlignment(Pos.CENTER);

        // Setting the convert button an action to calculate
        convertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Double result;
                if (toBox.getValue() != null) {
                    if (toBox.getValue().equals("JPY")) {
                        try {
                            result = convert(Double.parseDouble(fromText.getText()), api.getRates().get(0));
                            resultLabel.setText("It's ¥ " + String.valueOf(result));
                        } catch (NumberFormatException e) {
                            Alert error = new Alert(Alert.AlertType.INFORMATION);
                            error.setTitle("Something went wrong");
                            error.setHeaderText("Invalid input");
                            error.setContentText("Please input only number or floating point");
                            error.showAndWait();
                        } catch (NullPointerException e) {
                            Alert error = new Alert(Alert.AlertType.INFORMATION);
                            error.setTitle("Something went wrong");
                            error.setHeaderText("The select box is empty");
                            error.setContentText("Please select a currency");
                            error.showAndWait();
                        }
                    } else if (toBox.getValue().equals("CAD")) {
                        try {
                            result = convert(Double.parseDouble(fromText.getText()), api.getRates().get(1));
                            resultLabel.setText("It's $ " + String.valueOf(result));
                        } catch (NumberFormatException e) {
                            Alert error = new Alert(Alert.AlertType.INFORMATION);
                            error.setTitle("Something went wrong");
                          error.setHeaderText("Invalid input");
                            error.setContentText("Please input only number or floating point");
                            error.showAndWait();
                        } catch (NullPointerException e) {
                            Alert error = new Alert(Alert.AlertType.INFORMATION);
                            error.setTitle("Something went wrong");
                            error.setHeaderText("The select box is empty");
                            error.setContentText("Please select a currency");
                            error.showAndWait();
                        }
                    } else {
                        try {
                            result = convert(Double.parseDouble(fromText.getText()), api.getRates().get(2));
                            resultLabel.setText("It's $ " + String.valueOf(result));
                        } catch (NumberFormatException e) {
                            Alert error = new Alert(Alert.AlertType.INFORMATION);
                            error.setTitle("Something went wrong");
                            error.setHeaderText("Invalid input");
                            error.setContentText("Please input only number or floating point");
                            error.showAndWait();
                        } catch (NullPointerException e) {
                            Alert error = new Alert(Alert.AlertType.INFORMATION);
                            error.setTitle("Something went wrong");
                            error.setHeaderText("The select box is empty");
                            error.setContentText("Please select a currency");
                            error.showAndWait();
                        }
                    }
                } else {
                  Alert error = new Alert(Alert.AlertType.INFORMATION);
                  error.setTitle("Something went wrong");
                  error.setHeaderText("Invalid input");
                  error.setContentText("Please input all required fields");
                  error.showAndWait();
                }
            }
        });

        root.add(fromLabel, 0, 0);
        root.add(fromText, 1, 0);
        root.add(fromBox, 2, 0);
        root.add(toLabel, 0, 1);
        root.add(toBox, 2, 1);
        root.add(convertButton, 0, 2, 3, 1);
        root.add(resultLabel, 0, 3, 3, 1);
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
