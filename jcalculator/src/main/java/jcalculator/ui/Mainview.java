/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcalculator.ui;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jcalculator.database.Database;
import jcalculator.database.ScrollbackDao;

/**
 *
 * @author tomko
 */
public class Mainview extends Application {
    
    private DoubleEvaluator eval;
    private Stage stage;
    private Scene basicview;
    private ScrollbackDao sbdao;
    private FXMLBasicViewController bvc;
    
    @Override
    public void init() throws Exception {
        eval = new DoubleEvaluator();
        sbdao = new ScrollbackDao();
        createBasicScene();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        setBasicScene(stage);
    }
    
    public void createBasicScene() {
        FXMLLoader basicViewLoader = new FXMLLoader();
        basicViewLoader.setLocation(this.getClass().getResource("/basic.fxml"));
        Parent basic = null;
        try {
            basic = basicViewLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(Mainview.class.getName()).log(Level.SEVERE, null, ex);
        }
        bvc = basicViewLoader.getController();
        bvc.setApp(this);
        bvc.setDoubleEvaluator(eval);
        bvc.setScrollbackDao(sbdao);
        basicview = new Scene(basic);
    }
    
    public void setBasicScene(Stage stage) {
        stage.setTitle("jcalculator");
        stage.setScene(basicview);
        stage.setOnShowing(e -> bvc.setScrollback());
        stage.setOnHidden(e -> bvc.shutdown());
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(Mainview.class);
    }
    
}
