package ad.ad.dao;

import java.util.List;

import ad.ad.domain.Appartamento;
import ad.ad.domain.Lettura;
import ad.ad.domain.Stanza;

public interface StanzaDAO {

	public Long insertStanza(String nome,Appartamento appartamento);
	public int removeAllStanza();
	public boolean removeStanza(Long id);
	Stanza getStanza(String nomestanza, Long idAppartamento);
}
