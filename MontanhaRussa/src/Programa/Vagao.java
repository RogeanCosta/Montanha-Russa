package Programa;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Janelas.Animacao;

public class Vagao extends Thread {

	// Atributos Caracter�sticos do Vag�o
	public int tempoDeViagem;
	public int quantidadeDecadeiras;
	
	// Referente a movimenta��o por tempo do Vag�o
	public int velocidade;
	public int resto;
	
	// Referente a imagens
	BufferedImage imagem;
	BufferedImage imagemInvertida;
	
	// Valores iniciais de posx, posy refere-se a posi��o em que "Nasce"
	public int posx = 0;
	public int posy = 385;
	
	// Referente a movimenta��o do vag�o  
	public boolean status = false;
	public int direcao = 0;
	public boolean parou;
	
	public static String texto;
	
	// Referente a posi��o das cadeiras
	public static int []posCadeiras = {295, 265, 225, 195, 155, 125, 90, 60, 20, -10};
	

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
		
		// resto d� um boost no vag�o adicional pela parte decimal
		// perdida no c�lculo da velocidade
		v.resto = (2254 - v.posx) % (20 * v.tempoDeViagem);
		
		// false = N�o parou
		v.parou = false;
		
		do {
			tempo = (int)(fim - inicio)/1000; 
			Animacao.cronometro.setText(String.format("%d", tempo)); 			
			
			// Temporizador
			Aplicacao.tempoDelay(50);

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

	// Logo na constru��o do vag�o, as imagens s�o carregadas!
	public Vagao() {

		try {
			imagem = ImageIO.read(getClass().getResource("/imagens/carrinho.png"));
		} catch (IOException e) {
			System.out.println("N�o foi poss�vel carregar o carrinho!!");
			e.printStackTrace();
		}

		try {
//			imagemInvertida = ImageIO.read(new File("imagens/carrinhoInvertido.png"));
			imagemInvertida = ImageIO.read(getClass().getResource("/imagens/carrinhoInvertido.png"));
		} catch (IOException e) {
			System.out.println("N�o foi poss�vel carregar o carrinho invertido!!");
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
