package Programa;

import Janelas.Animacao;

public class Passageiro extends Thread {

	public int tempoEmbarque = 10;
	public int tempoDesembarque = 10;
	public String texto;
	
	
	@Override
	public void run() {
		while(true) {
			
			// Down vagão
			Aplicacao.downVagao();
			
			// Down mutex
			Aplicacao.downMutex();
			
			Aplicacao.cadeirasOcupadas++;
			
			// Colocar próximas duas instruções dentro do embarca!
			texto = String.format("Passageiro %d está esperando na fila.\n", (Aplicacao.identificador.indexOf(this)+1));
			Animacao.textArea.append(texto);
			
			// Aqui vem o método Embarca()
			embarca();
			
			if(Aplicacao.cadeirasOcupadas == Aplicacao.v.quantidadeDecadeiras) {
				Aplicacao.upLotado();
			} else {
				Aplicacao.upVagao();
			}
		
			Aplicacao.upMutex();
			
			// Preparação para viagem
			Aplicacao.downPreparativos();
			
			// Aqui vem o método viajando
			viajando();
		
			// Desembarque vai acontecer
			Aplicacao.downMutex();
			
			desambarcando();
			
			Aplicacao.cadeirasOcupadas--;
			
			if(Aplicacao.cadeirasOcupadas == 0) {
				Aplicacao.upVagao();
			}
			
			Aplicacao.upMutex();
		}
			
	}
	
	// Método com a animação dos passageiros embarcando
	public void embarca() {
		texto = String.format("Passageiro %d está embarcando.\n", (Aplicacao.identificador.indexOf(this)+1));
		Animacao.textArea.append(texto);
	
	}
	
	// Método com a animação do passageiros se divertindo
	public void viajando() {
		texto = String.format("Passageiro %d está apreciando paisagem.\n", (Aplicacao.identificador.indexOf(this)+1));
		Animacao.textArea.append(texto);
	}
	
	// Método com a animação dos passageiros desembarcando
	public void desambarcando() {
		
		texto = String.format("Passageiro %d está desembarcando.\n", (Aplicacao.identificador.indexOf(this)+1));
		Animacao.textArea.append(texto);
	}
}
