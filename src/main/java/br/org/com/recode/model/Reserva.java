package br.org.com.recode.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int hospedes, quartos;
	private double subTotal;

	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataE;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataS;
	
	@OneToOne
	private Hospedagem hos;

	public Reserva() {
	}

	public Reserva(long id, int hospedes, int quartos, double subTotal, LocalDate dataE, LocalDate dataS,
			Hospedagem hos) {
		this.id = id;
		this.hospedes = hospedes;
		this.quartos = quartos;
		this.subTotal = subTotal;
		this.dataE = dataE;
		this.dataS = dataS;
		this.hos = hos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getHospedes() {
		return hospedes;
	}

	public void setHospedes(int hospedes) {
		this.hospedes = hospedes;
	}

	public int getQuartos() {
		return quartos;
	}

	public void setQuartos(int quartos) {
		this.quartos = quartos;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public LocalDate getDataE() {
		return dataE;
	}

	public void setDataE(LocalDate dataE) {
		this.dataE = dataE;
	}

	public LocalDate getDataS() {
		return dataS;
	}

	public void setDataS(LocalDate dataS) {
		this.dataS = dataS;
	}

	public Hospedagem getHos() {
		return hos;
	}

	public void setHos(Hospedagem hos) {
		this.hos = hos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataE, dataS, hos, hospedes, id, quartos, subTotal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(dataE, other.dataE) && Objects.equals(dataS, other.dataS)
				&& Objects.equals(hos, other.hos) && hospedes == other.hospedes && id == other.id
				&& quartos == other.quartos
				&& Double.doubleToLongBits(subTotal) == Double.doubleToLongBits(other.subTotal);
	}
	
	

}
