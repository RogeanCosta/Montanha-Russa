package Janelas;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PlanoDeFundo {

	BufferedImage imagem;

	
	// Este construtor carrega a imagem
	public PlanoDeFundo() {
		
		try {
			imagem = ImageIO.read(new File("imagens/fundo.png"));
		} catch (IOException e) {
			System.out.println("Não foi possível caregar o plado de fundo!!");
			e.printStackTrace();
		}	
	}
	
	// Este método pinta a imagem de background
	public void pinta(Graphics2D g) {
		g.drawImage(imagem, 0, -150, imagem.getWidth(), imagem.getHeight(), null);
	}
}
