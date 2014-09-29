package it.ManagerFlat.project.daoimpl;

import it.ManagerFlat.project.dao.StanzaDAO;
import it.ManagerFlat.project.domain.Appartamento;
import it.ManagerFlat.project.domain.Inquilino;
import it.ManagerFlat.project.domain.Lettura;
import it.ManagerFlat.project.domain.Proprietario;
import it.ManagerFlat.project.domain.Stanza;
import it.ManagerFlat.project.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StanzaDAOImpl implements StanzaDAO {

	@Override
	public Long insertStanza(String nome,Appartamento appartamento) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			
			
			Stanza stanza=new Stanza();
			stanza.setNome(nome);
			stanza.setAppartamento(appartamento);
//			stanza.setLetture(letture);
			stanza.setCancellato(false);
			
//			appartamento.setIndirizzo(indirizzo);
			
			id = (Long) session.save(stanza);
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
	public Stanza getStanza(String nomestanza,Long idAppartamento) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Stanza result = new Stanza();
		try {
			transaction = session.beginTransaction();

			
			result = (Stanza) session.createQuery(
					"FROM Stanza WHERE nome='" + nomestanza + "' AND appartamento='"+idAppartamento+"'").uniqueResult();
			Hibernate.initialize(result.getAppartamento());
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
	public int removeAllStanza() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeStanza(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean result = true;
		try {
			transaction = session.beginTransaction();

			Stanza ingr = (Stanza) session.get(Stanza.class, id);
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
