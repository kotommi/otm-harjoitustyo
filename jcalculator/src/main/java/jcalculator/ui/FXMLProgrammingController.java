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
import javafx.scene.control.MenuItem;
import jcalculator.domain.conversion.Converter;

/**
 * FXML Controller class
 *
 * @author tomko
 */
public class FXMLProgrammingController implements Initializable {
    private Mainview app;
    private Converter converter;

    @FXML
    private MenuItem basicItem;


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
    }

    public void changeView() {
        this.basicItem.setOnAction(event -> {
            this.app.setBasicScene();
        });
    }

}
