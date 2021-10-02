import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import javax.swing.JFrame;

public class Aplicacao extends JFrame{

	public static Semaphore preparativos = new Semaphore(0);
	public static Semaphore mutex = new Semaphore(1);
	public static Semaphore vagao = new Semaphore(1, true);
	public static Semaphore lotado = new Semaphore(0);
	public static int cadeirasOcupadas = 0;
	
	public static void main(String[] args) {
		
		Janela j = new Janela();
//		Vagao v = j.Janela();
		j.Janela();
		
		
		while(true) {

			if(BotaoVagao.click != 0) {
				Vagao v = new Vagao(Integer.parseInt(j.cadeiras.getText()), Integer.parseInt(j.viagem.getText()));
				System.out.println("Quantidade de cadeiras é " + v.quantidadeCadeiras);
				System.out.println("Quantidade de tempo de viagem é " + v.tempoViagem);
			}
		}
		// Queue<Passageiro> bankQueue = new LinkedList();
		
		// v.start();
		
		// while(true) {
			
			// Quando a gente criasse um novo passageiro
		//	bankQueue.add(new Passageiro());
		// }
	}
}
