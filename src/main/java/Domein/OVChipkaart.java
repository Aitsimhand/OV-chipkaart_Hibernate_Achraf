package Domein;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ov_chipkaart")
public class OVChipkaart {
    @Id
    private int kaart_nummer;
    private Date geldig_tot;
    private int klasse;
    private Double saldo;

    @ManyToOne()
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;
    @ManyToMany(mappedBy = "OVChipkaarten")
    private List<Product> products = new ArrayList<>();



    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public OVChipkaart(int kaart_nummer, Date geldig_tot, int klasse, Double saldo, Reiziger reiziger) {
        this.kaart_nummer = kaart_nummer;
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
    }

    public OVChipkaart() {

    }

    public List<Product> getProducten() {
        return products;
    }

    public void setProducten(Product product) {
        this.products.add(product);
    }

    public int getKaart_nummer() {
        return kaart_nummer;
    }

    public void setKaart_nummer(int kaartNummer) {
        this.kaart_nummer = kaartNummer;
    }

    public Date getGeldig_tot() {
        return geldig_tot;
    }

    public void setGeldig_tot(Date geldigTot) {
        this.geldig_tot = geldigTot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public int getReizigerId() {
        return reiziger.getId();
    }

//TODO!! Still have to make this code return values of other classes if needed. Look at the other
    @Override
    public String toString() {


        return "OVChipkaart{" +
                "kaartNummer=" + kaart_nummer +
                ", geldigTot='" + geldig_tot + '\'' +
                ", klasse=" + klasse +
                ", saldo=" + saldo +
                ", reizigerId=" + reiziger.getId() +
                '}';
    }
}
