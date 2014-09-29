package it.ManagerFlat.project.dao;

import it.ManagerFlat.project.domain.Appartamento;
import it.ManagerFlat.project.domain.Lettura;
import it.ManagerFlat.project.domain.Stanza;

import java.util.List;

public interface StanzaDAO {

	public Long insertStanza(String nome,Appartamento appartamento);
	public int removeAllStanza();
	public boolean removeStanza(Long id);
	Stanza getStanza(String nomestanza, Long idAppartamento);
}
