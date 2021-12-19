package DAOHibernate;

import DAO.ProductDAO;
import Domein.OVChipkaart;
import Domein.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ProductDAOHibernate implements ProductDAO {
    private final Session session;

    public ProductDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Product product) {
        session.beginTransaction();
        session.saveOrUpdate(product);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Product product) {
        session.beginTransaction();
        session.saveOrUpdate(product);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Product product) {
        session.beginTransaction();
        session.delete(product);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public List<Product> findByOVChipkaart(OVChipkaart ov){
        return session.createQuery("FROM Product WHERE OVChipkaart.kaart_nummer=" + ov.getKaart_nummer(), Product.class).getResultList();
    }

    @Override
    public List<Product> findAll() {
        return session.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }
}
