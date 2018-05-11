package jcalculator.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import jcalculator.domain.conversion.Converter;
import jcalculator.domain.conversion.Encoding;
import jcalculator.domain.conversion.Endian;

/**
 * FXML Controller class. View for converting numbers from and to different
 * bases.
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
    //endianToSelector
    @FXML
    private MenuButton endianSelector2;
    @FXML
    private MenuItem littleItem2;
    @FXML
    private MenuItem bigItem2;
    @FXML
    private Button buttonHelp;

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
        resultField.setEditable(false);
        endianSelector.setDisable(true);
        changeView();
        changeFrom();
        changeTo();
        changeEndian();
        changeEndianTo();
        evaluate();
        helpButton();
    }

    /**
     * Initializes the endian selectors.
     */
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

    /**
     * Initializes the endian selectors.
     */
    public void changeEndianTo() {
        littleItem2.setOnAction(event -> {
            converter.setEndianTo(Endian.LITTLE);
            endianSelector2.setText(littleItem2.getText());
            inputField.requestFocus();
        });
        bigItem2.setOnAction(event -> {
            converter.setEndianTo(Endian.BIG);
            endianSelector2.setText(bigItem2.getText());
            inputField.requestFocus();
        });
    }

    /**
     * Initializes the encoding selector.
     */
    public void changeFrom() {
        fromDecimalItem.setOnAction(event -> {
            converter.setFrom(Encoding.DECIMAL);
            fromSelector.setText(fromDecimalItem.getText());
            inputField.requestFocus();
            littleItem.fire();
            endianSelector.setDisable(true);
        });
        fromHexItem.setOnAction(event -> {
            converter.setFrom(Encoding.HEX);
            fromSelector.setText(fromHexItem.getText());
            endianSelector.setDisable(false);
            inputField.requestFocus();
        });
        fromBinaryItem.setOnAction(event -> {
            converter.setFrom(Encoding.BINARY);
            fromSelector.setText(fromBinaryItem.getText());
            endianSelector.setDisable(false);
            inputField.requestFocus();

        });
    }

    /**
     * Initializes the encoding selector.
     */
    public void changeTo() {
        toBinaryItem.setOnAction(event -> {
            converter.setTo(Encoding.BINARY);
            toSelector.setText(toBinaryItem.getText());
            inputField.requestFocus();
        });
        toDecimalItem.setOnAction(actionEvent -> {
            converter.setTo(Encoding.DECIMAL);
            toSelector.setText(toDecimalItem.getText());
            inputField.requestFocus();
        });
        toHexItem.setOnAction(actionEvent -> {
            converter.setTo(Encoding.HEX);
            toSelector.setText(toHexItem.getText());
            inputField.fireEvent(new ActionEvent());
            //inputField.requestFocus();
        });
    }

    /**
     * Change view selector.
     */
    public void changeView() {
        this.basicItem.setOnAction(event -> {
            this.app.setBasicScene();
        });
    }

    /**
     * Initialize evaluate eventhandler.
     */
    public void evaluate() {
        this.inputField.setOnKeyTyped(keyEvent -> {
            String s = inputField.getText() + keyEvent.getCharacter();
            if (s.isEmpty()) {
                resultField.setText("");
            } else {
                s = converter.convert(s);
                resultField.setText(s);
            }
        });
    }

    public void helpButton() {
        buttonHelp.setOnAction(event -> {
            showHelp();
        });
    }

    private void showHelp() {
        Dialog dia = new Dialog();
        dia.setTitle("Help");
        dia.setContentText("Input numbers you want to convert in the lower textbox.\n"
                + "Try pressing enter to re-evaluate if you're getting NaN.\n"
                + "Negative numbers are disabled due to java hijinks.");
        dia.show();
        dia.setHeight(150d);
        Window window = dia.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(event -> window.hide());
    }

}
