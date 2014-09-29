package ad.ad.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LETTURA")
public class Lettura {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@ManyToOne
	private Stanza stanza;
	
	@Column(name = "ACQUAF")
	private float acquaf;
	
	@Column(name = "ACQUAC")
	private float acquac;
	
	@Column(name = "GAS")
	private float gas;
	
	@Column(name = "DATA")
	private String data;
	
	@Column(name = "SALDO")
	private String saldo;
	
	@Column(name = "LUCE")
	private float luce;

	@Column(name="CANCELLATO")
	private boolean cancellato;
	
	public Lettura() {
	}


	public Stanza getStanza() {
		return stanza;
	}


	public void setStanza(Stanza stanza) {
		this.stanza = stanza;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getAcquaf() {
		return acquaf;
	}

	public void setAcquaf(float acquaf) {
		this.acquaf = acquaf;
	}

	public float getAcquac() {
		return acquac;
	}

	public void setAcquac(float acquac) {
		this.acquac = acquac;
	}

	public float getGas() {
		return gas;
	}

	public void setGas(float gas) {
		this.gas = gas;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public float getLuce() {
		return luce;
	}

	public void setLuce(float luce) {
		this.luce = luce;
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
		result = prime * result + Float.floatToIntBits(acquac);
		result = prime * result + Float.floatToIntBits(acquaf);
		result = prime * result + (cancellato ? 1231 : 1237);
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + Float.floatToIntBits(gas);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Float.floatToIntBits(luce);
		result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
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
		Lettura other = (Lettura) obj;
		if (Float.floatToIntBits(acquac) != Float.floatToIntBits(other.acquac))
			return false;
		if (Float.floatToIntBits(acquaf) != Float.floatToIntBits(other.acquaf))
			return false;
		if (cancellato != other.cancellato)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (Float.floatToIntBits(gas) != Float.floatToIntBits(other.gas))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Float.floatToIntBits(luce) != Float.floatToIntBits(other.luce))
			return false;
		if (saldo == null) {
			if (other.saldo != null)
				return false;
		} else if (!saldo.equals(other.saldo))
			return false;
		if (stanza == null) {
			if (other.stanza != null)
				return false;
		} else if (!stanza.equals(other.stanza))
			return false;
		return true;
	}


	
	


	
	
}
