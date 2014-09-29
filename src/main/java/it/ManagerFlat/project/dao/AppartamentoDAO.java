package it.ManagerFlat.project.dao;

import it.ManagerFlat.project.domain.Appartamento;

public interface AppartamentoDAO {

	public Long insertAppartamento(String indirizzo);
	public Appartamento getAppartamento(Long id);
}
