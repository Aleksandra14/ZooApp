package org.tensorflow.lite.examples.classification.Domen;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class Pitanje {
    int rbr;
    String naziv;
    List<String> odgovori;
    String tacanOdg;
    String nivo;

    public Pitanje() {
        odgovori = new ArrayList<String>();
    }

    public Pitanje(int rbr, String naziv, List<String> odgovori, String tacanOdg, String nivo) {
        this.rbr = rbr;
        this.naziv = naziv;
        this.odgovori = odgovori;
        this.tacanOdg = tacanOdg;
        this.nivo = nivo;
    }

    public int getRbr() {
        return rbr;
    }

    public void setRbr(int rbr) {
        this.rbr = rbr;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<String> getOdgovori() {
        return odgovori;
    }

    public void setOdgovori(List<String> odgovori) {
        this.odgovori = odgovori;
    }

    public String getTacanOdg() {
        return tacanOdg;
    }

    public void setTacanOdg(String tacanOdg) {
        this.tacanOdg = tacanOdg;
    }

    public String getNivo() {
        return nivo;
    }

    public void setNivo(String nivo) {
        this.nivo = nivo;
    }

    @Override
    public String toString() {
        return "Pitanje{" +
                "naziv='" + naziv + '\'' +
                '}';
    }
}
