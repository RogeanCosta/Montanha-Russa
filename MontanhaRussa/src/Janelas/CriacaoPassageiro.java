package Janelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import Programa.Aplicacao;
import Programa.Passageiro;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class CriacaoPassageiro {

	public JFrame frameCriaPassageiro;
	private JTextField tempoEmbarque;
	private JTextField tempoDesembarque;
	private JButton botao = new JButton("Criar Passageiro");

	/**
	 * Create the application.
	 */
	public CriacaoPassageiro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameCriaPassageiro = new JFrame();
		frameCriaPassageiro.setResizable(false);
		frameCriaPassageiro.setTitle("Cria\u00E7\u00E3o de passageiro");
		frameCriaPassageiro.setBounds(100, 100, 340, 146);
		frameCriaPassageiro.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tempo de embarque: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 152, 21);
		frameCriaPassageiro.getContentPane().add(lblNewLabel);
		
		JLabel lblTempoDeDesembarque = new JLabel("Tempo de desembarque: ");
		lblTempoDeDesembarque.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTempoDeDesembarque.setBounds(10, 43, 176, 21);
		frameCriaPassageiro.getContentPane().add(lblTempoDeDesembarque);
		
		/**
		Parte de texto de Embarque  
		**/
		tempoEmbarque = new JTextField();
		tempoEmbarque.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {      // passa para a próxima opção com enter
					tempoDesembarque.requestFocus();
				}
			}
		});
		tempoEmbarque.setBounds(205, 13, 86, 20);
		frameCriaPassageiro.getContentPane().add(tempoEmbarque);
		tempoEmbarque.setColumns(10);
		
		/**
		Parte de texto de Desembarque  
		**/
		tempoDesembarque = new JTextField();
		tempoDesembarque.setBounds(205, 45, 86, 20);
		frameCriaPassageiro.getContentPane().add(tempoDesembarque);
		tempoDesembarque.setColumns(10);
		tempoDesembarque.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {      // passa para a próxima opção com enter
					botao.requestFocus();
				}
			}
		});
		
		
		/**
		Botão e configurações
		 * */
		botao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {      // faz a ação do click com enter
					botao.doClick();
				}
			}
		});
		botao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameCriaPassageiro.setVisible(false);
				Passageiro passageiro = new Passageiro();
				passageiro.tempoEmbarque = Integer.parseInt(tempoEmbarque.getText());
				passageiro.tempoDesembarque = Integer.parseInt(tempoDesembarque.getText());
				Aplicacao.bankQueue.add(passageiro);
				frameCriaPassageiro.dispose();
				passageiro.start();	
			}
		});
		botao.setForeground(Color.WHITE);
		botao.setBackground(new Color(0, 204, 153));
		botao.setFont(new Font("Tahoma", Font.BOLD, 14));
		botao.setBounds(10, 75, 152, 25);
		frameCriaPassageiro.getContentPane().add(botao);
	}
}
