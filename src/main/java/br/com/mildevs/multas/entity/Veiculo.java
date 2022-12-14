package br.com.mildevs.multas.entity;

import java.util.List;

import br.com.mildevs.multas.dao.MultaDAO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Veiculo {

	@Id
	private String placa;
	
	@Column(nullable = false)
	private int ano;
	
	@Column(nullable = false)
	private String modelo;
	
	@Column(nullable = false)
	private String marca;
	
	@OneToOne
    @JoinColumn(name = "cnh_fk", referencedColumnName = "nro_cnh")
    private Condutor condutor;
	
	@OneToMany(mappedBy = "veiculo")
    private List<Multa> multas;
	
	public Veiculo() {

	}
	
	public Veiculo(String placa, int ano, String modelo, String marca, Condutor condutor, List<Multa> multas) {
		this.placa = placa;
		this.ano = ano;
		this.modelo = modelo;
		this.marca = marca;
		this.condutor = condutor;
		this.multas = multas;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Condutor getCondutor() {
		return condutor;
	}

	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
		
	}

	public List<Multa> getMultas() {
		return multas;
	}

	public void setMultas(List<Multa> multa) {
		this.multas = multa;
	}

	@Override
	public String toString() {
		
		return "\n\t---------VEICULO---------"+
				"\n\tPlaca: " +this.placa+
				"\n\tAno: " +this.ano+
				"\n\tModelo: " +this.modelo+
				"\n\tMarca: " +this.marca+
				"\n\tCNH do condutor: " +this.condutor.getNroCnh()+
				"\n\tMultas: " +(multas==null ? "Não há multas cadastradas no veículo\n" : this.multas+"\n");
	}
	
}
