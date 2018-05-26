import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitTest {
	protected static Configuration config;
	protected static SessionFactory sessionFactory;

	@BeforeClass
	public static void setup() {
		config = new Configuration().configure("hibernate.cfg.xml");
		sessionFactory = config.buildSessionFactory();
	}

	@Test
	public void testJdbc() {
		String jdbcUrl = "jdbc:mysql://localhost:3306/hbtheater_tracker?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "hbtheater";
		String pass = "hbtheater";
		try {
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		try (Session ses = sessionFactory.openSession()) {

			Film tempFilm = new Film("The Matrix", "Film o SI", "R");
			Film tempFilm2 = new Film("Przerwana lekcja muzyki", "Susanna trafia do McLean Hospital", "R");
			Film tempFilm3 = new Film("Power Rangers", "Zordon powraca!", "12+");
			Film tempFilm4 = new Film("Teletubisie", "Twinkey Winkey, Lala, Poe", "3+");

			ses.beginTransaction();

			System.out.println("Saving the film...");
			ses.save(tempFilm);
			ses.save(tempFilm2);
			ses.save(tempFilm3);
			ses.save(tempFilm4);

			ses.getTransaction().commit();
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}

}
