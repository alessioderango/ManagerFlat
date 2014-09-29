package ad.ad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ad.ad.dao.AdminDAO;
import ad.ad.dao.AppartamentoDAO;
import ad.ad.dao.LetturaDAO;
import ad.ad.dao.ParametroDAO;
import ad.ad.dao.ProprietarioDAO;
import ad.ad.dao.StanzaDAO;
import ad.ad.daoimpl.AdminDAOImpl;
import ad.ad.daoimpl.AppartamentoDAOImpl;
import ad.ad.daoimpl.LetturaDAOImpl;
import ad.ad.daoimpl.ParametroDAOImpl;
import ad.ad.daoimpl.ProprietarioDAOImpl;
import ad.ad.daoimpl.StanzaDAOImpl;
import ad.ad.domain.Administrator;
import ad.ad.domain.Appartamento;
import ad.ad.domain.Lettura;
import ad.ad.domain.Parametro;
import ad.ad.domain.Proprietario;
import ad.ad.domain.Stanza;

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
	
	
	public Administrator verifyAdmin(String usr, String hpwd)
	{
		return admin.getAdmin(usr, hpwd);
			
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
