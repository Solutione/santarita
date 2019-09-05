package org.solutione.santarita.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.solutione.santarita.api.BDData_F;

public class PrFinance {
    public BorderPane BPFincance;
    public Label tfNoBenefit;
    public Label tfBenefit;

    @FXML
    void initialize(){
        double benefit = new BDData_F().getBenefit();
        double noBenefit = new BDData_F().getTotal() - benefit;
        tfBenefit.setText("$"+benefit);
        tfNoBenefit.setText("$"+noBenefit);
    }
    public void initData(BorderPane bpPrincipal) {
    }
}
