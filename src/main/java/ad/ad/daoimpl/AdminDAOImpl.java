package ad.ad.daoimpl;



import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ad.ad.dao.AdminDAO;
import ad.ad.domain.Administrator;
import ad.ad.util.HibernateUtil;



public class AdminDAOImpl implements AdminDAO {

	

	@Override
	public Long insertAdmin(String name,String surname,String user, String hpwd) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			
			Administrator admin=new Administrator();
			
			admin.setName(name);
			admin.setSurname(surname);
			admin.setUsername(user);
			admin.setHashPasswd(hpwd);
			
			id = (Long) session.save(admin);
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
	public int removeAllAdmin() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		int result = 0 ;
		try {
			transaction = session.beginTransaction();
			
			result=session.createQuery("DELETE FROM Administrator").executeUpdate();
			
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
	public Administrator getAdmin(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Administrator amdin = null;
		try {
			transaction = session.beginTransaction();
			
			amdin= (Administrator) session.load(Administrator.class, id);
			amdin.getName();

			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return amdin;
	}

	@Override
	public Administrator getAdmin(String usr, String hpwd) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Administrator admin = null;
		try {
			transaction = session.beginTransaction();
			
			Query query=session.createQuery("FROM Administrator WHERE username=:usr AND hashPasswd=:hpwd");
			query.setParameter("usr", usr);
			query.setParameter("hpwd", hpwd);
			admin=(Administrator) query.uniqueResult();

			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return admin;
	}

}
