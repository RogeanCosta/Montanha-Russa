package Programa;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Janelas.Animacao;

public class Vagao extends Thread {

	// Atributos Característicos do Vagão
	public int tempoDeViagem;
	public int quantidadeDecadeiras;
	
	// Referente a movimentação por tempo do Vagão
	public int velocidade;
	public int resto;
	
	// Referente a imagens
	BufferedImage imagem;
	BufferedImage imagemInvertida;
	
	// Valores iniciais de posx, posy refere-se a posição em que "Nasce"
	public int posx = 0;
	public int posy = 385;
	
	// Referente a movimentação do vagão  
	public boolean status = false;
	public int direcao = 0;
	public boolean parou;
	
	public static String texto;
	
	// Referente a posição das cadeiras
	public static int []posCadeiras = {295, 265, 225, 195, 155, 125, 90, 60, 20, -10};
	

	@Override
	public void run() {
		while (true) {

			esperandoEmbarque();

			Aplicacao.downLotado();

			Aplicacao.upPreparativos(quantidadeDecadeiras);

			texto = "Vagão viajando.\n";
			Animacao.textArea.append(texto);

			percorre(this);

			esperandoDesembarque();
			
			Aplicacao.downDesembarque();
		}

	}

	// Método de Animação para percorrer a Montanha
	public static void percorre(Vagao v) {
		
		long inicio = System.currentTimeMillis(); 
		long fim = System.currentTimeMillis(); 
		int tempo; 
		
		// resto dá um boost no vagão adicional pela parte decimal
		// perdida no cálculo da velocidade
		v.resto = (2254 - v.posx) % (20 * v.tempoDeViagem);
		
		// false = Não parou
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

	// Logo na construção do vagão, as imagens são carregadas!
	public Vagao() {

		try {
			imagem = ImageIO.read(getClass().getResource("/imagens/carrinho.png"));
		} catch (IOException e) {
			System.out.println("Não foi possível carregar o carrinho!!");
			e.printStackTrace();
		}

		try {
//			imagemInvertida = ImageIO.read(new File("imagens/carrinhoInvertido.png"));
			imagemInvertida = ImageIO.read(getClass().getResource("/imagens/carrinhoInvertido.png"));
		} catch (IOException e) {
			System.out.println("Não foi possível carregar o carrinho invertido!!");
			e.printStackTrace();
		}
	}
	
	public void esperandoEmbarque() {
		texto = "Vagão esperando embarque.\n";
		Animacao.textArea.append(texto);
	}
	
	public static void esperandoDesembarque() {
		texto = "Vagão esperando desembarque.\n";
		Animacao.textArea.append(texto);
	}

}
