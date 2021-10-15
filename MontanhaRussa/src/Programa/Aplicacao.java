package Programa;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import Janelas.CriacaoVagao;

public class Aplicacao {

	public static Semaphore preparativos = new Semaphore(0);
	public static Semaphore mutex = new Semaphore(1, true);
	public static Semaphore fila = new Semaphore(1, true);
	public static Semaphore vagao = new Semaphore(1, true);
	public static Semaphore lotado = new Semaphore(0);
	public static Semaphore desembarque = new Semaphore(0);
	public static int cadeirasOcupadas = 0;
	public static List<Passageiro> identificador = new ArrayList<Passageiro>();
	public static Vagao v;
	
	public static void tempoDelay(int tempo) {
		long I = System.currentTimeMillis();
		while (System.currentTimeMillis() - I < tempo) {
		}
	}
	
	public static void downVagao() {
		try {
			vagao.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void upVagao() {
		vagao.release();
	}
	
	public static void downMutex() {
		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void upMutex() {
		mutex.release();
	}
	
	public static void downLotado() {
		try {
			lotado.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void upLotado() {
		lotado.release();
	}
	
	public static void downPreparativos() {
		try {
			preparativos.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void upPreparativos() {
		preparativos.release();
	}
	
	public static void upPreparativos(int cadeiras) {
		preparativos.release(cadeiras);
	}
	
	public static void upDesembarque() {
		desembarque.release();
	}
	
	public static void downDesembarque() {
		try {
			desembarque.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void downFila( ) {
		try {
			fila.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void upFila() {
		fila.release();
	}
	
	public static void main(String[] args) {
		
		v = new Vagao();
		
		
		CriacaoVagao window = new CriacaoVagao(v);
		window.frmCriaoDeVago.setVisible(true);
		
		
		
		while(true) {
			//System.out.println("Numero de passageiros: " + identificador.size());
			
		}
		
		
	}
}
