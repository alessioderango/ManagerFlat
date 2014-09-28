package it.ManagerFlat.project.dao;

import java.util.List;

import it.ManagerFlat.project.domain.Appartamento;
import it.ManagerFlat.project.domain.Proprietario;

public interface ProprietarioDAO {

	public Long insertProprietario(String indirizzo,List<Appartamento> appartamenti);
	public int removeAllProprietario();
	public boolean removeProprietario(Long id);
	Proprietario getProprietario(String nome);
	
}
