package DAOHibernate;

import DAO.ReizigerDAO;
import Domein.Reiziger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO {
    private final Session session;

    public ReizigerDAOHibernate(Session session) {
    this.session = session;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        try {
            session.beginTransaction();
            session.saveOrUpdate(reiziger);
            session.getTransaction().commit();
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
            session.beginTransaction();
            session.update(reiziger);
            session.getTransaction().commit();
            System.out.println("Reiziger update successful.");
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
            session.beginTransaction();
            session.delete(reiziger);
            session.getTransaction().commit();
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

            return session.get(Reiziger.class, id);

        }

        catch (HibernateException e){
            e.printStackTrace();
            System.out.println("Reiziger findbyId() was NOT successful.");
            return null;
        }
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Reiziger WHERE geboortedatum =" + datum);
            session.getTransaction().commit();
            List<Reiziger> reizigers = query.list();
            System.out.println("Reiziger findbyGbdatum() was successful.");
            return reizigers;
        }

        catch (HibernateException e){
            e.printStackTrace();
            System.out.println("Reiziger findbyGbdatum() was NOT successful.");
            return null;
        }
    }

    @Override
    public List<Reiziger> findAll() {
        return session.createQuery("SELECT r FROM Reiziger r", Reiziger.class).getResultList();
    }
}
