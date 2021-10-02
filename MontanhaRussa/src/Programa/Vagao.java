package Programa;

public class Vagao extends Thread{

	public int tempoDeViagem;
	public int quantidadeDecadeiras = 1;
	
	
	@Override
	public void run(){
		while(true) {
			try {
				Aplicacao.lotado.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Aplicacao.preparativos.release(quantidadeDecadeiras);
			percorreMontanha();
		}

	}
	
	
	// M�todo de anima��o para percorrer montanha:
	public static void percorreMontanha() {
		
	}
	
	
}
