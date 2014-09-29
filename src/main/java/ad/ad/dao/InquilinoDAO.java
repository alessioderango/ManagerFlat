package ad.ad.dao;

import ad.ad.domain.Inquilino;
import ad.ad.domain.Stanza;

public interface InquilinoDAO {
	
	public Long insertInquilino(String name,String surname,String indirizzo, String email, String contratto,Stanza stanza,String datadinascita);
	public Inquilino getInquilino(Long id);
	//return the number of row deleted
	public int removeAllInquilino();
	public boolean removeInquilino(Long id);
	//aggiornamento inquilino
}
