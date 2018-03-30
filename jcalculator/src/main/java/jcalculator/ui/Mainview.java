/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcalculator.ui;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author tomko
 */
public class Mainview extends Application {

    DoubleEvaluator eval;

    @Override
    public void init() throws Exception {
        eval = new DoubleEvaluator();
    }

    @Override
    public void start(Stage stage) throws Exception {

        TextArea resultField = new TextArea("\n\n(2 + 4)^2        = 36");
        resultField.setEditable(false);
        TextField editField = new TextField("1 + 1 + 2 + 3 + 5 + 8");

        ChoiceBox cb = new ChoiceBox();
        cb.setItems(FXCollections.observableArrayList("Calculator", "Units"));
        cb.getSelectionModel().selectFirst();
        cb.getSelectionModel();
        

        GridPane napit = new GridPane();

        HBox rivi = new HBox();
        Button plus = new Button("+");
        Button minus = new Button("-");
        Button times = new Button("x");
        Button div = new Button("/");
        Button equals = new Button("=");
        napit.getChildren().addAll(plus, minus, times, div, equals);

        VBox vb = new VBox();
        vb.getChildren().addAll(cb, resultField, editField, napit);

        Scene view = new Scene(vb);

        stage.setScene(view);
        stage.show();

    }

    public static void main(String[] args) {
        launch(Mainview.class);
    }

}
