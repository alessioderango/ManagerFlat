package it.ManagerFlat.project.service;

import it.ManagerFlat.project.dao.AdminDAO;
import it.ManagerFlat.project.dao.AppartamentoDAO;
import it.ManagerFlat.project.dao.InquilinoDAO;
import it.ManagerFlat.project.dao.LetturaDAO;
import it.ManagerFlat.project.dao.ParametroDAO;
import it.ManagerFlat.project.dao.ProprietarioDAO;
import it.ManagerFlat.project.dao.StanzaDAO;
import it.ManagerFlat.project.daoimpl.AdminDAOImpl;
import it.ManagerFlat.project.daoimpl.AppartamentoDAOImpl;
import it.ManagerFlat.project.daoimpl.InquilinoDAOImpl;
import it.ManagerFlat.project.daoimpl.LetturaDAOImpl;
import it.ManagerFlat.project.daoimpl.ParametroDAOImpl;
import it.ManagerFlat.project.daoimpl.ProprietarioDAOImpl;
import it.ManagerFlat.project.daoimpl.StanzaDAOImpl;
import it.ManagerFlat.project.domain.Administrator;
import it.ManagerFlat.project.domain.Appartamento;
import it.ManagerFlat.project.domain.Inquilino;
import it.ManagerFlat.project.domain.Lettura;
import it.ManagerFlat.project.domain.Parametro;
import it.ManagerFlat.project.domain.Proprietario;
import it.ManagerFlat.project.domain.Stanza;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerFlat {
	
	@Autowired
	AdminDAO admin = new AdminDAOImpl();
	@Autowired
	AppartamentoDAO appart = new AppartamentoDAOImpl();
	@Autowired
	LetturaDAO letture = new LetturaDAOImpl();
	@Autowired
	StanzaDAO stanza = new StanzaDAOImpl();
	@Autowired
	ParametroDAO parametro = new ParametroDAOImpl();
	@Autowired
	ProprietarioDAO propri = new ProprietarioDAOImpl();
	@Autowired
	InquilinoDAO inquilino = new InquilinoDAOImpl();
	
	
	public Administrator verifyAdmin(String usr, String hpwd)
	{
		return admin.getAdmin(usr, hpwd);
			
	}
	public List<Inquilino> getAllInquilino(Proprietario p)
	{
		return inquilino.getAllInquilino(p);
		
	}
	
	public boolean aggiornaInquilino(Long id,String name,String email)
	{
		return inquilino.aggiornaInquilino( id,name,email);
		
	}
	public Inquilino getInquilino(Long id)
	{
		return inquilino.getInquilino(id);
		
	}

	public List<Lettura> getAllLetture(Long idApprtamento)
	{
		return letture.getAllLettura(idApprtamento);
		
	}
	public List<List<Lettura>> getAllLettureByData(Long idApp)
	{
		return letture.getAllLetturaByData(idApp);
		
	}
	public Lettura getLettura(Long id)
	{
		return letture.getLettura(id);
		
	}
	public boolean removeLettura(Long id)
	{
		return letture.removeLettura(id);
		
	}
	public boolean aggiornaLettura(Long id, float acquaf, float acquac,
			float gas, float luce)
	{
		return letture.aggiornaLettura(id, acquaf, acquac, gas, luce);
		
	}
	public Lettura getLetturaByDataEStanza(String data,String stanza){
		return letture.getLetturaByDataEStanza(data, stanza);
	}
	
	public Parametro getParametro(Long id)
	{
		return parametro.getParametro(id);
	}
	
	public boolean setParametro(Long id,float valore)
	{
		return parametro.setParametro(id, valore);
	}
	
	
	public Long insertLettura(Stanza stanza, float acquaf, float acquac,
			float gas, String data, String saldo, float luce){
				return letture.insertLettura(stanza, acquaf, acquac, gas, data, saldo, luce);
			
	}
	public Stanza getStanza(String nomestanza, Long idAppartamento){
		return stanza.getStanza(nomestanza,idAppartamento);
		
	}
	public Proprietario getProprietario(String nome){
		return propri.getProprietario(nome);
		
	}

	public Long insertStanza(String nome,Appartamento appartamento){
		return stanza.insertStanza(nome,appartamento);
	}
	public Long insertAppartamento(String indirizzo){
		return appart.insertAppartamento(indirizzo);
	}
	public Appartamento getAppartamento(Long id){
		return appart.getAppartamento(id);
	}
	
}
