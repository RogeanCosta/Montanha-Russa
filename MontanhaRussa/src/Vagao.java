
public class Vagao extends Thread{

	public static final int TEMPO_VIAGEM = 20;
	public static final int QUANTIDADE_CADEIRAS = 2;
	
	
	@Override
	public void run(){
		while(true) {
			try {
				Aplicacao.lotado.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Aplicacao.preparativos.release(QUANTIDADE_CADEIRAS);
			percorreMontanha();
		}

	}
	
	// Método de animação para percorrer montanha:
	public static void percorreMontanha() {
		
	}
}
