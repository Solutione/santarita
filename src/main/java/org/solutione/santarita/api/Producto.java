package org.solutione.santarita.api;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Producto {
    private SimpleStringProperty codigo;
    private SimpleStringProperty nombre;
    private SimpleDoubleProperty costo = null;
    private SimpleDoubleProperty precio;
    private SimpleDoubleProperty unidades;
    private SimpleStringProperty marca = null;
    private SimpleStringProperty caducidad = null;
    private SimpleDoubleProperty subtotal = null;
    private SimpleDoubleProperty beneficio = null;

    public Producto(String codigo,String nombre,double costo,double precio, double unidades,String marca,String caducidad){
        this.codigo = new SimpleStringProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
        this.costo = new SimpleDoubleProperty(costo);
        this.precio = new SimpleDoubleProperty(precio);
        this.unidades = new SimpleDoubleProperty(unidades);
        this.marca = new SimpleStringProperty(marca);
        this.caducidad = new SimpleStringProperty(caducidad);

        this.subtotal = new SimpleDoubleProperty(0);
    }
    public Producto(String codigo,String nombre,double precio, double unidades,double subtotal, double costo){
        this.codigo = new SimpleStringProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
        this.precio = new SimpleDoubleProperty(precio);
        this.unidades = new SimpleDoubleProperty(unidades);
        this.subtotal = new SimpleDoubleProperty(subtotal);
        this.costo = new SimpleDoubleProperty(costo);
    }
    public Producto(String codigo,String nombre, double unidades,String marca){
        this.codigo = new SimpleStringProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
        this.unidades = new SimpleDoubleProperty(unidades);
        this.marca = new SimpleStringProperty(marca);

        this.subtotal = new SimpleDoubleProperty(0);
    }
    public Producto(String codigo,String nombre){
        this.codigo = new SimpleStringProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);

        this.subtotal = new SimpleDoubleProperty(0);
    }
    public Producto(String nombre,double unidades,double beneficio){
        this.nombre = new SimpleStringProperty(nombre);
        this.unidades = new SimpleDoubleProperty(unidades);
        this.beneficio = new SimpleDoubleProperty(beneficio);

        this.subtotal = new SimpleDoubleProperty(0);
    }
    public Producto(String nombre,double costo,double precio,double beneficio){
        this.nombre = new SimpleStringProperty(nombre);
        this.costo = new SimpleDoubleProperty(costo);
        this.beneficio = new SimpleDoubleProperty(beneficio);
        this.precio = new SimpleDoubleProperty(precio);

        this.subtotal = new SimpleDoubleProperty(0);
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

    public double getUnidades() {
        return unidades.get();
    }

    public SimpleDoubleProperty unidadesProperty() {
        return unidades;
    }

    public void setUnidades(double unidades) {
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
    public double getBeneficio() {
        return beneficio.get();
    }

    public SimpleDoubleProperty beneficioProperty() {
        return beneficio;
    }

    public void setBeneficio(double beneficio) {
        this.beneficio.set(beneficio);
    }

    public void setAll(String code, String name, double cost, double price, double units, String brand, String expiration) {
        this.codigo = new SimpleStringProperty(code);
        this.nombre = new SimpleStringProperty(name);
        this.costo = new SimpleDoubleProperty(cost);
        this.precio = new SimpleDoubleProperty(price);
        this.unidades = new SimpleDoubleProperty(units);
        this.marca = new SimpleStringProperty(brand);
        this.caducidad = new SimpleStringProperty(expiration);
    }
}
