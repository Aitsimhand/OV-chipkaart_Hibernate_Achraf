package Domein;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Product {
    @Id
    @Column(name = "product_nummer")
    private int product_nummer;
    private String naam;
    private String beschrijving;
    private double prijs;

    @ManyToMany
    @JoinTable(name = "ov_chipkaart_product", joinColumns = {@JoinColumn(name = "product_nummer")}, inverseJoinColumns = {@JoinColumn(name = "kaart_nummer")})
    private List<OVChipkaart> OVChipkaarten = new ArrayList<>();


    public Product(int product_nummer, String naam, String beshrijving, double prijs) {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beshrijving;
        this.prijs = prijs;
    }

    public Product() {
        product_nummer = 0;
        naam = "";
        beschrijving = "";
        prijs = 0;

    }

    public void setOVChipkaarten(OVChipkaart ovChipkaart) {
        this.OVChipkaarten.add(ovChipkaart);
    }

    public void removeOVchipkaart(OVChipkaart ovChipkaart) {
        this.getOVChipkaarten().remove(ovChipkaart);
    }

    public List<OVChipkaart> getOVChipkaarten() {
        return OVChipkaarten;
    }

    public int getProduct_nummer() {
        return product_nummer;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeshrijving() {
        return beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setProduct_nummer(int product_nummer) {
        this.product_nummer = product_nummer;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setBeshrijving(String beshrijving) {
        this.beschrijving = beshrijving;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    @Override
    public String toString() {
        return "Product informatie:" + "\n" +
                "Product nummer: " +
                product_nummer +
                "\nNaam: " + naam +
                "\nBeschrijving: " +
                beschrijving + "\nPrijs: " +
                prijs +
                "\nGekoppeld aan de volgende oVChipkaarten: " +
                OVChipkaarten;
        }
    }
