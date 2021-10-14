package Programa;

import Janelas.Animacao;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Passageiro extends Thread {

	public int tempoEmbarque;
	public int tempoDesembarque;
	public String texto;
	public Vagao vagao;
	public BufferedImage personagemAndando[];
	public BufferedImage personagemRespirando[];
	public int indiceImagem = 0;
	public int status = 0;
	public int direcao = 0;
	public int posx = 760;
	public int posy = 540;
	public int cadeiraPassageiroInvertido;
	public int cadeiraPassageiro;
	public static int posicao;
	public static int []posFila = {60, 120, 180, 240, 300, 360, 420, 480, 540, 600};
	
	@Override
	public void run() {
		while(true) {
			Aplicacao.downFila();
			entrarNaFila();
			Aplicacao.upFila();
			
			// Colocar pr�ximas duas instru��es dentro do embarca!
			texto = String.format("Passageiro %d est� esperando na fila.\n", (Aplicacao.identificador.indexOf(this)+1));
			Animacao.textArea.append(texto);
			
			// Down vag�o
			Aplicacao.downVagao();
			
			// Down mutex
			Aplicacao.downMutex();
			
			Aplicacao.cadeirasOcupadas++;
			
			// Aqui vem o m�todo Embarca()
			embarca();
			
			if(Aplicacao.cadeirasOcupadas == Aplicacao.v.quantidadeDecadeiras) {
				Aplicacao.upLotado();
			} else {
				Aplicacao.upVagao();
			}
		
			Aplicacao.upMutex();
			
			// Prepara��o para viagem
			Aplicacao.downPreparativos();
			
			// Aqui vem o m�todo viajando
			viajando();
		
			// Desembarque vai acontecer
			Aplicacao.downMutex();
			
			desambarcando();
			
			Aplicacao.cadeirasOcupadas--;
			
			if(Aplicacao.cadeirasOcupadas == 0) {
				Aplicacao.upDesembarque();
				Aplicacao.upVagao();
			}
			
			Aplicacao.upMutex();
		}
			
	}
	
	public void organizaFila(int posicao) {
		while (posx != (posFila[posicao])) {
			long I = System.currentTimeMillis();
			while (System.currentTimeMillis() - I < 50) {
			}
			
			posx -= 10; // tem que ser 5
			indiceImagem++;
			
			if (indiceImagem == 20) {
				indiceImagem = 0;
			}
		}
	}
	
	// M�todo com a anima��o dos passageiros embarcando
	public void embarca() {
		int velocidadeEmbarque;
		int resto;
		
		posicao--;
		
		organizaFila(0);
		direcao = 1;
		
		texto = String.format("Passageiro %d est� embarcando.\n", (Aplicacao.identificador.indexOf(this)+1));
		Animacao.textArea.append(texto);
		
		long inicio = System.currentTimeMillis(); 
		long fim = System.currentTimeMillis(); 
		int tempo = (int)(fim - inicio)/1000;
		int cont = 0;
		
		for (int i = 0; i < Aplicacao.identificador.size(); i++) {
			if (Aplicacao.identificador.get(i).status == 1) {
				cont++;
			}
		}
		
		if (cont < 8) {
			velocidadeEmbarque = (int) (125 + vagao.posCadeiras[cont] + vagao.posx) 
					/ (20 * tempoEmbarque);
			
			resto = (125 + vagao.posCadeiras[cont] + vagao.posx) % (20 * tempoEmbarque);			
		} else {
			velocidadeEmbarque = (int) (245 - (vagao.posCadeiras[cont] + vagao.posx)) 
					/ (20 * tempoEmbarque);
			
			resto = (245 - (vagao.posCadeiras[cont] + vagao.posx)) % (20 * tempoEmbarque);
		}
		
		cadeiraPassageiroInvertido = 9 - cont;
		cadeiraPassageiro = cont;
		
		do {
			indiceImagem++;
			
			if (indiceImagem == 20) {
				indiceImagem = 0;
			}
			
			long I = System.currentTimeMillis();
			while (System.currentTimeMillis() - I < 50) {
			}
			
			tempo = (int)(fim - inicio)/1000; 
			Animacao.cronometro.setText(String.format("%d", tempo)); 
			
			fim = System.currentTimeMillis();
			
			if (posy > 430) {
				if(resto != 0) {
					posy -= 1;
					resto--;
				}
				posy -= velocidadeEmbarque;
			} else if (posx < vagao.posCadeiras[cont] + vagao.posx && cont < 8) {
				if(resto != 0) {
					posx += 1;
					resto--;
				}
				posx += velocidadeEmbarque;
			} else if (posx > vagao.posCadeiras[cont] + vagao.posx && cont > 7) {
				if(resto != 0) {
					posx -= 1;
					resto--;
				}
				posx -= velocidadeEmbarque;
			} else if(posy >= 300) {
				if(resto != 0) {
					posy -= 1;
					resto--;
				}
				posy -= velocidadeEmbarque;
			}
			
		} while (tempo < tempoEmbarque);
		
		Animacao.cronometro.setText(String.format("%d", tempo));
		
		long I = System.currentTimeMillis();
		while (System.currentTimeMillis() - I < 500) {
		}
			
		status = 1;
		indiceImagem = 0;
	
	}
	
	// M�todo com a anima��o do passageiros se divertindo
	public void viajando() {
		texto = String.format("Passageiro %d est� apreciando paisagem.\n", (Aplicacao.identificador.indexOf(this)+1));
		Animacao.textArea.append(texto);
		
		do {
			long I = System.currentTimeMillis();
			while ((System.currentTimeMillis() - I) < 50) {
			}
			
			if (vagao.direcao == 1) { // Enquanto n�o n�o saiu da tela anda para esquerda
				posx = vagao.posx + vagao.posCadeiras[cadeiraPassageiroInvertido];
				direcao = 0;
			} else if (vagao.direcao == 0) { // Enquanto n�o saiu da tela vai para frente
				posx  = vagao.posx + vagao.posCadeiras[cadeiraPassageiro];
				direcao = 1;
			}
			
			indiceImagem++;
			
			if (indiceImagem == 16) {
				indiceImagem = 0;
			}
			System.out.print(""); // importante!!!! NÃO RETIRAR NUNCA!
		} while (vagao.parou == false);
		
		
		// Esperar vagão parar
		long I = System.currentTimeMillis();
		while ((System.currentTimeMillis() - I) / 1000 <= 1) {
		}
	}
	
	// M�todo com a anima��o dos passageiros desembarcando
	public void desambarcando() {
		int tempoAguardo = 0;
		int velocidadeDesembarque;
		int resto = 0;
		
		texto = String.format("Passageiro %d est� desembarcando.\n", 
				(Aplicacao.identificador.indexOf(this)+1));
		Animacao.textArea.append(texto);
		
		for (int i = Aplicacao.identificador.indexOf(this); i > 0; i--) {
			tempoAguardo += Aplicacao.identificador.get(i).tempoDesembarque;
		}
		
		status = 0;
		indiceImagem = 0;
		
		
		long inicio = System.currentTimeMillis();
		int tempo = (int) (System.currentTimeMillis() - inicio) / 1000;
		
		velocidadeDesembarque = (int) (890 + vagao.posx - vagao.posCadeiras[cadeiraPassageiro]) 
				/ (20 * tempoDesembarque);
		
		resto = (890 + vagao.posx - vagao.posCadeiras[cadeiraPassageiro]) 
				% (20 * tempoDesembarque);
		
		do {
			long I = System.currentTimeMillis();
			while (System.currentTimeMillis() - I < 50) {
			}
			
			indiceImagem++;
			
			if (indiceImagem == 20) {
				indiceImagem = 0;
			}
			
			Animacao.cronometro.setText(String.format("%d", tempo));
			tempo = (int)(System.currentTimeMillis() - inicio) / 1000;
			
			if (posy < 430) {
				if(resto != 0) {
					posy += 1;
					resto--;
				}
				posy += velocidadeDesembarque;
			} else if (posx < 700) {
				if(resto != 0) {
					posx += 1;
					resto--;
				}
				posx += velocidadeDesembarque;
			} else if(posy <= 535) {
				direcao = 0;
				if(resto != 0) {
					posy += 1;
					resto--;
				}
				posy += velocidadeDesembarque;
			}
			
		} while (tempo < tempoDesembarque);
		
		Animacao.cronometro.setText(String.format("%d", tempo));
		long I = System.currentTimeMillis();
		while (System.currentTimeMillis() - I < 500) {
		}
		
		posx = 700;
		posy = 540;
	}
	
	public void entrarNaFila() {
		int cont = 0;
		
		for (int i = 0; i < Aplicacao.identificador.size() - 1; i++) {
			if (Aplicacao.identificador.get(i).status == 0) {
				cont++;
			}
		}
		
		organizaFila(cont);
		posicao++;
	}

	public void pinta(Graphics2D g) {
		if (status == 0) {
			if (direcao == 0) {
				g.drawImage(personagemAndando[indiceImagem], posx, posy, posx + 60, posy + 60, 
						personagemAndando[indiceImagem].getWidth(), 0, 0, 
						personagemAndando[indiceImagem].getHeight(), null);				
			} else if (direcao == 1) {
				g.drawImage(personagemAndando[indiceImagem], posx, posy, posx + 60, posy + 60, 
						0, 0, personagemAndando[indiceImagem].getWidth(), 
						personagemAndando[indiceImagem].getHeight(), null);	
			}
		} else if (status == 1) {
			if (direcao == 0) {
				g.drawImage(personagemRespirando[indiceImagem], posx, posy, posx + 60, posy + 60, 
						personagemRespirando[indiceImagem].getWidth(), 0, 0, 
						personagemRespirando[indiceImagem].getHeight(), null);				
			} else if (direcao == 1) {
				g.drawImage(personagemRespirando[indiceImagem], posx, posy, posx + 60, posy + 60, 
						0, 0, personagemRespirando[indiceImagem].getWidth(), 
						personagemRespirando[indiceImagem].getHeight(), null);	
			}
		}

	}
	
	public Passageiro() {
		personagemAndando = new BufferedImage[20];
		personagemRespirando = new BufferedImage[16];
		String imagem;
		
		
		for (int i = 0; i < 20; i++) {
			try {				
				imagem = "imagens/passageiro/Walk ("+ (i+1)+").png";
				personagemAndando[i] = ImageIO.read(new File(imagem));
			} catch (IOException e) {
				System.out.println("N�o foi poss�vel carregar passageiro andando!!");
				e.printStackTrace();
			}			
		}
		
		for (int i = 0; i < 16; i++) {
			try {
				imagem = "imagens/passageiro/Idle ("+ (i+1)+").png";
				personagemRespirando[i] = ImageIO.read(new File(imagem));
			} catch (IOException e) {
				System.out.println("N�o foi poss�vel carregar passageiro respirando!!");
				e.printStackTrace();
			}
		}
	}
}
