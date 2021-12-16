import DAO.AdresDAO;
import DAO.OVChipkaartDAO;
import DAO.ProductDAO;
import DAO.ReizigerDAO;
import DAOHibernate.AdresDAOHibernate;
import DAOHibernate.OVChipkaartDAOHibernate;
import DAOHibernate.ProductDAOHibernate;
import DAOHibernate.ReizigerDAOHibernate;
import Domein.Adres;
import Domein.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 *
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;

    static {
        try {
            // Create a Hibernate session factory
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retouneer een Hibernate session.
     *
     * @return Hibernate session
     * @throws HibernateException
     */
    private static Session getSession() throws HibernateException {
        return factory.openSession();
    }

    public static void main(String[] args) throws SQLException {
        testDAOHibernate();
    }

    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */
    private static void testFetchAll() {
        Session session = getSession();
        try {
            Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                Query query = session.createQuery("from " + entityType.getName());

                System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
                System.out.println();
            }
        } finally {
            session.close();
        }
    }

    private static void testDAOHibernate(){
        Session session = getSession();
        AdresDAO adresDAOHibernate = new AdresDAOHibernate(session);
        OVChipkaartDAO OVChipkaartHibernate = new OVChipkaartDAOHibernate(session);
        ProductDAO productDAOHibernate = new ProductDAOHibernate(session);
        ReizigerDAO reizigerDAOHibernate = new ReizigerDAOHibernate(session);

        Reiziger testReiziger = new Reiziger(100, "J", "", "SMIT", Date.valueOf("1997-10-24")  );
        Adres testAdres = new Adres();

        try {

            System.out.println("\n---------- Test ReizigerDAO -------------");

            //Read
            List<Reiziger> reizigers = reizigerDAOHibernate.findAll();

            System.out.println("<------------------------Voor de reiziger.save()------------------->");
            for (Reiziger reiziger: reizigers) {
                System.out.println(reiziger);
            }
            //Create
            Reiziger reiziger = new Reiziger(99,"A", "", "Ait Si M'hand", Date.valueOf("1997-10-24"));
            reizigerDAOHibernate.save(reiziger);

            List<Reiziger> reizigersNaSave = reizigerDAOHibernate.findAll();
            System.out.println("<------------------------NA de reiziger.save()------------------->");
            for (Reiziger reizigerSave: reizigersNaSave) {
                System.out.println(reizigerSave);
            }
            //Update
            reiziger.setAchternaam("Aitsimhand!!!!");
            reizigerDAOHibernate.update(reiziger);

            System.out.println("Reizger na de de update()" + "\n" + reizigerDAOHibernate.findById(reiziger.getId()).toString());
            //Delete
            reizigerDAOHibernate.delete(reiziger);


            //AdresDAOHibernate
            //Read
            System.out.println("<------------------------VOOR de adres.save()------------------->\n");
            List<Adres> adresListVoorSave = adresDAOHibernate.findAll();
            for (Adres adres : adresListVoorSave) {
                System.out.println(adres);
            }

            //Create
            Adres adresAchraf = new Adres(10, "2964BL", "26", "Irenestraat", "Groot-Ammers", testReiziger);
            adresDAOHibernate.save(adresAchraf);

            //Update
            adresAchraf.setWoonplaats("Utrecht");

            adresDAOHibernate.update(adresAchraf);
            System.out.println("<------------------------NA de adres.update()------------------->\n");
            List<Adres> adresListNaUpdate = adresDAOHibernate.findAll();
            for (Adres adres : adresListNaUpdate) {
                System.out.println(adres);
            }

            System.out.println("<------------------------NA de adres.delete()------------------->\n");
            adresDAOHibernate.delete(adresAchraf);
            List<Adres> adresListNaDelete = adresDAOHibernate.findAll();
            for (Adres adres: adresListNaDelete) {
                System.out.println(adres);
            }







        }

        finally {
            session.close();
        }


    }

}
