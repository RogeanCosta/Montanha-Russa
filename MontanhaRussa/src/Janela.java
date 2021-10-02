import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Janela extends JFrame{
	JFrame janela = new JFrame();
	JPanel div = new JPanel();
	JLabel qntCadeiras = new JLabel("Quantidade de cadeiras do vagão: ");
	JLabel tempoViagem = new JLabel("Tempo de Viagem: ");
	JTextField cadeiras = new JTextField(20);
	JTextField viagem = new JTextField(20);
	JButton confirma = new JButton("Criar vagão");
	BotaoVagao valores = new BotaoVagao(cadeiras, viagem);	
	
	public void Janela() {
		
		// Criação da Janela
//		JFrame janela = new JFrame();
		janela.setSize(500, 500);
		janela.setLocation(500, 200);
		janela.setTitle("Roller Coaster Aplication");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Criação de Jpanel
//		JPanel div = new JPanel();
		
		// Criação de uma Label
//		JLabel qntCadeiras = new JLabel("Quantidade de cadeiras do vagão: ");
//		JLabel tempoViagem = new JLabel("Tempo de Viagem: ");
		
		
		// Criação de campos
//		JTextField cadeiras = new JTextField(20);
//		JTextField viagem = new JTextField(20);
		
		// Criação de botão de envio de informações
//		JButton confirma = new JButton("Criar vagão");
//		BotaoVagao valores = new BotaoVagao(cadeiras, viagem);
		
		confirma.addActionListener(valores);
		
		// Construção da panel
		div.add(qntCadeiras);
		div.add(cadeiras);
		div.add(tempoViagem);
		div.add(viagem);
		div.add(confirma);
		
		// Construção completa da Janela
		janela.add(div);
		janela.setVisible(true);
		
		// Tentativa de Construção do Vagão no tempo certo
		// return new Vagao(Integer.parseInt(cadeiras.getText()), Integer.parseInt(viagem.getText()));

	}
}
