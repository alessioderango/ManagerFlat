package it.ManagerFlat.project.daoimpl;

import it.ManagerFlat.project.dao.ParametroDAO;
import it.ManagerFlat.project.domain.Lettura;
import it.ManagerFlat.project.domain.Parametro;
import it.ManagerFlat.project.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ParametroDAOImpl implements ParametroDAO {

	@Override
	public Parametro getParametro(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Parametro result = new Parametro();
		try {
			transaction = session.beginTransaction();

			result = (Parametro) session.createQuery("FROM Parametro WHERE id='" + id + "'").uniqueResult();

			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public boolean setParametro(Long id, float valore) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean result = false;
		try {
			transaction = session.beginTransaction(); 

			Parametro para = (Parametro) session.load(Parametro.class, id);
			if (para != null) {
				para.setValore(valore);

				session.update(para);
								
				result = true;
			}
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
			result = false;
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public Long insertParametro(String nome,float valore) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			
					
			Parametro parametro=new Parametro();
			parametro.setNome(nome);
			parametro.setValore(valore);
			
			id = (Long) session.save(parametro);
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
			id=null;
		} finally {
			session.close();
		}
		
		return id;
	}

}
