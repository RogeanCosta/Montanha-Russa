package Programa;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Janelas.Animacao;

public class Vagao extends Thread{

	public int tempoDeViagem;
	public int quantidadeDecadeiras;
	
	public int velocidade = 5;
	public int velocidadeInv = -15;
	//JLabel lCarro = new JLabel(new ImageIcon(getClass().getResource("imagens/carrinho.png")));
	BufferedImage imagem;
	BufferedImage imagemInvertida;
	public int posx = 0;
	public int posy = 370;
	public boolean status = false;
	public int direcao = 0;
	
	
	@Override
	public void run(){
		while(true) {
			
			String texto = "Vagão esperando embarque.\n";
			Animacao.textArea.append(texto);
			
			try {
				Aplicacao.lotado.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Aplicacao.preparativos.release(quantidadeDecadeiras);

			texto = "Vagão viajando.\n";
			Animacao.textArea.append(texto);
			
			percorre(this);
			
			texto = "Vagão esperando desembarque.\n";
			Animacao.textArea.append(texto);
		}

	}
	
	
	// Metodo de animacao para percorrer montanha:
	public static void percorre(Vagao v) {
		try{
			sleep(20);
		} catch(Exception e) {					
		}
		
		if (v.posx < 777 && v.direcao == 0) {    		//Enquanto não saiu da tela vai para frente
			v.posx += v.velocidade;
		}else if (v.direcao == 1 && v.posx > -350){		//Enquanto não não saiu da tela anda para esquerda
			v.posx -= v.velocidade;
		}else if (v.posx >= 777) {				 		//Assim que sair totalmente da tela altera a direcao
			v.direcao = 1;
		}else {											//Assim que sai da tela pela esquerda altera a direcao
			v.direcao = 0;
		}
		
	}
	
	
	public void pinta(Graphics2D g) {
			if (direcao == 0) {
				g.drawImage(imagem, posx, posy, imagem.getWidth(), imagem.getHeight(), null);
			}else if (direcao == 1) {
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
		
		//configuraJanela();
		//new Movimento().start();
	}
	
//	public class Movimento extends Thread {
//			
//			int x = 0;
//			
//			@Override
//			public void run() {
//				while(true) {
//					try{
//						sleep(20);
//					} catch(Exception e) {					
//					}
					
					//if(x == 0 && lCarro.getX() < 1365)
					//	lCarro.setBounds(lCarro.getX() + velocidade,250, 204, 68);
					
//					else if(x == 1 && carroInv.getX() > -204){
//						if(x == 1) {
//							add(carroInv);
//							x++;
//						}
//						
//						carroInv.setBounds(carroInv.getX() - velocidade, 350, 204, 68);
//					}
//					else if(lCarro.getX() >= 1365 && carroInv.getX() > -204) {
//						x = 1;
//					}
//					else {
//						lCarro.setBounds(-204, 250, 204, 68);
//						carroInv.setBounds(1367, 350, 204, 68);
//						x = 0;
					//}
					
			//	}
			//}
	
}
