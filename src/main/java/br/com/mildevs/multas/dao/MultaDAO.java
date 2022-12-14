package br.com.mildevs.multas.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.mildevs.multas.entity.Condutor;
import br.com.mildevs.multas.entity.Multa;
import br.com.mildevs.multas.entity.Veiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class MultaDAO {

	private EntityManager manager;

	public MultaDAO() {
		this.manager = Persistence.createEntityManagerFactory("multas").createEntityManager();
	}
	
	public String criaMulta(Multa multa, String placa) {
		List<Multa> multas = new ArrayList<Multa>();
		
		Veiculo veiculo = manager.find(Veiculo.class, placa);
		
		if(veiculo == null)
			throw new IllegalArgumentException("VEICULO NÃO ENCONTRADO, INFORME A PLACA CORRETA!");
		
		Condutor condutor = manager.find(Condutor.class, veiculo.getCondutor().getNroCnh());
		
		condutor.alteraPontuacao(multa.getPontuacao(), condutor);
		
		multas.add(multa);
		multa.setVeiculo(veiculo);
		veiculo.setMultas(multas);
		
		this.manager.getTransaction().begin();
		this.manager.persist(multa);
		this.manager.getTransaction().commit();
		return "MULTA CADASTRADA NO SISTEMA COM SUCESSO!";
		
	}
	
	public String removeMulta(int codigoMulta) {
		
		Multa multa = manager.find(Multa.class, codigoMulta);
		
		if(multa == null)
			throw new IllegalArgumentException("DADOS DA MULTA NÃO ENCONTRADOS");
		
		this.manager.getTransaction().begin();
		this.manager.remove(multa);
		this.manager.getTransaction().commit();
		return "MULTA REMOVIDA COM SUCESSO!";
	}
	
	public Multa consultaMulta(int codigoMulta) {
		
		return manager.find(Multa.class, codigoMulta);
	}
	
	public List<Multa> listaMultaPorVeiculo(String placa){
		
		Veiculo veiculo = manager.find(Veiculo.class, placa);
		
		if(veiculo == null)
			throw new IllegalArgumentException("VEICULO NÃO ENCONTRADO, INFORME A PLACA CORRETA!");

		Query query = manager.createQuery("SELECT m FROM Multa m WHERE m.veiculo.placa = :placa_fk", Multa.class).setParameter("placa_fk", veiculo.getPlaca());
		return query.getResultList();
	}
	
	public List<Multa> listaTodasMultas() {
		
		Query query = manager.createQuery("SELECT m FROM Multa m", Multa.class);
		return query.getResultList();
    }
}
