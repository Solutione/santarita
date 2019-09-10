package org.solutione.santarita.api;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Reporte {
    private SimpleStringProperty date;
    private SimpleStringProperty day;
    private SimpleDoubleProperty mount;

    public Reporte(String date, String day, double mount){
        this.date = new SimpleStringProperty(date);
        this.day = new SimpleStringProperty(day);
        this.mount = new SimpleDoubleProperty(mount);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getDay() {
        return day.get();
    }

    public SimpleStringProperty dayProperty() {
        return day;
    }

    public void setDay(String day) {
        this.day.set(day);
    }

    public double getMount() {
        return mount.get();
    }

    public SimpleDoubleProperty mountProperty() {
        return mount;
    }

    public void setMount(double mount) {
        this.mount.set(mount);
    }
}
