/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcalculator.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import jcalculator.domain.conversion.Converter;
import jcalculator.domain.conversion.Encoding;
import jcalculator.domain.conversion.Endian;

/**
 * FXML Controller class
 *
 * @author tomko
 */
public class FXMLProgrammingController implements Initializable {
    private Mainview app;
    private Converter converter;

    //fields
    @FXML
    private TextField resultField;
    @FXML
    private TextField inputField;
    //viewSelector
    @FXML
    private MenuItem basicItem;
    //fromSelector
    @FXML
    private MenuButton fromSelector;
    @FXML
    private MenuItem fromDecimalItem;
    @FXML
    private MenuItem fromHexItem;
    @FXML
    private MenuItem fromBinaryItem;
    //toSelector
    @FXML
    private MenuButton toSelector;
    @FXML
    private MenuItem toBinaryItem;
    @FXML
    private MenuItem toDecimalItem;
    @FXML
    private MenuItem toHexItem;
    //endianSelector
    @FXML
    private MenuButton endianSelector;
    @FXML
    private MenuItem littleItem;
    @FXML
    private MenuItem bigItem;


    public void setApp(Mainview app) {
        this.app = app;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        changeView();
        changeFrom();
        changeTo();
        changeEndian();
        evaluate();
    }

    public void changeEndian() {
        littleItem.setOnAction(actionEvent -> {
            converter.setEndian(Endian.LITTLE);
            endianSelector.setText(littleItem.getText());
        });
        bigItem.setOnAction(actionEvent -> {
            converter.setEndian(Endian.BIG);
            endianSelector.setText(bigItem.getText());
        });
    }

    public void changeFrom() {
        fromDecimalItem.setOnAction(event -> {
            converter.setFrom(Encoding.DECIMAL);
            fromSelector.setText(fromDecimalItem.getText());
        });
        fromHexItem.setOnAction(event -> {
            converter.setFrom(Encoding.HEX);
            fromSelector.setText(fromHexItem.getText());
        });
        fromBinaryItem.setOnAction(event -> {
            converter.setFrom(Encoding.BINARY);
            fromSelector.setText(fromBinaryItem.getText());
        });
    }

    public void changeTo() {
        toBinaryItem.setOnAction(event -> {
            converter.setTo(Encoding.BINARY);
            toSelector.setText(toBinaryItem.getText());
        });
        toDecimalItem.setOnAction(actionEvent -> {
            converter.setTo(Encoding.DECIMAL);
            toSelector.setText(toDecimalItem.getText());
        });
        toHexItem.setOnAction(actionEvent -> {
            converter.setTo(Encoding.HEX);
            toSelector.setText(toHexItem.getText());
        });
    }

    public void changeView() {
        this.basicItem.setOnAction(event -> {
            this.app.setBasicScene();
        });
    }

    public void evaluate() {
        this.inputField.setOnKeyTyped(keyEvent -> {
            String s = inputField.getText();
            if (s.isEmpty()) {
                resultField.setText("");
            } else {
                s = converter.convert(s);
                resultField.setText(s);
            }
        });
    }

}
