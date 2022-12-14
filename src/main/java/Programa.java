import java.time.LocalDate;
import java.util.Scanner;

import br.com.mildevs.multas.dao.CondutorDAO;
import br.com.mildevs.multas.dao.MultaDAO;
import br.com.mildevs.multas.dao.VeiculoDAO;
import br.com.mildevs.multas.entity.Condutor;
import br.com.mildevs.multas.entity.Multa;
import br.com.mildevs.multas.entity.Veiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Programa {

	public static void main(String[] args) {
		
		/*EntityManager manager;
		manager = Persistence.createEntityManagerFactory("multas").createEntityManager();
		manager.close();*/
		Scanner input = new Scanner(System.in);
		boolean Sair = false;
		int escolhaMenu;
		String error="";
		
		while(!Sair) {
			Menu.exibirMenuPrincipal(error);
			escolhaMenu = input.nextInt();
			error="";
			
			switch (escolhaMenu) {
			case 1:
				Menu.exibirMenuCondutor(error);
				break;
			case 2:
				Menu.exibirMenuVeiculo(error);
				break;
			case 3:
				Menu.exibirMenuMulta(error);
				break;
			case 4:
				Sair = true;
				break;
			default: error = "\n  NÚMERO INCORRETO! ESCOLHA UM NÚMERO VÁLIDO";
				break;
			}
		}
		
		
	}
}
