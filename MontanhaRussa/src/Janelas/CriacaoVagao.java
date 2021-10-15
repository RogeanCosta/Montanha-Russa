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
	JButton botao = new JButton("Criar vagão");


	// Cria o frame da criação do vagão.
	public CriacaoVagao(Vagao vagao) {
		inicializa(vagao);
	}

	// Inicializa o conteúdo do frame.
	private void inicializa(Vagao vagao) {
		
		// Cria o frame e configura
		frmCriaoDeVago = new JFrame();
		frmCriaoDeVago.setResizable(false);
		frmCriaoDeVago.setBackground(Color.LIGHT_GRAY);
		frmCriaoDeVago.setTitle("Criação de Vagão");
		frmCriaoDeVago.setBounds(100, 100, 316, 133);
		frmCriaoDeVago.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCriaoDeVago.getContentPane().setLayout(null);
		frmCriaoDeVago.setLocationRelativeTo(null);
		
		// Cria os textos da janela
		JLabel labelCadeiras = new JLabel("Número de cadeiras:");
		labelCadeiras.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelCadeiras.setBounds(10, 11, 152, 14);
		frmCriaoDeVago.getContentPane().add(labelCadeiras);
		
		JLabel labelTempoDeViagem = new JLabel("Tempo de viagem: ");
		labelTempoDeViagem.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelTempoDeViagem.setBounds(10, 36, 152, 14);
		frmCriaoDeVago.getContentPane().add(labelTempoDeViagem);
		
		// Criação e configuração das caixas de texto
		numeroDeCadeiras = new JTextField();
		numeroDeCadeiras.addKeyListener(new KeyAdapter() {
			@Override
			// passa para a próxima opção com enter
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
			// passa para a próxima opção com enter
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					botao.requestFocus();
				}
			}
		});
		tempoDeViagem.setBounds(172, 35, 86, 20);
		frmCriaoDeVago.getContentPane().add(tempoDeViagem);
		tempoDeViagem.setColumns(10);
		
		// Criação do botão e suas configurações
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
		       
		       /* 2254 - vagao.posx é a distancia percorrida pelo vagão
		          20 * tempoDeViagem já é o tempo convertido para segundos*/
		       vagao.velocidade =  (int) (2254 - vagao.posx) / (20 * vagao.tempoDeViagem);
		       
		       decimal = (2254 - vagao.posx) % (20 * vagao.tempoDeViagem);
		       vagao.resto = decimal;
		       
		       frmCriaoDeVago.setVisible(false);
		       frmCriaoDeVago.dispose();
		       Animacao telaPrincipal = new Animacao();		       
		       telaPrincipal.frameAnimacao.setVisible(true);
		       vagao.start();
		       String pronto = "Vagão foi criado com " + vagao.quantidadeDecadeiras + " cadeiras.\n";
		       Animacao.textArea.append(pronto);
		       
		    }
		});
		
	}
}
