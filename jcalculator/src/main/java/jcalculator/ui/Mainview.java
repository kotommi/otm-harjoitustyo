/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcalculator.ui;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author tomko
 */
public class Mainview extends Application {

    DoubleEvaluator eval;
    private Stage stage;
    private Scene basicview;

    @Override
    public void init() throws Exception {
        eval = new DoubleEvaluator();
        FXMLLoader basicViewLoader = new FXMLLoader(getClass().getResource("/FXMLBasicView.fxml"));
        Parent basic = basicViewLoader.load();
        FXMLBasicViewController bvc = basicViewLoader.getController();
        bvc.setApp(this);
        bvc.setDoubleEvaluator(eval);
        basicview = new Scene(basic);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        stage.setTitle("jcalculator");
        stage.setScene(basicview);
        stage.show();
    }

    public static void main(String[] args) {
        launch(Mainview.class);
    }

}
