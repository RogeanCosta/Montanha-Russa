import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Janela extends JFrame{
	JFrame janela = new JFrame();
	JPanel div = new JPanel();
	JLabel qntCadeiras = new JLabel("Quantidade de cadeiras do vag�o: ");
	JLabel tempoViagem = new JLabel("Tempo de Viagem: ");
	JTextField cadeiras = new JTextField(20);
	JTextField viagem = new JTextField(20);
	JButton confirma = new JButton("Criar vag�o");
	BotaoVagao valores = new BotaoVagao(cadeiras, viagem);	
	
	public void Janela() {
		
		// Cria��o da Janela
//		JFrame janela = new JFrame();
		janela.setSize(500, 500);
		janela.setLocation(500, 200);
		janela.setTitle("Roller Coaster Aplication");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Cria��o de Jpanel
//		JPanel div = new JPanel();
		
		// Cria��o de uma Label
//		JLabel qntCadeiras = new JLabel("Quantidade de cadeiras do vag�o: ");
//		JLabel tempoViagem = new JLabel("Tempo de Viagem: ");
		
		
		// Cria��o de campos
//		JTextField cadeiras = new JTextField(20);
//		JTextField viagem = new JTextField(20);
		
		// Cria��o de bot�o de envio de informa��es
//		JButton confirma = new JButton("Criar vag�o");
//		BotaoVagao valores = new BotaoVagao(cadeiras, viagem);
		
		confirma.addActionListener(valores);
		
		// Constru��o da panel
		div.add(qntCadeiras);
		div.add(cadeiras);
		div.add(tempoViagem);
		div.add(viagem);
		div.add(confirma);
		
		// Constru��o completa da Janela
		janela.add(div);
		janela.setVisible(true);
		
		// Tentativa de Constru��o do Vag�o no tempo certo
		// return new Vagao(Integer.parseInt(cadeiras.getText()), Integer.parseInt(viagem.getText()));

	}
}
