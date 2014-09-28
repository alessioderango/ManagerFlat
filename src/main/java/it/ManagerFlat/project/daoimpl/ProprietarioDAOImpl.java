package it.ManagerFlat.project.daoimpl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.ManagerFlat.project.dao.ProprietarioDAO;
import it.ManagerFlat.project.domain.Appartamento;
import it.ManagerFlat.project.domain.Inquilino;
import it.ManagerFlat.project.domain.Lettura;
import it.ManagerFlat.project.domain.Proprietario;
import it.ManagerFlat.project.util.HibernateUtil;

public class ProprietarioDAOImpl implements ProprietarioDAO {

	@Override
	public Long insertProprietario(String nome,
			List<Appartamento> appartamenti) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			
			Proprietario proprietario=new Proprietario();
			proprietario.setNome(nome);
			proprietario.setAppartamenti(appartamenti);
			proprietario.setCancellato(false);
			
//			appartamento.setIndirizzo(indirizzo);
			
			id = (Long) session.save(proprietario);
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
	public Proprietario getProprietario(String nome) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Proprietario result = new Proprietario();
		try {
			transaction = session.beginTransaction();
			System.out.println(nome);
			result = (Proprietario) session.createQuery(
					"FROM Proprietario WHERE nome='" + nome + "'").uniqueResult();
			Hibernate.initialize(result.getAppartamenti());
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
	public int removeAllProprietario() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeProprietario(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean result = true;
		try {
			transaction = session.beginTransaction();

			Proprietario ingr = (Proprietario) session.get(Proprietario.class, id);
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
