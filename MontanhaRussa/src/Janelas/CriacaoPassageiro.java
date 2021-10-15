package Janelas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Programa.Aplicacao;
import Programa.Passageiro;

public class CriacaoPassageiro {

	public JFrame frameCriaPassageiro;
	private JTextField tempoEmbarque;
	private JTextField tempoDesembarque;
	private JButton botao = new JButton("Criar Passageiro");

	
	// Cria o frame da criação de passageiro.
	public CriacaoPassageiro() {
		inicializa();
	}

	// Inicializa o conteúdo do frame.
	private void inicializa() {
		frameCriaPassageiro = new JFrame();
		frameCriaPassageiro.setResizable(false);
		frameCriaPassageiro.setTitle("Criação de passageiro");
		frameCriaPassageiro.setBounds(100, 100, 340, 146);
		frameCriaPassageiro.getContentPane().setLayout(null);
		frameCriaPassageiro.setLocationRelativeTo(null);
		
		JLabel labelEmbarque = new JLabel("Tempo de embarque: ");
		labelEmbarque.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelEmbarque.setBounds(10, 11, 176, 21);
		frameCriaPassageiro.getContentPane().add(labelEmbarque);
		
		JLabel labelDesembarque = new JLabel("Tempo de desembarque: ");
		labelDesembarque.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelDesembarque.setBounds(10, 43, 185, 21);
		frameCriaPassageiro.getContentPane().add(labelDesembarque);
		
		// Parte de texto de Embarque  
		tempoEmbarque = new JTextField();
		tempoEmbarque.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// passa para a próxima opção com enter
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					tempoDesembarque.requestFocus();
				}
			}
		});
		tempoEmbarque.setBounds(205, 14, 86, 20);
		frameCriaPassageiro.getContentPane().add(tempoEmbarque);
		tempoEmbarque.setColumns(10);
		
		// Parte de texto de Desembarque  
		tempoDesembarque = new JTextField();
		tempoDesembarque.setBounds(205, 45, 86, 20);
		frameCriaPassageiro.getContentPane().add(tempoDesembarque);
		tempoDesembarque.setColumns(10);
		tempoDesembarque.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// passa para a próxima opção com enter
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					botao.requestFocus();
				}
			}
		});
		
		// Botão e suas configurações
		botao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// faz a ação do click com enter
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					botao.doClick();
				}
			}
		});
		botao.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				frameCriaPassageiro.setVisible(false);
				Passageiro passageiro = new Passageiro((Aplicacao.identificador.size()+1));

				passageiro.vagao = Aplicacao.v;
				passageiro.tempoEmbarque = Integer.parseInt(tempoEmbarque.getText());
				passageiro.tempoDesembarque = Integer.parseInt(tempoDesembarque.getText());
				Aplicacao.identificador.add(passageiro);
				String pronto = "Passageiro " + (Aplicacao.identificador.indexOf(passageiro)+1) + " chegou na fila.\n";
				Animacao.textArea.append(pronto);
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
