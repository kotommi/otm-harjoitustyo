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
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import jcalculator.database.ScrollbackDao;
import jcalculator.util.DoubleUtil;

/**
 * FXML Controller class
 *
 * @author tomko
 */
public class FXMLBasicViewController implements Initializable {

    private DoubleEvaluator de;
    private Mainview app;
    private ScrollbackDao sbdao;
    private double ans = 0;

    @FXML
    private TextArea resultArea;
    @FXML
    private TextField inputField;

    @FXML
    private MenuItem basicItem;

    @FXML
    private MenuItem programmingItem;

    @FXML
    private Button buttonEvaluate;
    @FXML
    private Button button0;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private Button buttonDot;
    @FXML
    private Button buttonPercent;
    @FXML
    private Button buttonDivide;
    @FXML
    private Button buttonMultiply;
    @FXML
    private Button buttonPlus;
    @FXML
    private Button buttonMinus;
    @FXML
    private Button buttonAns;
    @FXML
    private Button buttonPower;

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
        appends();
        evaluate();
        //changeView();
    }

    public void changeView() {
        programmingItem.setOnAction(event -> {
            app.setProgrammingScene();
        });
    }

    public void appends() {
        button0.setOnAction(event -> {
            insertCharacter("0");
        });
        button1.setOnAction(event -> {
            insertCharacter("1");
        });
        button2.setOnAction(event -> {
            insertCharacter("2");
        });
        button3.setOnAction(event -> {
            insertCharacter("3");
        });
        button4.setOnAction(event -> {
            insertCharacter("4");
        });
        button5.setOnAction(event -> {
            insertCharacter("5");
        });
        button6.setOnAction(event -> {
            insertCharacter("6");
        });
        button7.setOnAction(event -> {
            insertCharacter("7");
        });
        button8.setOnAction(event -> {
            insertCharacter("8");
        });
        button9.setOnAction(event -> {
            insertCharacter("9");
        });
        buttonAns.setOnAction(event -> {
            insertCharacter(DoubleUtil.toString(ans));
        });
        buttonDivide.setOnAction(event -> {
            insertCharacter("/");
        });
        buttonMultiply.setOnAction(event -> {
            insertCharacter("*");
        });
        buttonPlus.setOnAction(event -> {
            insertCharacter("+");
        });
        buttonMinus.setOnAction(event -> {
            insertCharacter("-");
        });
        buttonPower.setOnAction(event -> {
            insertCharacter("^");
        });

    }

    public void insertCharacter(String c) {
        inputField.appendText(c);
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
        buttonEvaluate.setOnAction(event -> {
            try {
                double d = de.evaluate(inputField.getText());
                String ds = DoubleUtil.toString(d);
                ans = Double.parseDouble(ds);
                //TODO right justify the answer
                resultArea.appendText("\n" + inputField.getText() + "\t = " + ds);
                inputField.clear();
            } catch (IllegalArgumentException e) {
                inputField.setText("Malformed expression");
            }
        });

        inputField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                buttonEvaluate.fire();
            }
        });

    }

}
