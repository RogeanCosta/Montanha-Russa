package Programa;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
	public int posy = 385;
	public boolean status = false;
	public int direcao = 0;
	public static String texto;
	public static int []posCadeiras = {295, 265, 225, 195, 155, 125, 90, 60, 20, -10};
	public List<Passageiro> tempoDeDesembarque = new ArrayList<Passageiro>(quantidadeDecadeiras);
	
	public boolean parou;

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
			
			Aplicacao.downDesembarque();
		}

	}

	// M�todo de Anima��o para percorrer a Montanha
	public static void percorre(Vagao v) {
		
		long inicio = System.currentTimeMillis(); 
		long fim = System.currentTimeMillis(); 
		int tempo; 
		int decimal = (2254 - v.posx) % (20 * v.tempoDeViagem);
	    v.resto = decimal;
		v.parou = false;
		
		do {
			tempo = (int)(fim - inicio)/1000; 
			Animacao.cronometro.setText(String.format("%d", tempo)); 			
			
			// Temporizador
			long I = System.currentTimeMillis();
			while (System.currentTimeMillis() - I < 50) {
			}

//			System.out.println(v.velocidade + " " + v.resto + " " + v.posx);
			
			if (v.posx < 777 && v.direcao == 0) { // Enquanto nao saiu da tela vai para frente
				v.posx += v.velocidade;
				if(v.resto != 0) {
					v.posx += 1;
					v.resto--;
				}
			} else if (v.direcao == 1 && v.posx > -350) { // Enquanto nao saiu da tela (pela esquerda) anda continua andando
				v.posx -= v.velocidade;
				if(v.resto != 0) {
					v.posx -= 1;
					v.resto--;
				}
			} else if (v.posx >= 777) { // Assim que sair totalmente da tela altera a direcao
				v.direcao = 1;
			} else { 					// Assim que sai da tela pela esquerda altera a direcao
				v.direcao = 0;			// e altera status para true para poder parar o vagao 
				v.status = true;
			}

			 fim = System.currentTimeMillis(); 
			 tempo = (int)(fim - inicio)/1000; 
			 Animacao.cronometro.setText(String.format("%d", tempo)); 
		
		} while (v.status == false || v.posx < 0);

		v.parou = true;
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
	}
	
	public void esperandoEmbarque() {
		texto = "Vag�o esperando embarque.\n";
		Animacao.textArea.append(texto);
	}
	
	public static void esperandoDesembarque() {
		texto = "Vag�o esperando desembarque.\n";
		Animacao.textArea.append(texto);
	}

}
