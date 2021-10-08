package Programa;

import Janelas.Animacao;

public class Vagao extends Thread{

	public int tempoDeViagem;
	public int quantidadeDecadeiras;
	
	
	@Override
	public void run(){
		while(true) {
			String texto = "Vag�o esperando embarque.\n";
			//telaPrincipal.canvas.fundo.textArea.append(pronto);
			try {
				Aplicacao.lotado.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Aplicacao.preparativos.release(quantidadeDecadeiras);

			texto = "Vag�o viajando.\n";
			//Animacao.textArea.append(pronto);
			
			percorreMontanha();
			
			texto = "Vag�o esperando desembarque.\n";
			//telaPrincipal.canvas.fundo.textArea.append(pronto);
		}

	}
	
	
	// M�todo de anima��o para percorrer montanha:
	public static void percorreMontanha() {
		
	}
	
	
}
