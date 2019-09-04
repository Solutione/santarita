package org.solutione.santarita.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.solutione.santarita.api.Producto;

import java.io.IOException;

public class PrSaleFinish {
    public BorderPane BPPrSaleFinish;
    public TextField tfMoney;
    public Label lblExchange;
    public ImageView imgBtnFinish;
    private Stage stg;
    private BorderPane bpPrincipal;
    private double total;
    private ObservableList<Producto> oblProductos;

    @FXML
    void initialize() {}

    void initData(ObservableList<Producto> oblProductos,BorderPane bpPrincipal, Stage stg, String total) {
        this.total = Double.parseDouble(total);
        tfMoney.setText(total);
        this.stg = stg;
        this.bpPrincipal = bpPrincipal;
        this.oblProductos = oblProductos;
    }

    public void changeExchange(KeyEvent keyEvent) {
        double mon = 0;
        if(!tfMoney.getText().equals(""))
            mon = Double.parseDouble(tfMoney.getText());
        double ex = mon - total;
        lblExchange.setText(Double.toString(ex));
    }

    public void btnFinishMouseClick(MouseEvent mouseEvent) {
        panelConfirm();
    }
    public void onEnter(ActionEvent ae){
        panelConfirm();
    }

    private void panelConfirm(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/solutione/santarita/view/PrSaleFinishConfirm.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(new Scene((Pane) loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrSaleFinishConfirm controller = loader.<PrSaleFinishConfirm>getController();
        controller.initData(oblProductos,bpPrincipal,stage,stg);

        stage.show();
        stage.setAlwaysOnTop(true);
    }

}
