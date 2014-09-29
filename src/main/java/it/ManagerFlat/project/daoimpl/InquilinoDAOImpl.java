package it.ManagerFlat.project.daoimpl;

import it.ManagerFlat.project.dao.InquilinoDAO;
import it.ManagerFlat.project.domain.Appartamento;
import it.ManagerFlat.project.domain.Inquilino;
import it.ManagerFlat.project.domain.Stanza;
import it.ManagerFlat.project.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class InquilinoDAOImpl implements InquilinoDAO {

	@Override
	public Long insertInquilino(String name, String surname, String indirizzo,
			String email, String contratto, Stanza stanza, String datadinascita) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			
			Inquilino inquilino=new Inquilino();
			inquilino.setNome(name);
			inquilino.setCognome(surname);
			inquilino.setIndirizzo(indirizzo);
			inquilino.setEmail(email);
			inquilino.setContratto(contratto);
			inquilino.setStanza(stanza);
			inquilino.setDatanascita(datadinascita);
			
//			appartamento.setIndirizzo(indirizzo);
			
			id = (Long) session.save(inquilino);
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
	public Inquilino getInquilino(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Inquilino result = new Inquilino();
		try {
			transaction = session.beginTransaction();

			result = (Inquilino) session.createQuery(
					"FROM Inquilino WHERE id='" + id + "'").uniqueResult();

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
	public int removeAllInquilino() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeInquilino(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean result = true;
		try {
			transaction = session.beginTransaction();

			Inquilino ingr = (Inquilino) session.get(Inquilino.class, id);
			ingr.setCancellato(true);
			session.update(ingr);

			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
			result= false;
		} finally {
			session.close();
		}

		return result;
	}

}
