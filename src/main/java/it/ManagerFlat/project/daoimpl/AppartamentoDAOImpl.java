package it.ManagerFlat.project.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.ManagerFlat.project.dao.AppartamentoDAO;
import it.ManagerFlat.project.domain.Appartamento;
import it.ManagerFlat.project.util.HibernateUtil;


public class AppartamentoDAOImpl implements AppartamentoDAO {

	@Override
	public Long insertAppartamento(String indirizzo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			
			Appartamento appartamento=new Appartamento();
			
			appartamento.setIndirizzo(indirizzo);
			
			id = (Long) session.save(appartamento);
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
	
	@Override
	public Appartamento getAppartamento(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Appartamento result = new Appartamento();
		try {
			transaction = session.beginTransaction();

			result = (Appartamento) session.load(Appartamento.class, id);
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return result;
	}

}
