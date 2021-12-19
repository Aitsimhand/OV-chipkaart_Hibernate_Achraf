import DAO.AdresDAO;
import DAO.OVChipkaartDAO;
import DAO.ProductDAO;
import DAO.ReizigerDAO;
import DAOHibernate.AdresDAOHibernate;
import DAOHibernate.OVChipkaartDAOHibernate;
import DAOHibernate.ProductDAOHibernate;
import DAOHibernate.ReizigerDAOHibernate;
import Domein.Adres;
import Domein.OVChipkaart;
import Domein.Product;
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
        testFetchAll();
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

            System.out.println("Reiziger na de de update()" + "\n" + reizigerDAOHibernate.findById(reiziger.getId()).toString());
            //Delete
            reizigerDAOHibernate.delete(reiziger);

            System.out.println("\n------------- Test AdresDAO ----------------");
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
            System.out.println("Aantal:" + adresListNaUpdate.size());


            //Delete
            System.out.println("<------------------------NA de adres.delete()------------------->\n");
            adresDAOHibernate.delete(adresAchraf);
            List<Adres> adresListNaDelete = adresDAOHibernate.findAll();
            for (Adres adres: adresListNaDelete) {
                System.out.println(adres);
            }
            System.out.println("Aantal:" + adresListNaDelete.size());

            System.out.println("\n------------- Test OvchipkaartDAO -----------------");
            //Read
            System.out.println("<------------------------Voor de Ovhipkaart.save() ------------------->\n");
            List<OVChipkaart> ovChipkaartList = OVChipkaartHibernate.findAll();
            for (OVChipkaart ovChipkaart: ovChipkaartList){
                System.out.println(ovChipkaart);
            }
            System.out.println("Aantal:" + ovChipkaartList.size());
            //Create
            Reiziger reizigerOvchip = new Reiziger(95,"A", "", "Ait Si M'hand", Date.valueOf("1997-10-24"));
            OVChipkaart testOvchip = new OVChipkaart(79627, Date.valueOf("2023-10-24"), 2, 100.00, reizigerOvchip );

            OVChipkaartHibernate.save(testOvchip);

            System.out.println("<------------------------Na de Ovhipkaart.save() ------------------->\n");
            List<OVChipkaart> ovChipkaartListNaSave = OVChipkaartHibernate.findAll();
            for (OVChipkaart ovChipkaart: ovChipkaartListNaSave){
                System.out.println(ovChipkaart);

            }
            System.out.println("Aantal:" + ovChipkaartListNaSave.size());

            //Update
            System.out.println("<------------------------Na de Ovhipkaart.update() ------------------->\n");
            testOvchip.setKlasse(1);
            OVChipkaartHibernate.update(testOvchip);
            List<OVChipkaart> ovChipkaartListNaUpdate = OVChipkaartHibernate.findAll();
            for (OVChipkaart ovChipkaart: ovChipkaartListNaUpdate){
                System.out.println(ovChipkaart);
            }
            System.out.println("Aantal:"+ ovChipkaartListNaUpdate.size());


            System.out.println("<------------------------Na de Ovhipkaart.delete() ------------------->\n");
            //Delete
            OVChipkaartHibernate.delete(testOvchip);
            List<OVChipkaart> ovChipkaartListNaDelete = OVChipkaartHibernate.findAll();
            for (OVChipkaart ovChipkaart: ovChipkaartListNaDelete){
                System.out.println(ovChipkaart);

            }
            System.out.println("Aantal:" + ovChipkaartListNaDelete.size());
            reizigerDAOHibernate.delete(reizigerOvchip);

            System.out.println("\n------------- Test productDAO -----------------");

            //Read
            System.out.println("<------------------------VOOR de product.save() ------------------->\n");
            try {
                List<Product> productList = productDAOHibernate.findAll();
                for (Product product: productList){
                    System.out.println(product);
                }
                System.out.println("Aantal:" + productList.size());
            }
            catch (SQLException e){
                System.out.println("Het volgende ging fout:" + "\n");
                e.printStackTrace();
            }
            //Create
            System.out.println("<------------------------Na de product.save() ------------------->\n");
            Product testProduct = new Product(10, "Senioren pas", "kortingsproduct om senioren meer mobiliteit te geven", 10.00 );
            productDAOHibernate.save(testProduct);

            try {
                List<Product> productListNaSave = productDAOHibernate.findAll();
                for (Product product: productListNaSave){
                    System.out.println(product);
                }
                System.out.println("Aantal:" + productListNaSave.size());
            }
            catch (SQLException e){
                System.out.println("Het volgende ging fout:" + "\n");
                e.printStackTrace();
            }

            //Update

            testProduct.setPrijs(50.00);
            productDAOHibernate.update(testProduct);

            try {
                List<Product> productListNaUpdate = productDAOHibernate.findAll();
                for (Product product: productListNaUpdate){
                    System.out.println(product);
                }
                System.out.println("Aantal:" + productListNaUpdate.size());
            }
            catch (SQLException e){
                System.out.println("Het volgende ging fout:" + "\n");
                e.printStackTrace();
            }

            //Delete

            productDAOHibernate.delete(testProduct);
            try {
                List<Product> productListNaDelete = productDAOHibernate.findAll();
                for (Product product: productListNaDelete){
                    System.out.println(product);
                }
                System.out.println("Aantal:" + productListNaDelete.size());

            }
            catch (SQLException e){
                System.out.println("Het volgende ging fout:" + "\n");
                e.printStackTrace();
            }



        }

        finally {
            session.close();
        }


    }

}
