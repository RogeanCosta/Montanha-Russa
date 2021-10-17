package Janelas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Animacao {

	// Componentes da tela 
	public JFrame frameAnimacao;
	public static JTextArea textArea;
	public static JTextArea cronometro;
	private JScrollPane scrollPane;
	public static JPanel panel1;

	// Cria o frame da criação de passageiro.
	public Animacao() {
		inicializar();
	}

	// Inicializa o conteúdo do frame.
	private void inicializar() {
		
		frameAnimacao = new JFrame();	
		frameAnimacao.getContentPane().setBackground(new Color(222, 184, 135));
		frameAnimacao.setTitle("Montanha Russa");
		frameAnimacao.setResizable(false);
		frameAnimacao.setSize(1110, 649);
		frameAnimacao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameAnimacao.setLocationRelativeTo(null);
		frameAnimacao.getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Arial Black", Font.PLAIN, 13));
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.BLACK);
		textArea.setBounds(777, 0, 317, 499);
		textArea.setEditable(false);
		frameAnimacao.getContentPane().add(textArea);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(777, 0, 317, 499);
		frameAnimacao.getContentPane().add(scrollPane);
		
		Canvas canvas = new Canvas();
		canvas.setBounds(0, 0, 777, 610);
		canvas.setVisible(true);
		
		panel1 = new JPanel();
		panel1.setBounds(0, 0, 777, 610);
		panel1.setLayout(null);
		panel1.add(canvas);
		frameAnimacao.getContentPane().add(panel1);
		
		JButton btnNewButton = new JButton("Criar passageiro");
		btnNewButton.setBounds(863, 521, 146, 36);
		frameAnimacao.getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				CriacaoPassageiro window = new CriacaoPassageiro();
				window.frameCriaPassageiro.setVisible(true);
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 204, 153));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		cronometro = new JTextArea();
		cronometro.setFont(new Font("Arial Black", Font.PLAIN, 15));
		cronometro.setBounds(965, 568, 32, 22);
		cronometro.setEditable(false);
		frameAnimacao.getContentPane().add(cronometro);
		
		JLabel tempo = new JLabel("Cronômetro:");
		tempo.setFont(new Font("Tahoma", Font.BOLD, 13));
		tempo.setBounds(873, 568, 87, 22);
		frameAnimacao.getContentPane().add(tempo);
	}
}



