package org.solutione.santarita.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.solutione.santarita.api.BDProductos;
import org.solutione.santarita.api.BDProveedores;
import org.solutione.santarita.api.Producto;

public class PrProviderAddProvider {
    public ListView<String> lvMarcas;
    public ListView<String> lvProductos;
    public ImageView btnCancelar;
    public ImageView btnGuardar;
    public ImageView imgLogo;
    public TextField tfNombre;

    private ObservableList<String> marcas = FXCollections.observableArrayList();
    private ObservableList<String> productos = FXCollections.observableArrayList();

    private Stage thisStage;

    @FXML
    void initialize(){
        ObservableList<Producto> bdProductos = new BDProductos().getProducts();
        for (Producto producto : bdProductos) {
            if (!marcas.isEmpty()){
                int m =  marcas.size();
                for(int i= 0; i<m;i++)
                    if (!marcas.contains(producto.getMarca()))marcas.add(producto.getMarca());
            }else {
                marcas.add(producto.getMarca());
            }
        }
        lvMarcas.setItems(marcas);
        lvMarcas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            String pr = lvMarcas.getSelectionModel().getSelectedItem();
            productos.clear();
            for (Producto producto : bdProductos)
                if (producto.getMarca().equals(pr))productos.add(producto.getNombre());

            lvProductos.setItems(productos);
            lvProductos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        });
        lvProductos.setCellFactory(alv -> {
            ListCell<String> cell = new ListCell<>();
            cell.textProperty().bind(cell.itemProperty());
            cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                lvProductos.requestFocus();
                if(!cell.isEmpty()){
                    int index = cell.getIndex();
                    if (lvProductos.getSelectionModel().getSelectedIndices().contains(index)){
                        lvProductos.getSelectionModel().clearSelection(index);
                    }else {
                        lvProductos.getSelectionModel().select(index);
                    }
                    event.consume();
                }
            });
            return cell;
        });
    }

    void initData(Stage thisStage) {
        this.thisStage = thisStage;
    }

    public void btnGuardarMC(MouseEvent mouseEvent) {
        ObservableList <String> prod = lvProductos.getSelectionModel().getSelectedItems();
        StringBuilder cds = new StringBuilder();
        for (String prName : prod) {
            if (cds.toString().equals("")) {
                cds = new StringBuilder(new BDProductos().getCode(prName));
            }else {
                cds.append(",").append(new BDProductos().getCode(prName));
            }
        }
        String brand = lvMarcas.getSelectionModel().getSelectedItem();
        if (!tfNombre.getText().equals("")){
            new BDProveedores().addProvider(tfNombre.getText(),brand,cds.toString(),"");
            thisStage.close();
        }
    }

    public void btnCancelarMC(MouseEvent mouseEvent) {
        thisStage.close();
    }
}
