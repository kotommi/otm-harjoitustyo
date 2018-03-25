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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author tomko
 */
public class Mainview extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Label label = new Label("Here be text");
        TextField tf = new TextField("Write stuff here");
        Button b = new Button("Ok");

        VBox vb = new VBox();
        vb.getChildren().addAll(label, tf, b);

        Scene view = new Scene(vb);

        stage.setScene(view);
        stage.show();

    }

    public static void main(String[] args) {
        launch(Mainview.class);
    }

}
