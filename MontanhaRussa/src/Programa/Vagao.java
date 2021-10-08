package Programa;

import Janelas.Animacao;

public class Vagao extends Thread{

	public int tempoDeViagem;
	public int quantidadeDecadeiras;
	
	
	@Override
	public void run(){
		while(true) {
			String texto = "Vagão esperando embarque.\n";
			//telaPrincipal.canvas.fundo.textArea.append(pronto);
			try {
				Aplicacao.lotado.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Aplicacao.preparativos.release(quantidadeDecadeiras);

			texto = "Vagão viajando.\n";
			//Animacao.textArea.append(pronto);
			
			percorreMontanha();
			
			texto = "Vagão esperando desembarque.\n";
			//telaPrincipal.canvas.fundo.textArea.append(pronto);
		}

	}
	
	
	// Mï¿½todo de animaï¿½ï¿½o para percorrer montanha:
	public static void percorreMontanha() {
		
	}
	
	
}
