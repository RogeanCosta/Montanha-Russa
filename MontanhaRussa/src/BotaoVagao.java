import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;


public class BotaoVagao implements ActionListener {

	private JTextField chairs;
	private JTextField time;
	
	private int numCad;
	private int numTime;
	public static int click;
	
	public BotaoVagao(JTextField chairs, JTextField time) {
		this.chairs = chairs;
		this.time = time;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {	
		numCad = Integer.parseInt(chairs.getText());
		numTime = Integer.parseInt(time.getText());
		click = click + 1;
//		Vagao v = new Vagao(numCad, numTime);
	}
	

	 public int getNumCad() {
		return numCad;
	 }

	 public int getNumTime() {
		return numTime;
	 }
	 
	 public int getClick() {
		 return click;
	 }
}
