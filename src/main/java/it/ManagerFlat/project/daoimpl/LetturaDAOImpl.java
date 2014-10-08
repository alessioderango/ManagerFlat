package it.ManagerFlat.project.daoimpl;


import it.ManagerFlat.project.dao.LetturaDAO;
import it.ManagerFlat.project.domain.Inquilino;
import it.ManagerFlat.project.domain.Lettura;
import it.ManagerFlat.project.domain.Stanza;
import it.ManagerFlat.project.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LetturaDAOImpl implements LetturaDAO {

	@Override
	public Long insertLettura(Stanza stanza, float acquaf, float acquac,
			float gas, String data, String saldo, float luce) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			
					
			Lettura lettura=new Lettura();
			lettura.setStanza(stanza);
			lettura.setAcquaf(acquaf);
			lettura.setAcquac(acquac);
			lettura.setGas(gas);
			lettura.setData(data);
			lettura.setSaldo(saldo);
			lettura.setLuce(luce);
			lettura.setCancellato(false);
						
			id = (Long) session.save(lettura);
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
	public Lettura getLettura(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Lettura result = new Lettura();
		try {
			transaction = session.beginTransaction();

			result = (Lettura) session.createQuery("FROM Lettura WHERE id='" + id + "'").uniqueResult();

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
	public List<List<Lettura>> getAllLetturaByData(Long idAppartamento) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<List<Lettura>> result = new ArrayList<List<Lettura>>();
		List<Lettura> resultData = new ArrayList<Lettura>();
		List<Lettura> resultByData = new ArrayList<Lettura>();
		try {
			transaction = session.beginTransaction();
			
			resultData = session.createQuery("FROM Lettura GROUP BY(data)").list();
			for (Lettura lettura : resultData) {
				resultByData = session.createQuery("FROM Lettura WHERE data='"+lettura.getData()+"'").list();
				List<Lettura> tmp = new ArrayList<Lettura>();
				for (Lettura lettura1 : resultByData) {
					Hibernate.initialize(lettura1.getStanza());
					System.out.println("lettura.getStanza().getAppartamento().getId() "+lettura1.getStanza().getAppartamento().getId()+ " idAppartamento "+idAppartamento);
					if((lettura1.getStanza().getAppartamento().getId()==idAppartamento))
						tmp.add(lettura1);
				}
				if(tmp != null)
					result.add(tmp);
//				if((lettura.getStanza().getAppartamento().getId()==idAppartamento))
//					result.add(resultByData);
			}
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
	public Lettura getLetturaByDataEStanza(String data,String stanza) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Lettura resultData = new Lettura();
		try {
			transaction = session.beginTransaction();
			
			resultData = (Lettura) session.createQuery("FROM Lettura WHERE data='"+data+"' AND stanza='"+stanza+"'").uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return resultData;
	}

	@Override
	public boolean aggiornaLettura(Long id, float acquaf, float acquac,
			float gas,  float luce) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean result = false;
		try {
			transaction = session.beginTransaction(); 
			Lettura lettura = (Lettura) session.load(Lettura.class, id);
			if (lettura != null) {
				lettura.setAcquac(acquac);
				lettura.setAcquaf(acquaf);
				lettura.setLuce(luce);
				lettura.setGas(gas);

				session.update(lettura);
								
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
	public List<Lettura> getAllLettura(Long idAppartamento) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<Lettura> resulttmp = new ArrayList<Lettura>();
		
		List<Lettura> result = new ArrayList<Lettura>();
		try {
			transaction = session.beginTransaction();
			//TODO
			resulttmp = session.createQuery("FROM Lettura ").list();
			System.out.println("size list "+result.size());
			for (int i = 0; i < resulttmp.size(); i++) {
				Hibernate.initialize(resulttmp.get(i).getStanza());
				if((resulttmp.get(i).getStanza().getAppartamento().getId() == idAppartamento))
				{						
					result.add(resulttmp.get(i));
				}
			}
			
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
	public int removeAllLettura() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeLettura(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean result = true;
		try {
			transaction = session.beginTransaction();

			Lettura ingr = (Lettura) session.get(Lettura.class, id);
			session.delete(ingr); 

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
