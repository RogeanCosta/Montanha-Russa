import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Aplicacao {

	public static Semaphore preparativos = new Semaphore(0);
	public static Semaphore mutex = new Semaphore(1);
	public static Semaphore vagao = new Semaphore(1, true);
	public static Semaphore lotado = new Semaphore(0);
	public static int cadeirasOcupadas = 0;
	
	public static void main(String[] args) {
		
		Vagao v = new Vagao();
		Queue<Passageiro> bankQueue = new LinkedList();
		
		v.start();
		
		while(true) {
			
			// Quando a gente criasse um novo passageiro
			bankQueue.add(new Passageiro());
		}
	}
}
