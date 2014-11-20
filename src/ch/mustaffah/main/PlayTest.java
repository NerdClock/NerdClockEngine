package ch.mustaffah.main;
import ch.mustaffah.frontend.OscillatorPanel;
import ch.mustaffah.modules.Sine;


public class PlayTest {

	public static void main(String[] args) {
		OscillatorPanel panel=new OscillatorPanel(new Sine(0, 0));
		panel.setVisible(true);
	}

}
