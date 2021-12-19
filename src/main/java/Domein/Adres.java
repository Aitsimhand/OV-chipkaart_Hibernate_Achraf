package Domein;

import javax.persistence.*;

@Entity
@Table(name = "adres")
public class Adres {
    @Id
    @Column(name = "adres_id")
    private int id;
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;

    @OneToOne(cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;


    public Adres() {

    }

    public Adres(int id, String postcode, String huisnummer, String straat, String woonplaats, Reiziger reiziger) {
        if (id > 0) this.id = id;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat = straat;
        this.woonplaats = woonplaats;
        this.reiziger = reiziger;
        reiziger.setAdres(this);
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public String getStraat() {
        return straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }


    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public void setReiziger(Reiziger reiziger){
        this.reiziger = reiziger;
    }

    public int getReiziger_id() {
        return reiziger.reiziger_id;
    }


    @Override
    public String toString() {

        if (reiziger != null)
        return "ID:" + id + " POSTCODE:" + postcode + " HUISNUMMER:" + huisnummer + " STRAAT:" + straat + "WOONPLAATS:" + woonplaats + "\n" + reiziger ;

        else
            return "ID:" + id + " POSTCODE:" + postcode + " HUISNUMMER:" + huisnummer + " STRAAT:" + straat + "WOONPLAATS:" + woonplaats;
    }
}
