package it.ManagerFlat.project.util;


import it.ManagerFlat.project.domain.Administrator;
import it.ManagerFlat.project.domain.Appartamento;
import it.ManagerFlat.project.domain.Inquilino;
import it.ManagerFlat.project.domain.Lettura;
import it.ManagerFlat.project.domain.Parametro;
import it.ManagerFlat.project.domain.Proprietario;
import it.ManagerFlat.project.domain.Stanza;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
