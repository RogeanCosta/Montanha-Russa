package Programa;

import Janelas.Animacao;

public class Passageiro extends Thread {

	public int tempoEmbarque = 10;
	public int tempoDesembarque = 10;
	public String texto;
	
	
	@Override
	public void run() {
		while(true) {
			
			// Down vag�o
			Aplicacao.downVagao();
			
			// Down mutex
			Aplicacao.downMutex();
			
			Aplicacao.cadeirasOcupadas++;
			
			// Colocar pr�ximas duas instru��es dentro do embarca!
			texto = String.format("Passageiro %d est� esperando na fila.\n", (Aplicacao.identificador.indexOf(this)+1));
			Animacao.textArea.append(texto);
			
			// Aqui vem o m�todo Embarca()
			embarca();
			
			if(Aplicacao.cadeirasOcupadas == Aplicacao.v.quantidadeDecadeiras) {
				Aplicacao.upLotado();
			} else {
				Aplicacao.upVagao();
			}
		
			Aplicacao.upMutex();
			
			// Prepara��o para viagem
			Aplicacao.downPreparativos();
			
			// Aqui vem o m�todo viajando
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
	
	// M�todo com a anima��o dos passageiros embarcando
	public void embarca() {
		texto = String.format("Passageiro %d est� embarcando.\n", (Aplicacao.identificador.indexOf(this)+1));
		Animacao.textArea.append(texto);
	
	}
	
	// M�todo com a anima��o do passageiros se divertindo
	public void viajando() {
		texto = String.format("Passageiro %d est� apreciando paisagem.\n", (Aplicacao.identificador.indexOf(this)+1));
		Animacao.textArea.append(texto);
	}
	
	// M�todo com a anima��o dos passageiros desembarcando
	public void desambarcando() {
		
		texto = String.format("Passageiro %d est� desembarcando.\n", (Aplicacao.identificador.indexOf(this)+1));
		Animacao.textArea.append(texto);
	}
}
