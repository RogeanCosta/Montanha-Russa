
public class Vagao extends Thread{

	public int tempoViagem = 20;
	public int quantidadeCadeiras = 2;
	
	
	public Vagao (int tempoViagem, int quantidadeCadeiras) {
		this.tempoViagem = tempoViagem;
		this.quantidadeCadeiras = quantidadeCadeiras;
	}
	
	@Override
	public void run(){
		while(true) {
			try {
				Aplicacao.lotado.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Aplicacao.preparativos.release(this.quantidadeCadeiras);
			percorreMontanha();
		}

	}
	
	// Método de animação para percorrer montanha:
	public static void percorreMontanha() {
		
	}
}
