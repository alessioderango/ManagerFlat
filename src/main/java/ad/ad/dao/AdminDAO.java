package ad.ad.dao;

import ad.ad.domain.Administrator;




public interface AdminDAO {
	
	public Long insertAdmin(String name,String surname,String user, String hpwd);
	public Administrator getAdmin(Long id);
	//return the number of row deleted
	public int removeAllAdmin();
	public Administrator getAdmin(String usr,String hpwd);

}
