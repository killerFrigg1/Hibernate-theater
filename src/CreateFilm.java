import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateFilm {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Film.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// create 3 film objects
			System.out.println("Creating new Film objects...");
			Film tempFilm = new Film("The Matrix", "Film o SI", "R");
			Film tempFilm2 = new Film("Przerwana lekcja muzyki", "Susanna trafia do McLean Hospital", "R");
			Film tempFilm3 = new Film("Power Rangers", "Zordon powraca!", "12+");
			Film tempFilm4 = new Film("Teletubisie", "Twinkey Winkey, Lala, Poe", "3+");

			// start a transaction
			session.beginTransaction();

			// save the film objects
			System.out.println("Saving the film...");
			session.save(tempFilm);
			session.save(tempFilm2);
			session.save(tempFilm3);
			session.save(tempFilm4);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");

			// new transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			List<Film> theFilms = session.createQuery("from Film").list();
			display(theFilms);

			// display films with R rating
			theFilms = session.createQuery("from Film f where f.age_limit = 'R'").list();
			display(theFilms);

			// update the film
			Film myFilm = session.get(Film.class, 1);
			myFilm.setTitle("Enter the Matrix");

			session.getTransaction().commit();
			System.out.println("Done2!");

			// new transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			// delete statement
			session.createQuery("delete from Film where id > 3").executeUpdate();

			session.getTransaction().commit();
			System.out.println("Done3!");

		} finally {
			factory.close();
		}

	}

	public static void display(List<Film> list) {
		for (Film tempFilm : list) {
			System.out.println(tempFilm);
		}
	}

}
