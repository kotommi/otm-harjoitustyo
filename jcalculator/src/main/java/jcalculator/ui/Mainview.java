/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcalculator.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author tomko
 */
public class Mainview extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        TextField resultField = new TextField("\n\n(2 + 4)^2        = 36");
        resultField.setEditable(false);
        TextField editField = new TextField("1 + 1 + 2 + 3 + 5 + 8");

        HBox napit = new HBox();
        Button plus = new Button("+");
        Button minus = new Button("-");
        Button times = new Button("x");
        Button div = new Button("/");
        Button equals = new Button("=");
        napit.getChildren().addAll(plus, minus, times, div, equals);

        VBox vb = new VBox();
        vb.getChildren().addAll(resultField, editField, napit);

        Scene view = new Scene(vb);

        stage.setScene(view);
        stage.show();

    }

    public static void main(String[] args) {
        launch(Mainview.class);
    }

}
