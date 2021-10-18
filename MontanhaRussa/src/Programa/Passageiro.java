package Programa;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import Janelas.Animacao;

public class Passageiro extends Thread {

	// Atributos caracter�sticos do passageiro
	public int tempoEmbarque;
	public int tempoDesembarque;
	
	// Atributo que vai armazenar conte�do a ser impresso no log
	public String texto;
	
	// Vag�o que est� nos trilhos
	public Vagao vagao;

	// Referente ao conjunto de imagens que conferem a movimenta��o
	public BufferedImage personagemAndando[];
	public BufferedImage personagemRespirando[];
	public BufferedImage idImagem;

	// Referente a movimenta��o
	public int indiceImagem = 0;
	public int status = 0;
	public int direcao = 0;
	
	// valores iniciais de posx e posy refere-se a posi��o aonde o passageiro "Nasce"
	// Tamb�m utilizados para anima��o
	public int posx = 760;
	public int posy = 540;
	
	// utilizados para pegar os �ndices do vetor referente a posi��o da cadeira
	public int cadeiraPassageiroInvertido;
	public int cadeiraPassageiro;
	
	// Array com cada uma das posi��es da fila e um Array para organizar e atualizar a fila
	public static int []posFila = {60, 120, 180, 240, 300, 360, 420, 480, 540, 600};
	public static LinkedList<Passageiro> ordemFila = new LinkedList<Passageiro>();

	
	@Override
	public void run() {
		while(true) {
			Aplicacao.downFila();
			entrarNaFila();
			Aplicacao.upFila();
			
			texto = String.format("Passageiro %d est� esperando na fila.\n", (Aplicacao.identificador.indexOf(this)+1));
			Animacao.textArea.append(texto);
			
			// Down vag�o
			Aplicacao.downVagao();
			
			// Down mutex
			Aplicacao.downMutex();
			
			Aplicacao.cadeirasOcupadas++;
			
			embarca();
			atualizaFila();
			
			if(Aplicacao.cadeirasOcupadas == Aplicacao.v.quantidadeDecadeiras) {
				Aplicacao.upLotado();
			} else {
				Aplicacao.upVagao();
			}
		
			Aplicacao.upMutex();
			
			// Prepara��o para viagem
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
	
	public void entrarNaFila() {
		ordemFila.add(this);		
		organizaFila(ordemFila.size() - 1);
	}
	
	// Define aonde cada passageiro vai entrar na fila
	public void organizaFila(int posicao) {
		while (posx >= (posFila[posicao])) {
			Aplicacao.tempoDelay(50);
			
			posx -= 10;
			indiceImagem++;
			
			if (indiceImagem == 20) {
				indiceImagem = 0;
			}
		}
	}
	
	// Utilizado pelo atualizaFila, para recalcular a posi��o de cada um na fila
	public void organizaFila(int posicao, Passageiro p) {
		
		while (p.posx >= (p.posFila[posicao])) {
			Aplicacao.tempoDelay(50);
			
			p.posx -= 10;
			p.indiceImagem++;
			
			if (p.indiceImagem == 20) {
				p.indiceImagem = 0;
			}
		}
	}
	
	// A cada passageiro que embarca, fila vai reorganizar
	public void atualizaFila() {
	
		ordemFila.removeFirst();
		
		for(int i = 0; i < ordemFila.size(); i++) {
			organizaFila(i, ordemFila.get(i));
		}
	}
	
	// M�todo com a anima��o dos passageiros embarcando
	public void embarca() {
		int velocidadeEmbarque;
		int resto;
				
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
		
		// C�lculo da velocidade de acordo com a quantidade de passos a serem dados
		// e de acordo tamb�m com a posi��o final que ele dever ir!
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
			
			Aplicacao.tempoDelay(50);
			
			tempo = (int)(fim - inicio)/1000; 
			Animacao.cronometro.setText(String.format("%d", tempo)); 
			
			fim = System.currentTimeMillis();
			
			/* Este conjunto de condi��es s�o para: 
			 * 1) Subir at� determinada posi��o y
			 * 2) Andar at� em "frente" da cadeira que vai sentar
			 * 3) "Subir" at� a cadeira
			 */
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
		
		Aplicacao.tempoDelay(500);
			
		status = 1;
		indiceImagem = 0;
	
	}
	
	// M�todo com a anima��o do passageiros se divertindo
	public void viajando() {
		texto = String.format("Passageiro %d est� apreciando paisagem.\n", (Aplicacao.identificador.indexOf(this)+1));
		Animacao.textArea.append(texto);
		
		do {
			Aplicacao.tempoDelay(50);
			
			if (vagao.direcao == 1) { // Enquanto n�o saiu da tela anda para esquerda
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
			System.out.print(""); // importante!!!! N�O RETIRAR NUNCA!
		} while (vagao.parou == false);
		
		
		// Esperar vag�o parar
		Aplicacao.tempoDelay(1000);
	}
	
	// M�todo com a anima��o dos passageiros desembarcando
	public void desambarcando() {
		int velocidadeDesembarque;
		int resto = 0;
		
		texto = String.format("Passageiro %d est� desembarcando.\n", 
				(Aplicacao.identificador.indexOf(this)+1));
		Animacao.textArea.append(texto);
		
		status = 0;
		indiceImagem = 0;
		
		long inicio = System.currentTimeMillis();
		int tempo = (int) (System.currentTimeMillis() - inicio) / 1000;
		
		// C�lculo da velocidade de desembarque que depende da quantidade de passos
		// e tamb�m leva em conta o tempo de desembarque
		velocidadeDesembarque = (int) (890 + vagao.posx - vagao.posCadeiras[cadeiraPassageiro]) 
				/ (20 * tempoDesembarque);
		
		resto = (890 + vagao.posx - vagao.posCadeiras[cadeiraPassageiro]) 
				% (20 * tempoDesembarque);
		
		// Anima��o do desembarque acontecendo
		do {
			Aplicacao.tempoDelay(50);
			
			indiceImagem++;
			
			if (indiceImagem == 20) {
				indiceImagem = 0;
			}
			
			Animacao.cronometro.setText(String.format("%d", tempo));
			tempo = (int)(System.currentTimeMillis() - inicio) / 1000;
			
			// Este conjunto de condi��es � para
			// 1) Passageiro descer do vag�o
			// 2) Passageiro caminhar at� a sa�da do brinquendo
			// 3) Passageiro sair do brinquedo e se preparar para voltar da fila
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
		Aplicacao.tempoDelay(500);
		
		// Ajuste para a posi��o final
		posx = 700;
		posy = 540;
	}
	
	// pinta cada um dos frames referente ao personagem respirando/andando + seu id
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
		
		//carrega imagem do id
		imagem = "/imagens/passageiro/(" + id +").png";
		try {
			idImagem = ImageIO.read(getClass().getResource(imagem));
		} catch (IOException e1) {
			System.out.println("N�o conseguiu carregar a imagem do id passageiro!");
		}
		
		//carrega imagens do passageiro andando
		for (int i = 0; i < 20; i++) {
			try {				
				imagem = "/imagens/passageiro/Walk (" + (i+1) + ").png";
				personagemAndando[i] = ImageIO.read(getClass().getResource(imagem));
			} catch (IOException e) {
				System.out.println("N�o foi poss�vel carregar passageiro andando!!");
				e.printStackTrace();
			}			
		}
		
		//carrega imagens do passageiro respirando
		for (int i = 0; i < 16; i++) {
			try {
				imagem = "/imagens/passageiro/Idle (" + (i+1) + ").png";
				personagemRespirando[i] = ImageIO.read(getClass().getResource(imagem));
			} catch (IOException e) {
				System.out.println("N�o foi poss�vel carregar passageiro respirando!!");
				e.printStackTrace();
			}
		}
	}
}
