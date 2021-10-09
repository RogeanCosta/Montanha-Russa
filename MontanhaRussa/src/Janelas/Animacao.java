package Janelas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;


public class Animacao {

	public JFrame frameAnimacao;
	public JFrame frameOficial;
	public static JTextArea textArea;
	public static JTextArea cronometro;
	private JScrollPane scrollPane;
	private JPanel panel1;


	
	
	/**
	 * Create the application.
	 */
	public Animacao() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frameAnimacao = new JFrame();	
		frameAnimacao.getContentPane().setBackground(new Color(255, 153, 204));
		frameAnimacao.setTitle("Montanha Russa");
		//frameAnimacao.setResizable(false);
		frameAnimacao.setSize(1110, 649);
		frameAnimacao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameAnimacao.setLocationRelativeTo(null);
		frameAnimacao.getContentPane().setLayout(null);
		
		textArea = new JTextArea();
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
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 204, 153));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CriacaoPassageiro window = new CriacaoPassageiro();
				window.frameCriaPassageiro.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		cronometro = new JTextArea();
		cronometro.setBounds(946, 577, 94, 22);
		cronometro.setEditable(false);
		frameAnimacao.getContentPane().add(cronometro);
		
		JLabel tempo = new JLabel("Cron\u00F4metro:");
		tempo.setFont(new Font("Tahoma", Font.BOLD, 13));
		tempo.setBounds(839, 577, 87, 22);
		frameAnimacao.getContentPane().add(tempo);
		
		
	}
}



