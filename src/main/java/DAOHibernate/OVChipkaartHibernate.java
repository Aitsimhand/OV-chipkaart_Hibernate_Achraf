package DAOHibernate;

import DAO.OVChipkaartDAO;
import Domein.OVChipkaart;
import Domein.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class OVChipkaartHibernate implements OVChipkaartDAO {
    Session session;

    @Override
    public boolean save(OVChipkaart ovChipkaart) {
        try {
            session.save(ovChipkaart);
            System.out.println("OVChipkaart save successful.");
            return true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            System.out.println("Failed to save() ovChipkaart");
            return false;
        }
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) {
        try {
            session.update(ovChipkaart);
            System.out.println("updateOvchipkaart() was successful");
            return true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            System.out.println( "updateOvchipkaart() was NOT successful");
            return false;
        }
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) {
        try {
            session.delete(ovChipkaart);
            System.out.println( "deleteOvchipkaart() was successful");
            return true;
        }

        catch (HibernateException e){
            e.printStackTrace();
            System.out.println( "deleteOvchipkaart() was NOT successful");
            return false;
        }
    }

    @Override
    public OVChipkaart findByReiziger(Reiziger reiziger) {
        OVChipkaart ovChipkaart = new OVChipkaart();
        try {
            ovChipkaart = session.get(OVChipkaart.class, reiziger.getId());
            System.out.println( "findByReizigerOvchipkaart() was successful");
            return ovChipkaart;
        }

        catch (HibernateException e){
            e.printStackTrace();
            System.out.println( "findByReizigerOvchipkaart() was NOT successful");
            return null;
        }
    }

    @Override
    public List<OVChipkaart> findAll() {
        Query<OVChipkaart> query = session.createQuery("FROM OVChipkaart ", OVChipkaart.class);
        return query.list();
    }
}
