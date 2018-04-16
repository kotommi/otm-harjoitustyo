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
import javafx.scene.input.KeyCode;

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
        evaluate();
    }

    public void evaluate() {
        inputField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    double d = de.evaluate(inputField.getText());
                    resultArea.appendText("\n" + inputField.getText() + "\t" + Double.toString(d));
                    inputField.clear();
                } catch (IllegalArgumentException e) {
                    inputField.setText("Malformed expression");
                }
            }
        });
    }

}
