package Programa;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import Janelas.CriacaoVagao;

public class Aplicacao {

	public static Semaphore preparativos = new Semaphore(0);
	public static Semaphore mutex = new Semaphore(1);
	public static Semaphore vagao = new Semaphore(1, true);
	public static Semaphore lotado = new Semaphore(0);
	public static int cadeirasOcupadas = 0;
	public static  List<Passageiro> identificador = new ArrayList<Passageiro>();
	public static Vagao v;
	
	public static void main(String[] args) {
		
		v = new Vagao();
		
		
		CriacaoVagao window = new CriacaoVagao(v);
		window.frmCriaoDeVago.setVisible(true);
		
		
		
		while(true) {
			//System.out.println("Numero de passageiros: " + identificador.size());
			
		}
		
		
	}
}
