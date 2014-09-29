package it.ManagerFlat.project.dao;

import it.ManagerFlat.project.domain.Parametro;

public interface ParametroDAO {

	public Parametro getParametro(Long id);
	public Long insertParametro(String nome,float valore);
	public boolean setParametro(Long id, float valore );
	
}
