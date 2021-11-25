package Domein;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reiziger {
    @Id
    @Column(name = "reiziger_id")
    int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private String geboortedatum;

    @OneToOne
    @JoinColumn(name = "reiziger_id")
    private Adres adres;
    @OneToMany(mappedBy = "reiziger")
    private List<OVChipkaart> OVChipkaarten = new ArrayList<>();



    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, String geboortedatum) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public void setOVChipkaarten(OVChipkaart ovChipkaart) {
        this.OVChipkaarten.add(ovChipkaart);
    }

    public void removeOVChipkaart(OVChipkaart ovChipkaart){
        this.OVChipkaarten.remove(ovChipkaart);
    }

    public List<OVChipkaart> getOvChipkaarten() {
        return OVChipkaarten;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public Adres getAdres() {
        return adres;
    }

    public Reiziger() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(String geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    @Override
    public String toString() {
        return "De gegevens van deze reiziger" + "\n"
                + "Naam: " + voorletters + " " + tussenvoegsel + " " + achternaam + "  " + geboortedatum;
    }
}
