package Janelas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Programa.Vagao;

public class CriacaoVagao {

	public JFrame frmCriaoDeVago;
	private JTextField numeroDeCadeiras;
	private JTextField tempoDeViagem;
	JButton botao = new JButton("Criar vag�o");


	// Cria o frame da cria��o do vag�o.
	public CriacaoVagao(Vagao vagao) {
		inicializa(vagao);
	}

	// Inicializa o conte�do do frame.
	private void inicializa(Vagao vagao) {
		
		// Cria o frame e configura
		frmCriaoDeVago = new JFrame();
		frmCriaoDeVago.setResizable(false);
		frmCriaoDeVago.setBackground(Color.LIGHT_GRAY);
		frmCriaoDeVago.setTitle("Cria��o de Vag�o");
		frmCriaoDeVago.setBounds(100, 100, 316, 133);
		frmCriaoDeVago.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCriaoDeVago.getContentPane().setLayout(null);
		frmCriaoDeVago.setLocationRelativeTo(null);
		
		// Cria os textos da janela
		JLabel labelCadeiras = new JLabel("N�mero de cadeiras:");
		labelCadeiras.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelCadeiras.setBounds(10, 11, 152, 14);
		frmCriaoDeVago.getContentPane().add(labelCadeiras);
		
		JLabel labelTempoDeViagem = new JLabel("Tempo de viagem: ");
		labelTempoDeViagem.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelTempoDeViagem.setBounds(10, 36, 152, 14);
		frmCriaoDeVago.getContentPane().add(labelTempoDeViagem);
		
		// Cria��o e configura��o das caixas de texto
		numeroDeCadeiras = new JTextField();
		numeroDeCadeiras.addKeyListener(new KeyAdapter() {
			@Override
			// passa para a pr�xima op��o com enter
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {      
					tempoDeViagem.requestFocus();
				}
			}
		});
		numeroDeCadeiras.setBounds(172, 10, 86, 20);
		frmCriaoDeVago.getContentPane().add(numeroDeCadeiras);
		numeroDeCadeiras.setColumns(10);
		
		tempoDeViagem = new JTextField();
		tempoDeViagem.addKeyListener(new KeyAdapter() {
			@Override
			// passa para a pr�xima op��o com enter
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					botao.requestFocus();
				}
			}
		});
		tempoDeViagem.setBounds(172, 35, 86, 20);
		frmCriaoDeVago.getContentPane().add(tempoDeViagem);
		tempoDeViagem.setColumns(10);
		
		// Cria��o do bot�o e suas configura��es
		botao.setBackground(new Color(0, 204, 153));
		botao.setForeground(Color.WHITE);
		botao.setFont(new Font("Tahoma", Font.BOLD, 14));
		botao.setBounds(10, 61, 129, 23);
		frmCriaoDeVago.getContentPane().add(botao);
		botao.addKeyListener(new KeyAdapter() {
			@Override
			// aciona o botao com o enter
			public void keyPressed(KeyEvent e) {				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {	
					botao.doClick();
				}
			}
		});
		botao.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		       int decimal;
		    	
		       vagao.quantidadeDecadeiras = Integer.parseInt(numeroDeCadeiras.getText());
		       vagao.tempoDeViagem = Integer.parseInt(tempoDeViagem.getText());
		       
		       /* 2254 - vagao.posx � a distancia percorrida pelo vag�o
		          20 * tempoDeViagem j� � o tempo convertido para segundos*/
		       vagao.velocidade =  (int) (2254 - vagao.posx) / (20 * vagao.tempoDeViagem);
		       
		       decimal = (2254 - vagao.posx) % (20 * vagao.tempoDeViagem);
		       vagao.resto = decimal;
		       
		       frmCriaoDeVago.setVisible(false);
		       frmCriaoDeVago.dispose();
		       Animacao telaPrincipal = new Animacao();		       
		       telaPrincipal.frameAnimacao.setVisible(true);
		       vagao.start();
		       String pronto = "Vag�o foi criado com " + vagao.quantidadeDecadeiras + " cadeiras.\n";
		       Animacao.textArea.append(pronto);
		       
		    }
		});
		
	}
}
