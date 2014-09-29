package it.ManagerFlat.project.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="PROPRIETARIO")
public class Proprietario {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id_appartamento;
	
	@Column(name="NOME")
	private String nome;
	
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name="Appartamento_Proprietario", joinColumns={@JoinColumn(referencedColumnName="ID")}
                                        , inverseJoinColumns={@JoinColumn(referencedColumnName="ID")})  
    private List<Appartamento> appartamenti;
	
	@Column(name="CANCELLATO")
	private boolean cancellato;
		
	public Proprietario() {
	}

	public Long getId_appartamento() {
		return id_appartamento;
	}

	public void setId_appartamento(Long id_appartamento) {
		this.id_appartamento = id_appartamento;
	}

	

	public List<Appartamento> getAppartamenti() {
		return appartamenti;
	}

	public void setAppartamenti(List<Appartamento> appartamenti) {
		this.appartamenti = appartamenti;
	}

	public boolean isCancellato() {
		return cancellato;
	}

	public void setCancellato(boolean cancellato) {
		this.cancellato = cancellato;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((appartamenti == null) ? 0 : appartamenti.hashCode());
		result = prime * result + (cancellato ? 1231 : 1237);
		result = prime * result
				+ ((id_appartamento == null) ? 0 : id_appartamento.hashCode());
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
		Proprietario other = (Proprietario) obj;
		if (appartamenti == null) {
			if (other.appartamenti != null)
				return false;
		} else if (!appartamenti.equals(other.appartamenti))
			return false;
		if (cancellato != other.cancellato)
			return false;
		if (id_appartamento == null) {
			if (other.id_appartamento != null)
				return false;
		} else if (!id_appartamento.equals(other.id_appartamento))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	


	
	
}
