package br.com.mildevs.multas.dao;

import java.util.List;

import br.com.mildevs.multas.entity.Condutor;
import br.com.mildevs.multas.entity.Veiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class VeiculoDAO {

	private EntityManager manager;

	public VeiculoDAO() {
		this.manager = Persistence.createEntityManagerFactory("multas").createEntityManager();
	}
	
	public String criaVeiculo(Veiculo veiculo, String nroCnh) {
		
		Condutor condutor = manager.find(Condutor.class, nroCnh);
		
		if(condutor == null)
			throw new IllegalArgumentException("CONDUTOR NÃO ENCONTRADO, INFORME A CNH CORRETA");
		
		condutor.setVeiculo(veiculo);
		veiculo.setCondutor(condutor);
		
		this.manager.getTransaction().begin();
		this.manager.persist(veiculo);
		this.manager.getTransaction().commit();
		
		return "\nVEÍCULO CADASTRADO NO SISTEMA COM SUCESSO!";
	}
	
	public String atualizaVeiculo(Veiculo veiculo) {
		
		this.manager.getTransaction().begin();
		this.manager.merge(veiculo);
		this.manager.getTransaction().commit();
		
		return "\nVEÍCULO ATUALIZADO NO SISTEMA COM SUCESSO!";
	}
	
	public String vendaVeiculo(String nroCnh_01, String nroCnh_02) {
		VeiculoDAO veiculoDAO = new VeiculoDAO();
		Condutor Vendedor = manager.find(Condutor.class, nroCnh_01);
		if(Vendedor == null)
			throw new IllegalArgumentException("VENDEDOR NÃO ENCONTRADO, INFORME A CNH CORRETA");
		
		Condutor Comprador = manager.find(Condutor.class, nroCnh_02);
		if(Comprador == null)
			throw new IllegalArgumentException("COMPRADOR NÃO ENCONTRADO, INFORME A CNH CORRETA");
		
		Veiculo veiculo = Vendedor.getVeiculo();
		veiculo.setCondutor(Comprador);
		
		veiculoDAO.atualizaVeiculo(veiculo);
		
		Comprador.setVeiculo(Vendedor.getVeiculo());
		Vendedor.setVeiculo(null);
		
		return "VENDA DO VÉICULO FOI REALIZADA COM SUCESSO!";
	}
	
	public String removeVeiculo(String placa) {
		
		Veiculo verificaVeiculo = manager.find(Veiculo.class, placa);
		
		if(verificaVeiculo == null)
			throw new IllegalArgumentException("CONDUTOR DO VEICULO NÃO ENCONTRADO, INFORME A CNH CORRETA");
		
		//Condutor condutorVeiculo = manager.find(Condutor.class, verificaVeiculo);
		
		this.manager.getTransaction().begin();
		this.manager.remove(verificaVeiculo);
		this.manager.getTransaction().commit();
		return "VEÍCULO REMOVIDO DO SISTEMA!";
	}
	
	public Veiculo consultaVeiculo(String placa) {
		
		Veiculo Veiculo = manager.find(Veiculo.class, placa);
		
		if(Veiculo == null)
			throw new IllegalArgumentException("O VEICULO NÃO ESTÁ CADASTRADO NO SISTEMA!");
		
		return Veiculo;
	}
	
	public List<Veiculo> listaVeiculo(){
		
		Query query = manager.createQuery("SELECT v FROM Veiculo v", Veiculo.class);
		return query.getResultList();
	}
	
}
