package org.solutione.santarita.api;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.solutione.santarita.controller.Search;

import java.io.IOException;

public class test {
    public static void main(String[] args) {
        FXMLLoader loader = new FXMLLoader(test.class.getResource("/org/solutione/santarita/view/Search.fxml"));//getClass()
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(new Scene((Pane) loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Search controller = loader.<Search>getController();
        controller.initData(stage);

        stage.show();
        stage.setAlwaysOnTop(true);
        stage.toFront();
    }

}
