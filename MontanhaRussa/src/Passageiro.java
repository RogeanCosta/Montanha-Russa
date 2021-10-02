
public class Passageiro extends Thread {

	public int tempoEmbarque;
	public int tempoDesembarque;
	public Vagao vagao;
	
	
	public Passageiro(int tempoEmbarque, int tempoDesembarque, Vagao vagao) {
		this.tempoDesembarque = tempoDesembarque;
		this.tempoEmbarque = tempoEmbarque;
		this.vagao = vagao;
	}
	
	@Override
	public void run() {
		while(true) {
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
			
			// Aqui vem o método Embarca()
			embarca();
			
			if(Aplicacao.cadeirasOcupadas == vagao.quantidadeCadeiras) {
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
