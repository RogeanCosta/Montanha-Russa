package Janelas;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;



public class Animacao {

	public JFrame frameAnimacao;
	public JFrame frameOficial;
	public static JTextArea textArea;
	private JScrollPane scrollPane;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;

	
	
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
		frameAnimacao.setTitle("Montanha Russa");
		frameAnimacao.setResizable(false);
		frameAnimacao.setSize(1077, 592);
		frameAnimacao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameAnimacao.setLocationRelativeTo(null);
		frameAnimacao.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Criar passageiro");
		btnNewButton.setBounds(834, 343, 146, 36);
		
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
		frameAnimacao.getContentPane().add(btnNewButton);
		
		textArea = new JTextArea();
		textArea.setBounds(746, 389, 307, 152);
		textArea.setEditable(false);
		frameAnimacao.getContentPane().add(textArea);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(746, 389, 307, 152);
		frameAnimacao.getContentPane().add(scrollPane);
		
		Canvas canvas = new Canvas();
		canvas.setBounds(0, 0, 728, 527);
		canvas.setVisible(true);
		
		panel1 = new JPanel();
		panel1.setBounds(0, 0, 745, 553);
		panel1.setLayout(null);
		panel1.add(canvas);
		frameAnimacao.getContentPane().add(panel1);
		
		
	}
}



