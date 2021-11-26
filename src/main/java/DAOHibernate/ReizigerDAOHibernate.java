package DAOHibernate;

import DAO.ReizigerDAO;
import Domein.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO {
    Session session;
    public ReizigerDAOHibernate() {

    }

    @Override
    public boolean save(Reiziger reiziger) {
        try {
            session.save(reiziger);
            System.out.println("Reiziger save successful.");
            return true;
        }

        catch (HibernateException e){
            e.printStackTrace();
            System.out.println("Reiziger save NOT successful.");
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            session.update(reiziger);
            System.out.println("Reiziger update successful.")
            return true;

        }

        catch (HibernateException e){
            e.printStackTrace();
            System.out.println("Reizger update NOT successful.");
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            session.delete(reiziger);
            System.out.println("Reiziger delete successful.");
            return true;
        }

        catch (HibernateException e){
            e.printStackTrace();
            System.out.println("Reiziger delete NOT successful.");
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) {
        try {
            Query query = session.createQuery(Reiziger.class, );
        }
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        return null;
    }

    @Override
    public List<Reiziger> findAll() {
        return null;
    }
}
