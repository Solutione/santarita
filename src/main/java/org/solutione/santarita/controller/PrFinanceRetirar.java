package org.solutione.santarita.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.solutione.santarita.api.BDData_F;

public class PrFinanceRetirar {
    private Stage stage;
    public TextField tfRetiro;
    public Label lblBenefit;
    public Label lblTotal;

    @FXML
    void initialize(){
        tfRetiro.setPromptText(Double.toString(Principal.BENEFIT.get()));
        lblBenefit.setText(Double.toString(Principal.BENEFIT.get()));
        lblTotal.setText(Double.toString(Principal.TOTAL.get()));
    }
    public void initData(Stage stage) {
        this.stage = stage;
    }

    public void onEnter(ActionEvent actionEvent) {
        if (!tfRetiro.getText().isEmpty()){
            if (Double.parseDouble(tfRetiro.getText()) <= Principal.TOTAL.get()){
                Principal.TOTAL.set(Principal.TOTAL.get()-Double.parseDouble(tfRetiro.getText()));
                if (Double.parseDouble(tfRetiro.getText()) >= Principal.BENEFIT.get()){
                    Principal.BENEFIT.set(0);
                    Principal.NOBENEFIT.set(Principal.TOTAL.get());
                }
                else{
                    Principal.BENEFIT.set(Principal.BENEFIT.get() - Double.parseDouble(tfRetiro.getText()));
                    Principal.NOBENEFIT.set(Principal.TOTAL.get() - Principal.BENEFIT.get());
                }
            }
            new BDData_F().updateTotal(Principal.TOTAL.get());
            new BDData_F().updateBenefit(Principal.BENEFIT.get());
            stage.close();
        }
    }
}
