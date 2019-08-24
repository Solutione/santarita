package org.solutione.santarita.api;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Producto {
    private final SimpleStringProperty codigo;
    private final SimpleStringProperty nombre;
    private final SimpleIntegerProperty unidades;
    private final SimpleDoubleProperty costo;
    private final SimpleDoubleProperty precio;
    private final SimpleStringProperty marca;
    private final SimpleStringProperty caducidad;

    public Producto(String codigo,String nombre,int unidades,double costo,double precio,String marca,String caducidad){
        this.codigo = new SimpleStringProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
        this.unidades = new SimpleIntegerProperty(unidades);
        this.costo = new SimpleDoubleProperty(costo);
        this.precio = new SimpleDoubleProperty(precio);
        this.marca = new SimpleStringProperty(marca);
        this.caducidad = new SimpleStringProperty(caducidad);
    }

    public String getCodigo() {
        return codigo.get();
    }

    public SimpleStringProperty codigoProperty() {
        return codigo;
    }

    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public int getUnidades() {
        return unidades.get();
    }

    public SimpleIntegerProperty unidadesProperty() {
        return unidades;
    }

    public double getCosto() {
        return costo.get();
    }

    public SimpleDoubleProperty costoProperty() {
        return costo;
    }

    public double getPrecio() {
        return precio.get();
    }

    public SimpleDoubleProperty precioProperty() {
        return precio;
    }

    public String getMarca() {
        return marca.get();
    }

    public SimpleStringProperty marcaProperty() {
        return marca;
    }

    public String getCaducidad() {
        return caducidad.get();
    }

    public SimpleStringProperty caducidadProperty() {
        return caducidad;
    }
}
