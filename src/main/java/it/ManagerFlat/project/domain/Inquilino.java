package it.ManagerFlat.project.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="INQUILINO")
public class Inquilino {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="COGNOME")
	private String cognome;
	
	@Column(name="INDIRIZZO")
	private String indirizzo;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="contratto")
	private String contratto;
	
	@OneToOne
	@JoinColumn(name="STANZA_ID")
	private Stanza stanza;
	
	@Column(name="DATANASCITA")
	private String datanascita;
	
	@Column(name="CANCELLATO")
	private boolean cancellato;
	
	
	
		
	public Inquilino() {
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getNome() {
		return nome;
	}




	public void setNome(String nome) {
		this.nome = nome;
	}




	public String getCognome() {
		return cognome;
	}




	public void setCognome(String cognome) {
		this.cognome = cognome;
	}




	public String getIndirizzo() {
		return indirizzo;
	}




	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getContratto() {
		return contratto;
	}




	public void setContratto(String contratto) {
		this.contratto = contratto;
	}




	public Stanza getStanza() {
		return stanza;
	}




	public void setStanza(Stanza stanza) {
		this.stanza = stanza;
	}




	public String getDatanascita() {
		return datanascita;
	}




	public void setDatanascita(String datanascita) {
		this.datanascita = datanascita;
	}




	public boolean isCancellato() {
		return cancellato;
	}




	public void setCancellato(boolean cancellato) {
		this.cancellato = cancellato;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (cancellato ? 1231 : 1237);
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result
				+ ((contratto == null) ? 0 : contratto.hashCode());
		result = prime * result
				+ ((datanascita == null) ? 0 : datanascita.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((indirizzo == null) ? 0 : indirizzo.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Inquilino other = (Inquilino) obj;
		if (cancellato != other.cancellato)
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (contratto == null) {
			if (other.contratto != null)
				return false;
		} else if (!contratto.equals(other.contratto))
			return false;
		if (datanascita == null) {
			if (other.datanascita != null)
				return false;
		} else if (!datanascita.equals(other.datanascita))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
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
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (stanza == null) {
			if (other.stanza != null)
				return false;
		} else if (!stanza.equals(other.stanza))
			return false;
		return true;
	}




	




}
