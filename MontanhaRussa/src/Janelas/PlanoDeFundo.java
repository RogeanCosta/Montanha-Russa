package Janelas;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PlanoDeFundo {

	BufferedImage imagem;

	public PlanoDeFundo() {
		
		try {
			imagem = ImageIO.read(new File("imagens/teste.jpg"));
		} catch (IOException e) {
			System.out.println("Não foi possível caregar o plado de fundo!!");
			e.printStackTrace();
		}
		
	}
	
	
	public void pinta(Graphics2D g) {
	
		g.drawImage(imagem, 0, 0, imagem.getWidth(), imagem.getHeight(), null);
		
	}
	
	
	
}
