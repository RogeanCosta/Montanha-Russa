package Programa;

import Janelas.Animacao;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class Passageiro extends Thread {

	public int tempoEmbarque;
	public int tempoDesembarque;
	public String texto;
	public Vagao vagao;
	public BufferedImage personagemAndando[];
	public BufferedImage personagemRespirando[];
	public BufferedImage idImagem;
	public int indiceImagem = 0;
	public int status = 0;
	public int direcao = 0;
	public int posx = 760;
	public int posy = 540;
	public int cadeiraPassageiroInvertido;
	public int cadeiraPassageiro;
	public static int posicao;
	public static int []posFila = {60, 120, 180, 240, 300, 360, 420, 480, 540, 600};
	public int id;
	
	// Testando organização da fila!
	public static int organizou = 1;
	public static LinkedList<Passageiro> ordemFila = new LinkedList<Passageiro>();

	
	@Override
	public void run() {
		while(true) {
			Aplicacao.downFila();
			entrarNaFila();
			Aplicacao.upFila();
			
			// Colocar prï¿½ximas duas instruï¿½ï¿½es dentro do embarca!
			texto = String.format("Passageiro %d estï¿½ esperando na fila.\n", (Aplicacao.identificador.indexOf(this)+1));
			Animacao.textArea.append(texto);
			
			// Down vagão
			Aplicacao.downVagao();
			
			// Down mutex
			Aplicacao.downMutex();
			
			Aplicacao.cadeirasOcupadas++;
			
			embarca();
			ordemFila.removeFirst();

			for(int i = 0; i < ordemFila.size(); i++) {
				organizaFila(i, ordemFila.get(i));
			}
			
			if(Aplicacao.cadeirasOcupadas == Aplicacao.v.quantidadeDecadeiras) {
				Aplicacao.upLotado();
			} else {
				Aplicacao.upVagao();
			}
		
			Aplicacao.upMutex();
			
			// Preparação para viagem
			Aplicacao.downPreparativos();
			
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
		while (posx >= (posFila[posicao])) {
			long I = System.currentTimeMillis();
			while (System.currentTimeMillis() - I < 50) {
			}
			
			posx -= 10;
			indiceImagem++;
			
			if (indiceImagem == 20) {
				indiceImagem = 0;
			}
		}
	}
	
	public void organizaFila(int posicao, Passageiro p) {
		
		while (p.posx >= (p.posFila[posicao])) {
			long I = System.currentTimeMillis();
			while (System.currentTimeMillis() - I < 50) {
			}
			
			p.posx -= 10;
			p.indiceImagem++;
			
			if (p.indiceImagem == 20) {
				p.indiceImagem = 0;
			}
		}
	}
	
	// Método com a animação dos passageiros embarcando
	public void embarca() {
		int velocidadeEmbarque;
		int resto;
				
		direcao = 1;
		
		texto = String.format("Passageiro %d está embarcando.\n", (Aplicacao.identificador.indexOf(this)+1));
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
			velocidadeEmbarque = (int) (135 + vagao.posCadeiras[cont] + vagao.posx) 
					/ (20 * tempoEmbarque);
			
			resto = (135 + vagao.posCadeiras[cont] + vagao.posx) % (20 * tempoEmbarque);			
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
	
	// Método com a animação do passageiros se divertindo
	public void viajando() {
		texto = String.format("Passageiro %d está apreciando paisagem.\n", (Aplicacao.identificador.indexOf(this)+1));
		Animacao.textArea.append(texto);
		
		do {
			long I = System.currentTimeMillis();
			while ((System.currentTimeMillis() - I) < 50) {
			}
			
			if (vagao.direcao == 1) { // Enquanto não saiu da tela anda para esquerda
				posx = vagao.posx + vagao.posCadeiras[cadeiraPassageiroInvertido];
				direcao = 0;
			} else if (vagao.direcao == 0) { // Enquanto não saiu da tela vai para frente
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
	
	// Método com a animação dos passageiros desembarcando
	public void desambarcando() {
		int tempoAguardo = 0;
		int velocidadeDesembarque;
		int resto = 0;
		
		texto = String.format("Passageiro %d está desembarcando.\n", 
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
		ordemFila.add(this);		
		organizaFila(ordemFila.size() - 1);
	}

	public void pinta(Graphics2D g) {
		if (status == 0) {
			if (direcao == 0) {
				g.drawImage(personagemAndando[indiceImagem], posx, posy, posx + 60, posy + 60, 
						personagemAndando[indiceImagem].getWidth(), 0, 0, 
						personagemAndando[indiceImagem].getHeight(), null);	
				g.drawImage(idImagem, posx+20, posy + 5, 
						posx+20+10, posy + 20, 
						0, 0, idImagem.getWidth(), idImagem.getHeight(), null);
			} else if (direcao == 1) {
				g.drawImage(personagemAndando[indiceImagem], posx, posy, posx + 60, posy + 60, 
						0, 0, personagemAndando[indiceImagem].getWidth(), 
						personagemAndando[indiceImagem].getHeight(), null);
				g.drawImage(idImagem, posx+30, posy + 5, 
						posx+20+20, posy+20, 
						0, 0, idImagem.getWidth(), idImagem.getHeight(), null);
			}
		} else if (status == 1) {
			if (direcao == 0) {
				g.drawImage(personagemRespirando[indiceImagem], posx, posy, posx + 60, posy + 60, 
						personagemRespirando[indiceImagem].getWidth(), 0, 0, 
						personagemRespirando[indiceImagem].getHeight(), null);	
				g.drawImage(idImagem, posx+20, posy + 5, 
						posx+20+10, posy+20, 
						0, 0, idImagem.getWidth(), idImagem.getHeight(), null);
			} else if (direcao == 1) {
				g.drawImage(personagemRespirando[indiceImagem], posx, posy, posx + 60, posy + 60, 
						0, 0, personagemRespirando[indiceImagem].getWidth(), 
						personagemRespirando[indiceImagem].getHeight(), null);	
				g.drawImage(idImagem, posx+30, posy + 5, 
						posx+20+20, posy+20,
						0, 0, idImagem.getWidth(), idImagem.getHeight(), null);
			}
		}

	}
	
	public Passageiro(int id) {
		personagemAndando = new BufferedImage[20];
		personagemRespirando = new BufferedImage[16];
		String imagem;
		
		this.id = id;
		System.out.println("ID: " + id);
		
		//carrega imagem do id
		imagem = "imagens/passageiro/(" + id + ").png";
		try {
			idImagem = ImageIO.read(new File(imagem));
		} catch (IOException e1) {
			System.out.println("Não conseguiu carregar a imagem");
		}
		
		//carrega imagens do passageiro andando
		for (int i = 0; i < 20; i++) {
			try {				
				imagem = "imagens/passageiro/Walk ("+ (i+1)+").png";
				personagemAndando[i] = ImageIO.read(new File(imagem));
			} catch (IOException e) {
				System.out.println("Não foi possível carregar passageiro andando!!");
				e.printStackTrace();
			}			
		}
		
		//carrega imagens do passageiro respirando
		for (int i = 0; i < 16; i++) {
			try {
				imagem = "imagens/passageiro/Idle ("+ (i+1)+").png";
				personagemRespirando[i] = ImageIO.read(new File(imagem));
			} catch (IOException e) {
				System.out.println("Não foi possível carregar passageiro respirando!!");
				e.printStackTrace();
			}
		}
	}
}
