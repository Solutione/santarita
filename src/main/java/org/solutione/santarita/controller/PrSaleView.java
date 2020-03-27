package org.solutione.santarita.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.DatePicker; 
import javafx.stage.Stage;
import org.solutione.santarita.api.BDProductos;
import org.solutione.santarita.api.BDProveedores;
import org.solutione.santarita.api.Producto;
import java.util.*;

import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class PrSaleView {
   
    public ImageView btnCancelar;
    public ImageView btnGuardar;
    public Label lblTotalProductos;
    public Label lblTotalGanancias;
    public TableView<Producto> tProducts;
    public int count=0;

    public TableColumn <Producto, String> tcProductName;
    public TableColumn <Producto, Number> tcCosto;
    public TableColumn <Producto, Number> tcPrecio;
    public TableColumn <Producto, Number> tcGanancia;
    

    private Stage thisStage;
    private BorderPane principalPane;
    private String provider;
    private String date;
    private boolean edit = false;
    private ObservableList<Producto> products = FXCollections.observableArrayList();

    @FXML
    private DatePicker dateSale;

    public void initialize(){
       dateSale.setOnAction((ActionEvent event) -> {
            String date1=dateSale.getValue().toString();
            String year=date1.substring(0, 4);
            String month=date1.substring(5, 7);
            String day=date1.substring(8, 10);
            String date=day+"/"+month+"/"+year;
            System.out.println(date);

        if(date1!=null){
            if(count==0){
            ObservableList<Producto> prod = new BDProductos().getSaleProduct(date);
            for (Producto p:prod)
                products.add(p);
                    
            tcProductName.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
            tcCosto.setCellValueFactory(cellData -> cellData.getValue().costoProperty());
            tcPrecio.setCellValueFactory(cellData -> cellData.getValue().precioProperty());
            tcGanancia.setCellValueFactory(cellData -> cellData.getValue().beneficioProperty());
            tProducts.setItems(products);
            count++;
            String totalVentas=new BDProductos().getTotalSale(date);
            String totalGanancias=new BDProductos().getTotalBenefit(date);
            lblTotalGanancias.setText("Ventas: "+totalVentas+"  Ganancias: "+totalGanancias);
            String totalProductos=  new BDProductos().getProductsSale(date);
            lblTotalProductos.setText("Productos Vendidos: "+totalProductos);
            }else{
            tProducts.getItems().clear();
            ObservableList<Producto> prod = new BDProductos().getSaleProduct(date);
            for (Producto p:prod)
                products.add(p);

            tcProductName.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
            tcCosto.setCellValueFactory(cellData -> cellData.getValue().costoProperty());
            tcPrecio.setCellValueFactory(cellData -> cellData.getValue().precioProperty());
            tcGanancia.setCellValueFactory(cellData -> cellData.getValue().beneficioProperty());
            tProducts.setItems(products);
            String totalVentas=new BDProductos().getTotalSale(date);
            String totalGanancias=new BDProductos().getTotalBenefit(date);
            lblTotalGanancias.setText("Ventas: "+totalVentas+"  Ganancias: "+totalGanancias);
            String totalProductos=  new BDProductos().getProductsSale(date);
            lblTotalProductos.setText("Productos Vendidos: "+totalProductos);
            
            }
              
        }else{
            lblTotalGanancias.setText("Vendido total: 0.0");
            lblTotalProductos.setText("Productos Vendidos: 0.0");
        }
      


        });
    }


    void initData(Stage thisStage) {
        this.thisStage = thisStage;     
    }



    public void btnCancelarMC(MouseEvent mouseEvent) {
        thisStage.close();
    }

}
