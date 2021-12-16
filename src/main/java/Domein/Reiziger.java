package Domein;

import javax.persistence.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;


@Entity(name = "Reiziger")
@Table(name = "reiziger")

public class Reiziger {
    @Id
    @Column
    int reiziger_id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;

    @OneToOne(mappedBy = "reiziger")
    private Adres adres;
    @OneToMany(mappedBy = "reiziger")
    private List<OVChipkaart> OVChipkaarten;



    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.reiziger_id = id;
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
        return reiziger_id;
    }

    public void setId(int id) {
        this.reiziger_id = id;
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

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(String geboortedatum) {
        this.geboortedatum = Date.valueOf(geboortedatum);
    }

    @Override
    public String toString() {
        return "De gegevens van deze reiziger" + "\n"
                + "Naam: " + voorletters + " " + tussenvoegsel + " " + achternaam + "  " + geboortedatum;
    }
}
