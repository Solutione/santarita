package org.solutione.santarita.api;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Producto {
    private SimpleStringProperty codigo;
    private SimpleStringProperty nombre;
    private SimpleDoubleProperty costo = null;
    private SimpleDoubleProperty precio;
    private SimpleIntegerProperty unidades;
    private SimpleStringProperty marca = null;
    private SimpleStringProperty caducidad = null;
    private SimpleDoubleProperty subtotal = null;

    public Producto(String codigo,String nombre,double costo,double precio, int unidades,String marca,String caducidad){
        this.codigo = new SimpleStringProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
        this.costo = new SimpleDoubleProperty(costo);
        this.precio = new SimpleDoubleProperty(precio);
        this.unidades = new SimpleIntegerProperty(unidades);
        this.marca = new SimpleStringProperty(marca);
        this.caducidad = new SimpleStringProperty(caducidad);
    }
    public Producto(String codigo,String nombre,double precio, int unidades,double subtotal){
        this.codigo = new SimpleStringProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
        this.precio = new SimpleDoubleProperty(precio);
        this.unidades = new SimpleIntegerProperty(unidades);
        this.subtotal = new SimpleDoubleProperty(subtotal);
    }

    public String getCodigo() {
        return codigo.get();
    }

    public SimpleStringProperty codigoProperty() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }

    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public double getCosto() {
        return costo.get();
    }

    public SimpleDoubleProperty costoProperty() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo.set(costo);
    }

    public double getPrecio() {
        return precio.get();
    }

    public SimpleDoubleProperty precioProperty() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio.set(precio);
    }

    public int getUnidades() {
        return unidades.get();
    }

    public SimpleIntegerProperty unidadesProperty() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades.set(unidades);
    }

    public String getMarca() {
        return marca.get();
    }

    public SimpleStringProperty marcaProperty() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca.set(marca);
    }

    public String getCaducidad() {
        return caducidad.get();
    }

    public SimpleStringProperty caducidadProperty() {
        return caducidad;
    }

    public void setCaducidad(String caducidad) {
        this.caducidad.set(caducidad);
    }

    public double getSubtotal() {
        return subtotal.get();
    }

    public SimpleDoubleProperty subtotalProperty() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal.set(subtotal);
    }
}
