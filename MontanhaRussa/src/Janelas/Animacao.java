package Janelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Animacao {

	public JFrame frameAnimacao;

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
		frameAnimacao.setResizable(false);
		frameAnimacao.setBounds(100, 100, 851, 495);
		frameAnimacao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("Criar passageiro");
		btnNewButton.setBounds(0, 0, 0, 0);
		btnNewButton.setBounds(680, 412, 145, 33);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 204, 153));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CriacaoPassageiro window = new CriacaoPassageiro();
				window.frameCriaPassageiro.setVisible(true);
			}
		});
		frameAnimacao.getContentPane().setLayout(null);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		frameAnimacao.getContentPane().add(btnNewButton);
	}
}
