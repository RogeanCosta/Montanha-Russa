package Programa;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Janelas.Animacao;

public class Vagao extends Thread {

	public int tempoDeViagem;
	public int quantidadeDecadeiras;

	public int velocidade = 1;
	public int resto;
	BufferedImage imagem;
	BufferedImage imagemInvertida;
	public int posx = 0;
	public int posy = 370;
	public boolean status = false;
	public int direcao = 0;
	public static String texto;

	public static void VagaoEspera() {
		Aplicacao.downLotado();
	}

	@Override
	public void run() {
		while (true) {

			esperandoEmbarque();

			Aplicacao.downLotado();

			Aplicacao.upPreparativos(quantidadeDecadeiras);

			texto = "Vag�o viajando.\n";
			Animacao.textArea.append(texto);

			percorre(this);

			esperandoDesembarque();
		}

	}

	// M�todo de Anima��o para percorrer a Montanha
	public static void percorre(Vagao v) {
		
		long inicio = System.currentTimeMillis(); 
		long fim = System.currentTimeMillis(); 
		int tempo; 
		
		do {
			tempo = (int)(fim - inicio)/1000; 
			Animacao.cronometro.setText(String.format("%d", tempo)); 			
			
			// Temporizador
			long I = System.currentTimeMillis();
			while (System.currentTimeMillis() - I < 50) {
			}

			System.out.println(v.velocidade + " " + v.resto + " " + v.posx);
			
			if (v.posx < 777 && v.direcao == 0) { // Enquanto n�o saiu da tela vai para frente
				v.posx += v.velocidade;
				if(v.resto != 0) {
					v.posx += 1;
					v.resto--;
				}
			} else if (v.direcao == 1 && v.posx > -350) { // Enquanto n�o n�o saiu da tela anda para esquerda
				v.posx -= v.velocidade;
			} else if (v.posx >= 777) { // Assim que sair totalmente da tela altera a direcao
				v.direcao = 1;
			} else { // Assim que sai da tela pela esquerda altera a direcao
				v.direcao = 0;
				v.status = true;
			}

			 fim = System.currentTimeMillis(); 
			 tempo = (int)(fim - inicio)/1000; 
			 Animacao.cronometro.setText(String.format("%d", tempo)); 
		
		} while (v.status == false || v.posx < 0);

		// Animacao.textArea.append(String.format("%d", tempo)); 
		v.status = false;
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
			System.out.println("N�o foi poss�vel caregar o plado de fundo!!");
			e.printStackTrace();
		}

		try {
			imagemInvertida = ImageIO.read(new File("imagens/carrinhoInvertido.png"));
		} catch (IOException e) {
			System.out.println("N�o foi poss�vel caregar o plado de fundo!!");
			e.printStackTrace();
		}

		// configuraJanela();
		// new Movimento().start();
	}
	
	public static void esperandoEmbarque() {
		try {
			sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		texto = "Vag�o esperando embarque.\n";
		Animacao.textArea.append(texto);
	}
	
	public static void esperandoDesembarque() {
		try {
			sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		texto = "Vag�o esperando desembarque.\n";
		Animacao.textArea.append(texto);
	}

}
