package br.com.mildevs.multas.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Condutor {

	@Id
	@Column(name = "nro_cnh")
	private String nroCnh;
	
	@Column(name = "data_emissao", nullable = false)
	private LocalDate dataEmissao;
	
	@Column(name = "orgao_emissor", nullable = false)
	private String orgaoEmissor;
	
	@Column(nullable = false)
	private int pontuacao;
	
	@Column
	private boolean apto = true;
	
	@OneToOne(mappedBy = "condutor")
	private Veiculo veiculo;
	
	public Condutor() {

	}
	
	public Condutor(String nroCnh, LocalDate dataEmissao, String orgaoEmissor, int pontuacao, Veiculo veiculo) {
		this.nroCnh = nroCnh;
		this.dataEmissao = dataEmissao;
		this.orgaoEmissor = orgaoEmissor;
		this.pontuacao = pontuacao;
		this.veiculo = veiculo;
	}

	public String getNroCnh() {
		return nroCnh;
	}

	public void setNroCnh(String nroCnh) {
		this.nroCnh = nroCnh;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	
	public String alteraPontuacao(int pontosMulta, Condutor condutor) {
		
		if(condutor.getPontuacao() - pontosMulta <= 0) {
			condutor.setPontuacao(0);
			condutor.setApto(false);
			return "LIMITE DE PONTUAÇÃO EXCEDIDA, O CONDUTOR TERÁ SUA CNH CANCELADA!";
		}
		
		this.pontuacao = (condutor.getPontuacao()-pontosMulta);
		return "PONTOS DA CNH RETIRADOS!";
	}

	public boolean isApto() {
		return apto;
	}

	public void setApto(boolean apto) {
		this.apto = apto;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	@Override
	public String toString() {

		return "\n\t---------CONDUTOR---------"+
				"\n\tNúmero da CNH: " +this.nroCnh+
				"\n\tData de emissão: " +this.dataEmissao+
				"\n\tOrgão emissor: " +this.orgaoEmissor+
				"\n\tPontuação: " +this.pontuacao+
				"\n\tPlaca do veículo: " +(veiculo==null ? "Sem veiculo\n" : this.veiculo.getPlaca()+"\n");
	}
	
}
