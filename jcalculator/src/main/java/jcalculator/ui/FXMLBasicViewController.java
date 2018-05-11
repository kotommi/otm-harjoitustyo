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
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Window;
import jcalculator.database.ScrollbackDao;
import jcalculator.util.DoubleUtil;

/**
 * FXML Controller class. Basic calculator view.
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
    private Label errorLabel;
    
    @FXML
    private MenuItem basicItem;
    @FXML
    private MenuItem programmingItem;
    
    @FXML
    private Button buttonEvaluate;
    @FXML
    private Button buttonHelp;
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
    @FXML
    private Button buttonSqrt;
    @FXML
    private Button buttonClear;
    
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
        errorLabel.setVisible(false);
    }

    /**
     * Help window popup.
     */
    public void helpDialog() {
        Dialog dia = new Dialog();
        dia.setTitle("Help");
        dia.setContentText("ans = previous answer\n"
                + "constants:\n"
                + "pi\n"
                + "e\n"
                + "ex.: pi+e = 5.859874\n"
                + "\n"
                + "functions:\n"
                + "abs: abs(-5) = 5\n"
                + "acos: acos(.5)	 = 1.047198\n"
                + "asin: asin(1)	 = 1.570796\n"
                + "atan: atan(.1)	 = 0.099669\n"
                + "avg: avg(1,2,3)	 = 2 note the commas\n"
                + "ceil: ceil(1.3)	 = 2\n"
                + "cos: cos(90)	 = -0.448074\n"
                + "floor: floor(99.99999)	 = 99\n"
                + "ln: ln(e)	 = 1\n"
                + "log: log(10)	 = 1 base10\n"
                + "max: max(4,2)	 = 4\n"
                + "min: min(4,2)	 = 2\n"
                + "random: random()	 = 0.572119 between 0-1\n"
                + "round: round(1.2)	 = 1\n"
                + "sin: sin(42)	 = -0.916522\n"
                + "sum: sum(1,2,3)	 = 6\n"
                + "tan: tan(17)	 = 3.493916");
        dia.show();
        Window window = dia.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(event -> window.hide());
    }

    /**
     * Change current view.
     */
    public void changeView() {
        programmingItem.setOnAction(event -> {
            app.setProgrammingScene();
        });
        basicItem.setOnAction(event -> {
            app.setBasicScene();
        });
    }

    /**
     * Set eventhandlers.
     */
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
        buttonSqrt.setOnAction(event -> {
            insertCharacter("^(1/2)");
        });
        buttonClear.setOnAction(event -> {
            inputField.clear();
        });
        buttonHelp.setOnAction(event -> {
            helpDialog();
        });
    }

    /**
     * Insert character to inputField.
     *
     * @param c a character to insert.
     */
    public void insertCharacter(String c) {
        inputField.appendText(c);
    }

    /**
     * Save scrollback to database. Called on shutdown.
     */
    public void shutdown() {
        List<String> lines = new ArrayList();
        String s = resultArea.getText();
        String[] split = s.split("\n");
        lines.addAll(Arrays.asList(split));
        sbdao.clearAndSave(lines);
        Platform.exit();
    }

    /**
     * Loads scrollback.
     */
    public void setScrollback() {
        resultArea.setText(sbdao.getScrollback());
    }

    /**
     * Set the evaluate eventhandler.
     */
    public void evaluate() {
        buttonEvaluate.setOnAction(event -> {
            errorLabel.setVisible(false);
            try {
                String input = inputField.getText().toLowerCase();
                if (input.contains("ans")) {
                    input = input.replaceAll("ans", Double.toString(ans));
                }
                double d = de.evaluate(input);
                String ds = DoubleUtil.toString(d);
                ans = Double.parseDouble(ds);
                resultArea.appendText("\n" + inputField.getText() + "\t = " + ds);
                inputField.clear();
            } catch (IllegalArgumentException e) {
                errorLabel.setVisible(true);
            }
        });
        
        inputField.setOnKeyPressed(event -> {
            if (errorLabel.visibleProperty().get()) {
                errorLabel.setVisible(false);
            }
            if (event.getCode() == KeyCode.ENTER) {
                buttonEvaluate.fire();
            }
        });
        
    }
    
}
