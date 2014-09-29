package it.ManagerFlat.project.dao;


import it.ManagerFlat.project.domain.Lettura;
import it.ManagerFlat.project.domain.Stanza;

import java.util.ArrayList;
import java.util.List;

public interface LetturaDAO {
	
	public Long insertLettura(Stanza stanza,float acquaf,float acquac,float gas,String data,String saldo,float luce);
	public Lettura getLettura(Long id);
	//return the number of row deleted
	public int removeAllLettura();
	public boolean removeLettura(Long id);
	Lettura getLetturaByDataEStanza(String data, String stanza);
	boolean aggiornaLettura(Long id,  float acquaf, float acquac,
			float gas, float luce);
	List<Lettura> getAllLettura(Long idAppartamento);
	List<List<Lettura>> getAllLetturaByData(Long idAppartamento);

}
