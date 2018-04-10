/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcalculator.ui;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author tomko
 */
public class FXMLBasicViewController implements Initializable {

    private DoubleEvaluator de;
    private Mainview app;

    @FXML
    private TextArea resultArea;

    @FXML
    private TextField inputField;

    public void setDoubleEvaluator(DoubleEvaluator de) {
        this.de = de;
    }

    public void setApp(Mainview app) {
        this.app = app;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
