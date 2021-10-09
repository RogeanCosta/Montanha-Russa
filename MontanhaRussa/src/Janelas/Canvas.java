package Janelas;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Programa.Aplicacao;
import Programa.Vagao;

public class Canvas extends JPanel implements Runnable{

public PlanoDeFundo fundo; 
//public Vagao carrinho;
	

	public Canvas() {
		fundo = new PlanoDeFundo();
		//carrinho = new Vagao();
		Thread gameloop = new Thread(this);
		gameloop.start();
	}



	@Override
	public void run () {   //isso é uma thread
		while(true) {
			//atualiza
			atualiza();
			
			//pinta
			repaint();  //chama o paintComponent
			
			//dorme
			//dorme();
		}
	}
	
	
	private void atualiza() {
		//atualiza o estado do jogo
	}
	
	
	//Função para controlar a velocidade
	private void dorme(){
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	//Metodo que pinta a tela
	@Override
	public void paintComponent (Graphics g2) {  //metodo coloca algo na tela para printar
		super.paintComponent(g2);  			   //limpa tela sempre que essa funcao for chamada
		
		Graphics2D g = (Graphics2D) g2.create();
		
		fundo.pinta(g);						   //pinta imagem de fundo
		Aplicacao.v.pinta(g);
		
	}
	
}
