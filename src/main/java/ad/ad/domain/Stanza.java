package ad.ad.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="STANZA")
public class Stanza {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	
	@Column(name="NOME")
	private String nome;
	
	@OneToOne
	@JoinColumn(name="APPARTAMENTO_ID")
	private Appartamento appartamento;
	

//	@ManyToOne(cascade = { CascadeType.ALL })
//	@JoinColumn(name = "Letture")
//	private List<Lettura> letture = new ArrayList<Lettura>();
	
	@OneToOne(mappedBy= "stanza",cascade = CascadeType.ALL)
	private Inquilino inquilino;
	
	@Column(name="CANCELLATO")
	private boolean cancellato;	
	
	public Stanza() {
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

	public Appartamento getAppartamento() {
		return appartamento;
	}

	public void setAppartamento(Appartamento appartamento) {
		this.appartamento = appartamento;
	}



	public Inquilino getInquilino() {
		return inquilino;
	}

	public void setInquilino(Inquilino inquilino) {
		this.inquilino = inquilino;
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
		result = prime * result
				+ ((appartamento == null) ? 0 : appartamento.hashCode());
		result = prime * result + (cancellato ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Stanza other = (Stanza) obj;
		if (appartamento == null) {
			if (other.appartamento != null)
				return false;
		} else if (!appartamento.equals(other.appartamento))
			return false;
		if (cancellato != other.cancellato)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	




	


	

	
	
}
