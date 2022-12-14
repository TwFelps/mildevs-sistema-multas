package br.com.mildevs.multas.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import br.com.mildevs.multas.entity.Condutor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class CondutorDAO {
	
	private EntityManager manager;

	public CondutorDAO() {
		this.manager = Persistence.createEntityManagerFactory("multas").createEntityManager();
	}

	public String criaCondutor(Condutor condutor) {
		
		manager.getTransaction().begin();
		manager.persist(condutor);
		manager.getTransaction().commit();
		
		return "\nCADASTRO EFETUADO COM SUCESSO!";
	}
	
	public String atualizaCondutor(String nroCnh) {
		Scanner input = new Scanner(System.in);
		Condutor condutor = manager.find(Condutor.class, nroCnh);
		
		if(condutor == null)
			throw new IllegalArgumentException("CONDUTOR NÃO ENCONTRADO!");
		
		condutor.setDataEmissao(LocalDate.now());
		System.out.print("INFORME O NOVO ÓRGÃO EMISSOR: ");
		condutor.setOrgaoEmissor(input.nextLine());
		System.out.print("INFORME A NOVA PONTUAÇÃO DA CNH: ");
		condutor.setPontuacao(input.nextInt());
		input.nextLine();
		
		manager.getTransaction().begin();
		manager.merge(condutor);
		manager.getTransaction().commit();
		
		return "\nATUALIZAÇÃO EFETUADA COM SUCESSO!";
	}
	
	public String removeCondutor(String nroCnh) {
		
		Condutor consultaCondutor = manager.find(Condutor.class, nroCnh);
		
		if(consultaCondutor == null)
			throw new IllegalArgumentException("CONDUTOR NÃO ENCONTRADO!");
		
		manager.getTransaction().begin();
		manager.remove(consultaCondutor);
		manager.getTransaction().commit();
		
		return "\nREMOÇÃO REALIZADA COM SUCESSO!";
	}
	
	public Condutor consultaCondutor(String nroCnh) {
		
		Condutor condutor = this.manager.find(Condutor.class, nroCnh);
		
		if(condutor == null) 
			throw new IllegalArgumentException("CONDUTOR NÃO ENCONTRADO!");
		
		return condutor;
	}
	
	public List<Condutor> listaCondutores(){
		Query query = manager.createQuery("SELECT c FROM Condutor c", Condutor.class);	
		return query.getResultList();
	}
}
