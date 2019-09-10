package org.solutione.santarita.api;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dfDay = new SimpleDateFormat("EEEE");
        ObservableList<Reporte> reportes = FXCollections.observableArrayList();

        for (int i = 0; i < 7; i++) {
            reportes.add(new Reporte(df.format(c.getTime()),dfDay.format(c.getTime()),0.0));
            c.add(Calendar.DATE, 1);
        }

        ObservableList<History> history =  new BDHistory().getHistory();

        double bigger = 0.0;
        for(Reporte r: reportes) {
            for (History h : history)
                if (h.getDate().equals(r.getDate()))
                    r.setMount(r.getMount() + h.getBenefit());

            if (bigger<r.getMount())
                bigger= r.getMount();
        }

        int value = (int) ((reportes.get(0).getMount()*500)/bigger);

        System.out.println(value);
    }
    private static Date convertDateProduct(String date_product){
        Date dateProduct = null;
        try {
            dateProduct=new SimpleDateFormat("dd/MM/yyyy").parse(date_product);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateProduct;
    }

}
