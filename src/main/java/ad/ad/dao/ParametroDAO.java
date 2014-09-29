package ad.ad.dao;

import ad.ad.domain.Parametro;

public interface ParametroDAO {

	public Parametro getParametro(Long id);
	public Long insertParametro(String nome,float valore);
	public boolean setParametro(Long id, float valore );
	
}
