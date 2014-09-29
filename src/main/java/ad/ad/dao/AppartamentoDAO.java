package ad.ad.dao;

import ad.ad.domain.Appartamento;

public interface AppartamentoDAO {

	public Long insertAppartamento(String indirizzo);
	public Appartamento getAppartamento(Long id);
}
