package ch.mustaffah.frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ch.mustaffah.modules.Sine;

public class OscillatorPanel extends JFrame {
	private Sine sine;
	private JPanel mainPanel;
	private JButton startButton;
	private JButton updateButton;
	

	public OscillatorPanel(Sine sine) {
		this.sine = sine;
		createGui();
	}

	private JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JPanel();
			mainPanel.add(getStartButton());
			mainPanel.add(getUpdateButton());
		}
		return mainPanel;
	}

	private JButton getStartButton() {
		if (startButton == null) {
			startButton = new JButton("random");
			startButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					sine.oscillatorStart();
				}
			});
			;
		}
		return startButton;
	}
	
	private JButton getUpdateButton() {
		if (updateButton == null) {
			updateButton = new JButton("update");
			updateButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
						sine.setFreq(898);
				}
			});
			;
		}
		return updateButton;
	}

	private void createGui() {
		this.setSize(500, 500);
		this.setVisible(true);
		this.getContentPane().add(getMainPanel());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
