package ad.ad.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="APPARTAMENTO")
public class Appartamento {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	
	@Column(name="INDIRIZZO")
	private String indirizzo;
	
	@ManyToMany(mappedBy="appartamenti",cascade = { CascadeType.ALL })
	private List<Proprietario> proprietari;
	
	@Column(name="CANCELLATO")
	private boolean cancellato;
		
	@OneToMany(cascade = CascadeType.ALL)
	private List<Stanza> stanza= new ArrayList<Stanza>();
	
	public Appartamento() {
	}



	public String getIndirizzo() {
		return indirizzo;
	}



	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}



	public List<Proprietario> getProprietari() {
		return proprietari;
	}



	public void setProprietari(List<Proprietario> proprietari) {
		this.proprietari = proprietari;
	}



	public boolean isCancellato() {
		return cancellato;
	}



	public void setCancellato(boolean cancellato) {
		this.cancellato = cancellato;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



public List<Stanza> getStanza() {
		return stanza;
	}



	public void setStanza(List<Stanza> stanza) {
		this.stanza = stanza;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (cancellato ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((indirizzo == null) ? 0 : indirizzo.hashCode());
		result = prime * result
				+ ((proprietari == null) ? 0 : proprietari.hashCode());
		result = prime * result + ((stanza == null) ? 0 : stanza.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appartamento other = (Appartamento) obj;
		if (cancellato != other.cancellato)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (indirizzo == null) {
			if (other.indirizzo != null)
				return false;
		} else if (!indirizzo.equals(other.indirizzo))
			return false;
		if (proprietari == null) {
			if (other.proprietari != null)
				return false;
		} else if (!proprietari.equals(other.proprietari))
			return false;
		if (stanza == null) {
			if (other.stanza != null)
				return false;
		} else if (!stanza.equals(other.stanza))
			return false;
		return true;
	}



	

	


		
	
}
