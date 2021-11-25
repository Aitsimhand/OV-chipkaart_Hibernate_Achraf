package DAO;

import Domein.Adres;
import Domein.Reiziger;

import java.util.ArrayList;

public interface AdresDAO {
    boolean save(Adres adres);
    boolean update(Adres adres);
    boolean delete(Adres adres);
    Adres findByReiziger(Reiziger reiziger);
    ArrayList<Adres> findAll();

}
