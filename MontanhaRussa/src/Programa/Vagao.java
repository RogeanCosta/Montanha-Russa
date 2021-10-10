package Programa;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import Janelas.Animacao;

public class Vagao extends Thread {

	public int tempoDeViagem;
	public int quantidadeDecadeiras;

	public int velocidade = 15; 
	public int velocidadeInv = -15;
	// JLabel lCarro = new JLabel(new
	// ImageIcon(getClass().getResource("imagens/carrinho.png")));
	BufferedImage imagem;
	BufferedImage imagemInvertida;
	public int posx = 0;
	public int posy = 370;
	public boolean status = false;
	public int direcao = 0;

	// Referente a Delay
	int segundosRestantes = 15;
	Timer tempo = new Timer();
	
	TimerTask delay = new TimerTask() {
		
		@Override
		public void run() {
			if(tempoDeViagem-- > 0) {
				System.out.println(posx + " " + direcao + " " + status);
			
				if (posx < 777 && direcao == 0 ) {    		//Enquanto não saiu da tela vai para frente
					posx += velocidade;
	
				} else if (direcao == 1 && posx > -350){		//Enquanto não não saiu da tela anda para esquerda
					posx -= velocidade;
				
				}
			
				if (posx >= 777) {				 		//Assim que sair totalmente da tela altera a direcao
					direcao = 1;
				}
				
				if (posx <= -350){											//Assim que sai da tela pela esquerda altera a direcao
					direcao = 0;
					status = true;
					System.out.println("TESTEEE");
				}
			} else {
				delay.cancel();
			}
		}
	};
	
	
//	@Override
//	public void start() {
//		tempo.scheduleAtFixedRate(delay, 1000, 1000);
//	}
	
	public static void VagaoEspera() {
		Aplicacao.downLotado();
	}
	
	@Override
	public void run() {
		while (true) {

			String texto = "Vagão esperando embarque.\n";
			Animacao.textArea.append(texto);

			Aplicacao.downLotado();

			Aplicacao.upPreparativos(quantidadeDecadeiras);

			texto = "Vagão viajando.\n";
			Animacao.textArea.append(texto);

			percorre(this);

			Aplicacao.downLotado();
			texto = "Vagão esperando desembarque.\n";
			Animacao.textArea.append(texto);
			
		}

	}

	// Metodo de animacao para percorrer montanha:
	public static void percorre(Vagao v) {
//			try{
//				sleep(10);
//			} catch(Exception e) {					
//			}
			// v.start();
			v.velocidade = 1556 * 60 / v.tempoDeViagem;
		
			try {
				v.tempo.scheduleAtFixedRate(v.delay, 1000, 1000);
			} catch (Exception e) {
			}
		System.out.println("---------------------------------------");
	}

	public void pinta(Graphics2D g) {
		if (direcao == 0) {
			g.drawImage(imagem, posx, posy, imagem.getWidth(), imagem.getHeight(), null);
		} else if (direcao == 1) {
			g.drawImage(imagemInvertida, posx, posy, imagemInvertida.getWidth(), imagemInvertida.getHeight(), null);
		}

	}

	public Vagao() {

		try {
			imagem = ImageIO.read(new File("imagens/carrinho.png"));
		} catch (IOException e) {
			System.out.println("Não foi possível caregar o plado de fundo!!");
			e.printStackTrace();
		}

		try {
			imagemInvertida = ImageIO.read(new File("imagens/carrinhoInvertido.png"));
		} catch (IOException e) {
			System.out.println("Não foi possível caregar o plado de fundo!!");
			e.printStackTrace();
		}

		// configuraJanela();
		// new Movimento().start();
	}

}
