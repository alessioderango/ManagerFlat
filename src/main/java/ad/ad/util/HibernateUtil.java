package ad.ad.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ad.ad.domain.Administrator;
import ad.ad.domain.Appartamento;
import ad.ad.domain.Inquilino;
import ad.ad.domain.Lettura;
import ad.ad.domain.Parametro;
import ad.ad.domain.Proprietario;
import ad.ad.domain.Stanza;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new Configuration()
					.configure("resource/hibernate.cfg.xml")
					.addPackage("it.ManagerFlat.project.controller.domain")
					.addAnnotatedClass(Appartamento.class)
					.addAnnotatedClass(Stanza.class)
					.addAnnotatedClass(Lettura.class)
					.addAnnotatedClass(Proprietario.class)
					.addAnnotatedClass(Administrator.class)
					.addAnnotatedClass(Inquilino.class)
					.addAnnotatedClass(Parametro.class)
					// .addAnnotatedClass(PUT CLASS)
					.buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
