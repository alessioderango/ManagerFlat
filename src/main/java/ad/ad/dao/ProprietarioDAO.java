package ad.ad.dao;

import java.util.List;

import ad.ad.domain.Appartamento;
import ad.ad.domain.Proprietario;

public interface ProprietarioDAO {

	public Long insertProprietario(String indirizzo,List<Appartamento> appartamenti);
	public int removeAllProprietario();
	public boolean removeProprietario(Long id);
	Proprietario getProprietario(String nome);
	
}
