package br.com.mildevs.multas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Multa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo_multa")
	private int codigoMulta;
	
	@Column(nullable = false)
	private double valor;
	
	@Column(nullable = false)
	private int pontuacao;
	
	@ManyToOne
    @JoinColumn(name = "placa_fk", referencedColumnName = "placa")
    private Veiculo veiculo;
	
	public Multa() {

	}

	public Multa(int codigoMulta, double valor, int pontuacao, Veiculo veiculo) {
		this.codigoMulta = codigoMulta;
		this.valor = valor;
		this.pontuacao = pontuacao;
		this.veiculo = veiculo;
	}

	public int getCodigoMulta() {
		return codigoMulta;
	}

	public void setCodigoMulta(int codigoMulta) {
		this.codigoMulta = codigoMulta;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		if(valor <0)
			throw new IllegalArgumentException("O VALOR DA MULTA NÃO PODE SER NEGATIVO");
		
		this.valor = valor;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		if(pontuacao < 0)
			throw new IllegalArgumentException("A PONTUAÇÃO DEVE SER UM VALOR POSITIVO");
		
		this.pontuacao = pontuacao;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	@Override
	public String toString() {
		
		return "\n\t----------MULTA----------"+
			   "\n\tCodigo: " +this.codigoMulta+
			   "\n\tValor: " +this.valor+" R$"+
			   "\n\tPontos: " +this.pontuacao+
			   "\n\tPlaca do veículo: " +this.veiculo.getPlaca()+"\n";
	}
}
