package DAOHibernate;

import DAO.OVChipkaartDAO;
import Domein.OVChipkaart;
import Domein.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class OVChipkaartDAOHibernate implements OVChipkaartDAO {
    private final Session session;

    public OVChipkaartDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(OVChipkaart ovChipkaart) {
        session.beginTransaction();
        session.saveOrUpdate(ovChipkaart);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) {
       session.beginTransaction();
       session.saveOrUpdate(ovChipkaart);
       session.getTransaction().commit();
       return true;
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) {
        session.beginTransaction();
        session.delete(ovChipkaart);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public OVChipkaart findByReiziger(Reiziger reiziger) {
        OVChipkaart ovChipkaart = session.get(OVChipkaart.class, reiziger.getId());
        System.out.println( "findByReizigerOvchipkaart() was successful");
        return ovChipkaart;
    }

    @Override
    public List<OVChipkaart> findAll() {
        Query<OVChipkaart> query = session.createQuery("FROM OVChipkaart ", OVChipkaart.class);
        return query.list();
    }
}
