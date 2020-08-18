package org.solutione.santarita.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.solutione.santarita.api.BDData_F;
import java.math.BigDecimal;

public class PrFinanceRetirar {
    private Stage stage;
    public TextField tfRetiro;
    public Label lblBenefit;
    public Label lblTotal;
    public ComboBox cbDestino;

    @FXML
    void initialize(){
        tfRetiro.setPromptText(Double.toString(Principal.BENEFIT.get()));
        lblBenefit.setText(Double.toString(Principal.BENEFIT.get()));
        lblTotal.setText(Double.toString(Principal.TOTAL.get()));
    }
    public void initData(Stage stage) {
        this.stage = stage;
        cbDestino.getItems().addAll(
            "Opciones",
            "Total",
            "Ganancia",
            "Precio"  
        );
        cbDestino.setStyle("-fx-background-color: #a49582; -fx-text-fill: #3f0d16; -fx-font: 27px \"Lexend Exa Regular\";");
        
    } 

    public void onEnter(ActionEvent actionEvent) {
        Double total = Principal.TOTAL.get();
        Double precio = Principal.NOBENEFIT.get();
        Double ganancia = Principal.BENEFIT.get();
        Double retiro = Double.parseDouble(tfRetiro.getText());
        String origen=cbDestino.getSelectionModel().getSelectedItem().toString();
        if(origen=="Opciones"){
            
        }
        if(origen=="Total"){
            if (!tfRetiro.getText().isEmpty()){
                if(retiro <= total){
                    total=redondea(total-retiro);
                    if(retiro >= ganancia){
                        ganancia=0.0;
                        precio=total;
                    }else{
                        ganancia=redondea(ganancia - retiro);
                        precio=redondea(total -ganancia);     
                    }
                stage.close();
                }
            }
        }
        if(origen=="Ganancia"){
            if(!tfRetiro.getText().isEmpty()){
                if(retiro<=ganancia){
                    total=redondea(total-retiro);
                    ganancia=redondea(ganancia-retiro);
                    stage.close();
                }
            }
            
        }
        if(origen=="Precio"){
            if(!tfRetiro.getText().isEmpty()){
                if(retiro<=precio){
                    total=redondea(total-retiro);
                    precio=redondea(precio-retiro);
                    stage.close();
                }
            }
        }
        Principal.NOBENEFIT.set(precio);
        Principal.BENEFIT.set(ganancia);
        new BDData_F().updateBenefit(ganancia);
        Principal.TOTAL.set(total);
        new BDData_F().updateTotal(total);  
        
    }
    public Double redondea(Double num){
        BigDecimal res; 
        res = new BigDecimal(num).setScale(3, BigDecimal.ROUND_HALF_DOWN); 
        double resultado = res.doubleValue();
        return resultado;
    }
 
      
}
