package DAO;

import Domein.OVChipkaart;
import Domein.Reiziger;

import java.util.List;

public interface OVChipkaartDAO {
    boolean save(OVChipkaart ovChipkaart);
    boolean update(OVChipkaart ovChipkaart);
    boolean delete(OVChipkaart ovChipkaart);
    OVChipkaart findByReiziger(Reiziger reiziger);
    List<OVChipkaart> findAll();

}
