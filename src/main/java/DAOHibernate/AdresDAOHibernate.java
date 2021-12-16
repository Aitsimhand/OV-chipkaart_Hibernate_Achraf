package DAOHibernate;

import DAO.AdresDAO;
import Domein.Adres;
import Domein.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class AdresDAOHibernate implements AdresDAO {
    private final Session session;

    public AdresDAOHibernate(Session session) {
        this.session = session;
    }
    @Override
    public boolean save(Adres adres){
        session.beginTransaction();
        session.saveOrUpdate(adres);
        session.getTransaction().commit();
        System.out.println("Adres has been saved successfully");
        return true;
    }

    @Override
    public boolean delete(Adres adres){
        try {
            session.beginTransaction();
            session.delete(adres);
            session.getTransaction().commit();
            System.out.println("Adres has been deleted successfully");
            return true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Adres adres){
        try {
            session.beginTransaction();
            session.update(adres);
            session.getTransaction().commit();
            System.out.println("Adres has been updated successfully");
            return true;
        }

        catch (HibernateException e){
            e.printStackTrace();
            return true;
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger){
        Adres adres = new Adres();

       try {
           adres = session.get(Adres.class, reiziger.getId());
           System.out.println("Het adres aan de hand van een reziger object is opgehaald.");
           return adres;
       }

       catch (HibernateException e) {
        e.printStackTrace();
        System.out.println("findAdresByReiziger failed, see the stacktrace above for more details.");
        return adres;

       }
   }

   public List<Adres> findAll() throws HibernateException{

       Query<Adres> query = session.createQuery("FROM Adres ", Adres.class);
       return query.list();
   }


}
