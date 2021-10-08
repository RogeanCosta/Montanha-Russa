package Programa;

import Janelas.Animacao;

public class Vagao extends Thread{

	public int tempoDeViagem;
	public int quantidadeDecadeiras = 1;
	
	
	@Override
	public void run(){
		while(true) {
			String pronto = "Vagão esperando embarque.\n";
			Animacao.textArea.append(pronto);
			try {
				Aplicacao.lotado.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Aplicacao.preparativos.release(quantidadeDecadeiras);

			pronto = "Vagão viajando.\n";
			Animacao.textArea.append(pronto);
			
			percorreMontanha();
			
			pronto = "Vagão esperando desembarque.\n";
			Animacao.textArea.append(pronto);
		}

	}
	
	
	// M�todo de anima��o para percorrer montanha:
	public static void percorreMontanha() {
		
	}
	
	
}
