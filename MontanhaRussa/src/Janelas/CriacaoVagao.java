package Janelas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import Programa.Vagao;

public class CriacaoVagao {

	public JFrame frmCriaoDeVago;
	private JTextField numeroDeCadeiras;
	private JTextField tempoDeViagem;
	JButton botao = new JButton("Criar vag\u00E3o");

	/**
	 * Create the application.
	 */
	public CriacaoVagao(Vagao vagao) {
		initialize(vagao);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Vagao vagao) {
		/**
		Cria o frame e configura
		**/
		frmCriaoDeVago = new JFrame();
		frmCriaoDeVago.setResizable(false);
		frmCriaoDeVago.setBackground(Color.LIGHT_GRAY);
		frmCriaoDeVago.setTitle("Cria\u00E7\u00E3o de Vag\u00E3o");
		frmCriaoDeVago.setBounds(100, 100, 316, 133);
		frmCriaoDeVago.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCriaoDeVago.getContentPane().setLayout(null);
		frmCriaoDeVago.setLocationRelativeTo(null);
		
		
		/**
		Cria os textos da janela
		**/
		JLabel lblNewLabel = new JLabel("N\u00FAmero de cadeiras:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 152, 14);
		frmCriaoDeVago.getContentPane().add(lblNewLabel);
		
		JLabel lblTempoDeViagem = new JLabel("Tempo de viagem: ");
		lblTempoDeViagem.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTempoDeViagem.setBounds(10, 36, 152, 14);
		frmCriaoDeVago.getContentPane().add(lblTempoDeViagem);
		
		/**
		Cria��o e configura��o das caixas de texto
		**/
		numeroDeCadeiras = new JTextField();
		numeroDeCadeiras.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {      // passa para a pr�xima op��o com enter
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
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {      // passa para a pr�xima op��o com enter
					botao.requestFocus();
				}
			}
		});
		tempoDeViagem.setBounds(172, 35, 86, 20);
		frmCriaoDeVago.getContentPane().add(tempoDeViagem);
		tempoDeViagem.setColumns(10);
		
		/**
		Cria��o e configura��o do bot�o
		**/ 
		botao.setBackground(new Color(0, 204, 153));
		botao.setForeground(Color.WHITE);
		botao.setFont(new Font("Tahoma", Font.BOLD, 14));
		botao.setBounds(10, 61, 129, 23);
		//javax.swing.BorderFactory.createEtchedBorder()
		frmCriaoDeVago.getContentPane().add(botao);
		botao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {				// aciona o botao com o enter
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
