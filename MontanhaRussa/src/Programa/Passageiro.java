package Programa;

import Janelas.Animacao;

public class Passageiro extends Thread {

	public int tempoEmbarque = 10;
	public int tempoDesembarque = 10;
	public int codigo;
	
	@Override
	public void run() {
		while(true) {
			
			String texto = String.format("Passageiro %d está esperando na fila.\n", codigo);
			Animacao.textArea.append(texto);
			
			// Down vagão
			try {
				Aplicacao.vagao.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Down mutex
			try {
				Aplicacao.mutex.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Aplicacao.cadeirasOcupadas++;
			
			texto = String.format("Passageiro %d está embarcando.\n", codigo);
			Animacao.textArea.append(texto);
			
			// Aqui vem o método Embarca()
			embarca();
			
			if(Aplicacao.cadeirasOcupadas == Aplicacao.v.quantidadeDecadeiras) {
				Aplicacao.lotado.release();
			} else {
				Aplicacao.vagao.release();
			}
		
			Aplicacao.mutex.release();
			
			// Preparação para viagem
			try {
				Aplicacao.preparativos.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			// Aqui vem o método viajando
			viajando();
		
			// Desembarque vai acontecer
			try {
				Aplicacao.mutex.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			texto = String.format("Passageiro %d está desembarcando.\n", codigo);
			Animacao.textArea.append(texto);
			
			desambarcando();
			
			Aplicacao.cadeirasOcupadas--;
			
			if(Aplicacao.cadeirasOcupadas == 0) {
				Aplicacao.vagao.release();
			}
			
			Aplicacao.mutex.release();
		}
			
	}
	
	// Método com a animação dos passageiros embarcando
	public static void embarca() {
		
	}
	
	// Método com a animação do passageiros se divertindo
	public static void viajando() {
		
	}
	
	// Método com a animação dos passageiros desembarcando
	public static void desambarcando() {
		
	}
}
