package Janelas;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Programa.Aplicacao;
import Programa.Passageiro;

@SuppressWarnings("serial")
public class Canvas extends JPanel implements Runnable{

public PlanoDeFundo fundo; 	

	// Construtor está gerando a imagem completa: Fundo + Vagão + Passageiros!
	public Canvas() {
		fundo = new PlanoDeFundo();
		Thread gameloop = new Thread(this);
		gameloop.start();
	}

	@Override
	public void run () {
		while(true) {
			//atualiza a pintura chamando o paintComponent
			repaint();
		}
	}
	
	@Override
	//Metodo que pinta a tela
	public void paintComponent (Graphics g2) {
		//limpa tela sempre que essa funcao for chamada
		super.paintComponent(g2);
		
		Graphics2D g = (Graphics2D) g2.create();
		
		//pinta imagem de fundo	
		fundo.pinta(g);
		
		//pinta imagem dos passageiros
		for(Passageiro p : Aplicacao.identificador) {
			p.pinta(g);
		}
		
		//pinta imagem do vagão
		Aplicacao.v.pinta(g);
	}
}
