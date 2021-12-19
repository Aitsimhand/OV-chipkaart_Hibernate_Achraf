package DAOHibernate;

import DAO.ReizigerDAO;
import Domein.Reiziger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO {
    private final Session session;

    public ReizigerDAOHibernate(Session session) {
    this.session = session;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        session.beginTransaction();
        session.saveOrUpdate(reiziger);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        session.beginTransaction();
        session.update(reiziger);
        session.getTransaction().commit();
        System.out.println("Reiziger update successful.");
        return true;

    }

    @Override
    public boolean delete(Reiziger reiziger) {
        session.beginTransaction();
        session.delete(reiziger);
        session.getTransaction().commit();
        System.out.println("Reiziger delete successful.");
        return true;
    }

    @Override
    public Reiziger findById(int id) {
        return session.get(Reiziger.class, id);
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        session.beginTransaction();
        Query query = session.createQuery("FROM Reiziger WHERE geboortedatum =" + datum);
        session.getTransaction().commit();
        List<Reiziger> reizigers = query.list();
        System.out.println("Reiziger findbyGbdatum() was successful.");
        return reizigers;
    }

    @Override
    public List<Reiziger> findAll() {
        return session.createQuery("SELECT r FROM Reiziger r", Reiziger.class).getResultList();
    }
}
