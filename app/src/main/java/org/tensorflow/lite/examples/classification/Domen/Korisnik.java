package org.tensorflow.lite.examples.classification.Domen;

import java.util.ArrayList;
import java.util.List;

public class Korisnik {
    String nivo;
    int brTacnih;
    int osvojenBrPoena;
    int ukBrPitanja;
    int ukBrPoena;
    List<Pitanje> listaPitanja;
    List<String> listaTacnihOdgovora;
    List<String> listaOdgovoraKorisnika;

    public Korisnik() {
        listaPitanja = new ArrayList<>();
        listaTacnihOdgovora = new ArrayList<>();
        listaOdgovoraKorisnika = new ArrayList<>();
    }

    public Korisnik(String nivo, int brTacnih, int osvojenBrPoena, int ukBrPitanja, int ukBrPoena, List<Pitanje> listaPitanja, List<String> listaTacnihOdgovora, List<String> listaOdgovoraKorisnika) {
        this.nivo = nivo;
        this.brTacnih = brTacnih;
        this.osvojenBrPoena = osvojenBrPoena;
        this.ukBrPitanja = ukBrPitanja;
        this.ukBrPoena = ukBrPoena;
        this.listaPitanja = listaPitanja;
        this.listaTacnihOdgovora = listaTacnihOdgovora;
        this.listaOdgovoraKorisnika = listaOdgovoraKorisnika;
    }

    public String getNivo() {
        return nivo;
    }

    public void setNivo(String nivo) {
        this.nivo = nivo;
    }

    public int getBrTacnih() {
        return brTacnih;
    }

    public void setBrTacnih(int brTacnih) {
        this.brTacnih = brTacnih;
    }

    public int getOsvojenBrPoena() {
        return osvojenBrPoena;
    }

    public void setOsvojenBrPoena(int osvojenBrPoena) {
        this.osvojenBrPoena = osvojenBrPoena;
    }

    public int getUkBrPitanja() {
        return ukBrPitanja;
    }

    public void setUkBrPitanja(int ukBrPitanja) {
        this.ukBrPitanja = ukBrPitanja;
    }

    public int getUkBrPoena() {
        return ukBrPoena;
    }

    public void setUkBrPoena(int ukBrPoena) {
        this.ukBrPoena = ukBrPoena;
    }

    public List<Pitanje> getListaPitanja() {
        return listaPitanja;
    }

    public void setListaPitanja(List<Pitanje> listaPitanja) {
        this.listaPitanja = listaPitanja;
    }

    public List<String> getListaTacnihOdgovora() {
        return listaTacnihOdgovora;
    }

    public void setListaTacnihOdgovora(List<String> listaTacnihOdgovora) {
        this.listaTacnihOdgovora = listaTacnihOdgovora;
    }

    public List<String> getListaOdgovoraKorisnika() {
        return listaOdgovoraKorisnika;
    }

    public void setListaOdgovoraKorisnika(List<String> listaOdgovoraKorisnika) {
        this.listaOdgovoraKorisnika = listaOdgovoraKorisnika;
    }

    public void izracunajUkBrPitanja(){
        ukBrPitanja = listaPitanja.size();
    }

    public void izracunajUkBrPoena(){
        for(int i = 0; i < listaTacnihOdgovora.size(); i++){
            if(listaOdgovoraKorisnika.get(i).equals(listaTacnihOdgovora.get(i))){
                brTacnih++;
            }
        }
        switch (nivo) {
            case "lak":
                osvojenBrPoena = brTacnih;
                ukBrPoena = ukBrPitanja;
                break;
            case "srednji":
                osvojenBrPoena = brTacnih * 2;
                ukBrPoena = ukBrPitanja * 2;
                break;
            case "težak":
                osvojenBrPoena = brTacnih * 3;
                ukBrPoena = ukBrPitanja * 3;
                break;
        }
    }
}
