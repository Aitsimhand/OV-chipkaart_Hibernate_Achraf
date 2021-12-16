package DAOHibernate;

import DAO.ProductDAO;
import Domein.OVChipkaart;
import Domein.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAOHibernate implements ProductDAO {
    private final Session session;

    public ProductDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Product product) {
        try {
            session.save(product);
            System.out.println("Product save NOT successful.");
            return true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            System.out.println("Product save successful.");
            return false;
        }
    }

    @Override
    public boolean update(Product product) {
        try {
            session.update(product);
            System.out.println("Product update successful.");
            return true;
        }

        catch (HibernateException e){
            e.printStackTrace();
            System.out.println("Product update NOT successful.");
            return false;
        }

    }

    @Override
    public boolean delete(Product product) {
        try {
            session.delete(product);
            System.out.println("Product delete successful.");
            return true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            System.out.println("Product delete NOT successful.");
            return false;
        }
    }

    @Override
    public List<Product> findByOVChipkaart(OVChipkaart ov) throws HibernateException{
        Query<Product> query = session.createQuery("FROM Product WHERE OVChipkaart.kaart_nummer=" + ov.getKaart_nummer() );
        return query.list();
    }

    @Override
    public List<Product> findAll() throws HibernateException {
        Query<Product> query = session.createQuery("FROM Product", Product.class);
        return query.list();
    }
}
