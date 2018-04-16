/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcalculator.ui;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import jcalculator.database.ScrollbackDao;

/**
 * FXML Controller class
 *
 * @author tomko
 */
public class FXMLBasicViewController implements Initializable {

    private DoubleEvaluator de;
    private Mainview app;
    private ScrollbackDao sbdao;

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

    public void setScrollbackDao(ScrollbackDao sbdao) {
        this.sbdao = sbdao;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        evaluate();
    }

    public void shutdown() {
        List<String> lines = new ArrayList();
        String s = resultArea.getText();
        String[] split = s.split("\n");
        lines.addAll(Arrays.asList(split));
        sbdao.clearAndSave(lines);
        Platform.exit();
    }

    public void setScrollback() {
        resultArea.setText(sbdao.getScrollback());
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
