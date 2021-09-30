
public class Passageiro extends Thread {

	public static final int TEMPO_EMBARQUE = 10;
	public static final int TEMPO_DESEMBARQUE = 10;
	
	@Override
	public void run() {
		while(true) {
			// Down vag�o
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
			
			// Aqui vem o m�todo Embarca()
			embarca();
			
			if(Aplicacao.cadeirasOcupadas == Vagao.QUANTIDADE_CADEIRAS) {
				Aplicacao.lotado.release();
			} else {
				Aplicacao.vagao.release();
			}
		
			Aplicacao.mutex.release();
			
			// Prepara��o para viagem
			try {
				Aplicacao.preparativos.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Aqui vem o m�todo viajando
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
	
	// M�todo com a anima��o dos passageiros embarcando
	public static void embarca() {
		
	}
	
	// M�todo com a anima��o do passageiros se divertindo
	public static void viajando() {
		
	}
	
	// M�todo com a anima��o dos passageiros desembarcando
	public static void desambarcando() {
		
	}
}