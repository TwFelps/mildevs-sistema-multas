import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import br.com.mildevs.multas.dao.CondutorDAO;
import br.com.mildevs.multas.dao.MultaDAO;
import br.com.mildevs.multas.dao.VeiculoDAO;
import br.com.mildevs.multas.entity.Condutor;
import br.com.mildevs.multas.entity.Multa;
import br.com.mildevs.multas.entity.Veiculo;

public class Menu {

	public static void exibirMenuPrincipal(String error) {
		
		System.out.println("\n===============MENU PRINCIPAL===============");
		System.out.println("\n\t1-EXIBIR MENU DO CONDUTOR");
		System.out.println("\t2-EXIBIR MENU DO VEICULO");
		System.out.println("\t3-EXIBIR MENU DA MULTA");
		System.out.println("\t4-ENCERRAR MENU PRINCIPAL");
		System.out.println(error);
		System.out.print("\n\tINFORME A AÇÃO DESEJADA: ");
		
	}
	
	public static void exibirMenuCondutor(String error) {
		CondutorDAO condutorDAO = new CondutorDAO();
		Condutor condutor = new Condutor();
		Scanner input = new Scanner(System.in);
		boolean Sair = false;
		int escolhaMenuCondutor;
		
		while(!Sair) {			
			System.out.println("\n=============MENU CONDUTOR=============");
			System.out.println("\n\t1-CADASTRAR NOVO CONDUTOR");
			System.out.println("\t2-ATUALIZAR DADOS DE UM CONDUTOR");
			System.out.println("\t3-REMOVER CONDUTOR");
			System.out.println("\t4-BUSCAR UM CONDUTOR");
			System.out.println("\t5-MOSTRAR CONDUTORES");
			System.out.println("\t6-VOLTAR AO MENU PRINCIPAL");
			System.out.println(error);
			System.out.print("\n\tINFORME A AÇÃO DESEJADA: ");
			escolhaMenuCondutor = input.nextInt();
			input.nextLine();
			error="";
			
			switch (escolhaMenuCondutor) {
			case 1:
				System.out.println("\n=====CADASTRO DO CONDUTOR=====");
				System.out.print("INFORME A CNH: ");
				condutor.setNroCnh(input.nextLine());
				condutor.setDataEmissao(LocalDate.now());
				System.out.print("INFORME O ÓRGÃO EMISSOR: ");
				condutor.setOrgaoEmissor(input.nextLine());
				System.out.print("INFORME A PONTUAÇÃO DA CNH: ");
				condutor.setPontuacao(input.nextInt());
				input.nextLine();
				System.out.println(condutorDAO.criaCondutor(condutor));
				break;
			case 2:
				System.out.println("\n=====ATUALIZAÇÃO DO CONDUTOR=====");
				System.out.print("INFORME A CNH: ");
				System.out.println(condutorDAO.atualizaCondutor(input.nextLine()));
				break;
			case 3:
				System.out.println("\n=====REMOÇÃO DO CONDUTOR=====");
				System.out.print("INFORME A CNH: ");
				System.out.println(condutorDAO.removeCondutor(input.nextLine()));
				break;
			case 4:
				System.out.println("\n=====CONSULTA DO CONDUTOR=====");
				System.out.print("INFORME A CNH: ");
				System.out.println(condutorDAO.consultaCondutor(input.nextLine()));
				break;
			case 5:
				System.out.println("\n=====LISTAGEM DE CONDUTORES=====");
				System.out.println(condutorDAO.listaCondutores());
				break;
			case 6:
				Sair = true;
				break;
			default:
				error = "\n  NÚMERO INCORRETO! ESCOLHA UM NÚMERO VÁLIDO";
				break;
			}
		}
		
	}
	
	public static void exibirMenuVeiculo(String error) {
		VeiculoDAO veiculoDAO = new VeiculoDAO();
		Veiculo veiculo = new Veiculo();
		Condutor condutor = new Condutor();
		
		Scanner input = new Scanner(System.in);
		boolean Sair = false;
		int escolhaMenuVeiculo;
		
		while(!Sair) {
			
			System.out.println("\n=============MENU VEÍCULO=============");
			System.out.println("\n\t1-CADASTRAR NOVO VEÍCULO");
			System.out.println("\t2-REMOVER VEÍCULO");
			System.out.println("\t3-VENDER VEÍCULO");
			System.out.println("\t4-BUSCAR UM VEÍCULO");
			System.out.println("\t5-MOSTRAR VEÍCULOS");
			System.out.println("\t6-VOLTAR AO MENU PRINCIPAL");
			System.out.println(error);
			System.out.print("\n\tINFORME A AÇÃO DESEJADA: ");
			escolhaMenuVeiculo = input.nextInt();
			input.nextLine();
			error="";
			
			switch (escolhaMenuVeiculo) {
			case 1:
				System.out.println("\n=====CADASTRO DO VEÍCULO=====");
				System.out.print("INFORME A PLACA: ");
				veiculo.setPlaca(input.nextLine());
				System.out.print("INFORME O ANO DO VEÍCULO: ");
				veiculo.setAno(input.nextInt());
				input.nextLine();
				System.out.print("INFORME O MODELO: ");
				veiculo.setModelo(input.nextLine());
				System.out.print("INFORME A MARCA: ");
				veiculo.setMarca(input.nextLine());
				System.out.print("INFORME A CNH DO CONDUTOR: ");
				System.out.println(veiculoDAO.criaVeiculo(veiculo, input.nextLine()));
				break;
			case 2:
				System.out.println("\n=====REMOÇÃO DO VEÍCULO=====");
				System.out.print("INFORME A PLACA: ");
				veiculoDAO.removeVeiculo(input.nextLine());
				break;
			case 3:
				String Vendedor, Comprador;
				System.out.println("\n=====VENDA DO VEÍCULO=====");
				System.out.print("INFORME A CNH DO VENDEDOR: ");
				Vendedor = input.nextLine();
				System.out.print("INFORME A CNH DO COMPRADOR: ");
				Comprador = input.nextLine();
				System.out.println(veiculoDAO.vendaVeiculo(Vendedor, Comprador));
				break;
			case 4:
				System.out.println("\n=====CONSULTA DO VEÍCULO=====");
				System.out.print("INFORME A PLACA: ");
				System.out.println(veiculoDAO.consultaVeiculo(input.nextLine()));
				break;
			case 5:
				System.out.println("\n=====LISTAGEM DOS VEÍCULOS=====");
				System.out.println(veiculoDAO.listaVeiculo());
				break;
			case 6:
				Sair = true;
				break;
			default:
				error = "\n  NÚMERO INCORRETO! ESCOLHA UM NÚMERO VÁLIDO";
				break;
			}
		}
	}
	
	public static void exibirMenuMulta(String error) {
		MultaDAO multaDAO = new MultaDAO();
		Multa multa = new Multa();
		Scanner input = new Scanner(System.in);
		boolean Sair = false;
		int escolhaMenuMulta;
		
		while(!Sair) {
			
			System.out.println("\n=============MENU MULTA============");
			System.out.println("\n\t1-CADASTRAR NOVA MULTA");
			System.out.println("\t2-REMOVER MULTA");
			System.out.println("\t3-BUSCAR MULTA");
			System.out.println("\t4-BUSCAR UM MULTAS DE UM VEÍCULO");
			System.out.println("\t5-MOSTRAR MULTAS");
			System.out.println("\t6-VOLTAR AO MENU PRINCIPAL");
			System.out.println(error);
			System.out.print("\n\tINFORME A AÇÃO DESEJADA: ");
			escolhaMenuMulta = input.nextInt();
			input.nextLine();
			error="";
			
			switch (escolhaMenuMulta) {
			case 1:
				System.out.println("\n============CADASTRO DE MULTA===========");
				System.out.print("INFORME O VALOR DA MULTA: ");
				multa.setValor(input.nextDouble());
				input.nextLine();
				System.out.print("INFORME OS PONTOS DA MULTA: ");
				multa.setPontuacao(input.nextInt());
				input.nextLine();
				System.out.print("INFORME A PLACA DO VEÍCULO MULTADO: ");
				String placa = input.nextLine();
				System.out.println(multaDAO.criaMulta(multa, placa));
				break;
			case 2:
				System.out.println("\n============REMOÇÃO DE MULTA===========");
				System.out.print("INFORME O CÓDIGO MULTA: ");
				multaDAO.removeMulta(input.nextInt());
				input.nextLine();
				break;
			case 3:
				System.out.println("\n============BUSCAR MULTA===========");
				System.out.print("INFORME O CÓDIGO MULTA: ");
				System.out.println(multaDAO.consultaMulta(input.nextInt()));
				input.nextLine();
				break;
			case 4:
				System.out.println("\n============MULTAS DE UM VEÍCULO===========");
				System.out.print("INFORME A PLACA DO VEÍCULO MULTADO: ");
				System.out.println(multaDAO.listaMultaPorVeiculo(input.nextLine()));
				break;
			case 5:
				System.out.println("\n============MULTAS DO SISTEMA===========");
				System.out.println(multaDAO.listaTodasMultas());
				break;
			case 6:
				Sair = true;
				break;
			default:
				error = "\n  NÚMERO INCORRETO! ESCOLHA UM NÚMERO VÁLIDO";
				break;
			}
		}
	}

}
