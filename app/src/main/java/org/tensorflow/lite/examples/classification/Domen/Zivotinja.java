package org.tensorflow.lite.examples.classification.Domen;

public class Zivotinja {
    String naziv;
    String slika;
    String porodica;
    String zivotniVek;
    String staniste;
    String rasprostranjenost;
    String tekst;
    String zanimljivost;

    public Zivotinja() {
    }

    public Zivotinja(String naziv, String slika, String porodica, String zivotniVek, String staniste, String rasprostranjenost, String tekst, String zanimljivost) {
        this.naziv = naziv;
        this.slika = slika;
        this.porodica = porodica;
        this.zivotniVek = zivotniVek;
        this.staniste = staniste;
        this.rasprostranjenost = rasprostranjenost;
        this.tekst = tekst;
        this.zanimljivost = zanimljivost;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getPorodica() {
        return porodica;
    }

    public void setPorodica(String porodica) {
        this.porodica = porodica;
    }

    public String getZivotniVek() {
        return zivotniVek;
    }

    public void setZivotniVek(String zivotniVek) {
        this.zivotniVek = zivotniVek;
    }

    public String getStaniste() {
        return staniste;
    }

    public void setStaniste(String staniste) {
        this.staniste = staniste;
    }

    public String getRasprostranjenost() {
        return rasprostranjenost;
    }

    public void setRasprostranjenost(String rasprostranjenost) {
        this.rasprostranjenost = rasprostranjenost;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getZanimljivost() {
        return zanimljivost;
    }

    public void setZanimljivost(String zanimljivost) {
        this.zanimljivost = zanimljivost;
    }

    @Override
    public String toString() {
        return "Zivotinja{" +
                "naziv='" + naziv + '\'' +
                '}';
    }
}
