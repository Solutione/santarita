package org.solutione.santarita.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.solutione.santarita.api.BDProductos;
import org.solutione.santarita.api.BDProveedores;
import org.solutione.santarita.api.Producto;
import org.solutione.santarita.api.Proveedor;
import java.util.*; 

import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PrProviderView {
   
    public ImageView btnCancelar;
    public ImageView btnGuardar;
    public Label lblDate;
    public Label lblTotalProductos;
    public Label lblTotalGanancias;
    public TableView<Producto> tProducts;
    public TableColumn <Producto, String> tcProductName;
    public TableColumn <Producto, Number> tcCantidad;
    public TableColumn <Producto, Number> tcGanancia;
    

    private Stage thisStage;
    private BorderPane principalPane;
    private String provider;
    private String date;
    private boolean edit = false;
    private ObservableList<Producto> products = FXCollections.observableArrayList();
    private ObservableList<Producto> noproducts = FXCollections.observableArrayList();

    @FXML
    void initialize(){
       
    }


    void initData(BorderPane principalPane, Stage thisStage, String provider) {
        Calendar fecha = new GregorianCalendar();
        this.thisStage = thisStage;
        this.principalPane =  principalPane;
        this.provider = provider;
        int year = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String date1=new BDProveedores().getDateVisit(provider);

        if(date1!=null){
            lblDate.setText("Del "+date1+" al "+dia + "/" + (mes+1) + "/" + year);
            ObservableList<Producto> prod = new BDProveedores().getViewVisit(date1,provider);
            for (Producto p:prod)
                products.add(p);
                    
            tcProductName.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
            tcCantidad.setCellValueFactory(cellData -> cellData.getValue().unidadesProperty());
            tcGanancia.setCellValueFactory(cellData -> cellData.getValue().beneficioProperty());
   
            String total=new BDProveedores().getTotalVisit(date1,provider);
            lblTotalGanancias.setText("Costo total: "+total);
            String totalP=new BDProveedores().getTotalProducts(date1,provider);
            lblTotalProductos.setText("Productos Vendidos: "+totalP);
            tProducts.setItems(products);
        }else{
            lblDate.setText("Fecha de Visita del proveedor "+provider+" no disponible");
            lblTotalGanancias.setText("Vendido total: 0.0");
            lblTotalProductos.setText("Productos Vendidos: 0.0");
        }
        
    


    }

    public void btnGuardarMC(MouseEvent mouseEvent) {
        thisStage.close();
    }

    public void btnCancelarMC(MouseEvent mouseEvent) {
        thisStage.close();
    }

}
