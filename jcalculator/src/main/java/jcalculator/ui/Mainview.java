package jcalculator.ui;

import com.fathzer.soft.javaluator.DoubleEvaluator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jcalculator.database.Database;
import jcalculator.database.ScrollbackDao;
import jcalculator.domain.conversion.Converter;

/**
 * @author tomko
 */
public class Mainview extends Application {

    private DoubleEvaluator eval;
    private Stage stage;
    private Scene basicview;
    private ScrollbackDao sbdao;
    private FXMLBasicViewController bvc;
    private Scene programmingview;
    private FXMLProgrammingController pvc;
    private Converter converter;

    @Override
    public void init() throws Exception {
        eval = new DoubleEvaluator();
        String dbName = "scrollback.db";
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));
            dbName = properties.getProperty("dbFile");
            if (!dbName.contains(".db")) {
                dbName += ".db";
                properties.setProperty("dbFile", dbName);
            }
        } catch (Exception e) {
        }
        sbdao = new ScrollbackDao(new Database(dbName));
        createBasicScene();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        setBasicScene();
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
        basic.getStylesheets().add("/styles/fonts.css");
        basicview = new Scene(basic);
    }

    public void setBasicScene() {
        stage.setResizable(true);
        stage.setTitle("jcalculator");
        stage.setScene(basicview);
        stage.setOnShowing(e -> bvc.setScrollback());
        stage.setOnHidden(e -> bvc.shutdown());
        bvc.changeView();
        stage.show();
        stage.setResizable(false);
    }

    public void setProgrammingScene() {
        if (programmingview == null) {
            createProgrammingScene();
        }
        stage.setResizable(true);
        stage.setScene(programmingview);
        stage.setResizable(false);
    }

    private void createProgrammingScene() {
        FXMLLoader programmingViewLoader = new FXMLLoader();
        programmingViewLoader.setLocation(this.getClass().getResource("/programming.fxml"));
        Parent programming = null;
        try {
            programming = programmingViewLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(Mainview.class.getName()).log(Level.SEVERE, null, ex);
        }
        pvc = programmingViewLoader.getController();
        pvc.setApp(this);
        this.converter = new Converter();
        pvc.setConverter(converter);
        programming.getStylesheets().add("/styles/fonts.css");
        programmingview = new Scene(programming);
    }

    public static void main(String[] args) {
        launch(Mainview.class);
    }

}
