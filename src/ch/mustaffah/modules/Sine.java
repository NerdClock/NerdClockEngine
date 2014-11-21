package ch.mustaffah.modules;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Sine extends Thread implements Runnable {

	private boolean ON = false;
	private int SAMPLE_RATE = 16;
	private int freq = 500;

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	public Sine(int sampleRate, int frequency) {
		this.SAMPLE_RATE = sampleRate;
		this.freq = frequency;
	}

	private void togglePlay() {
		final AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, true);
		try (SourceDataLine line = AudioSystem.getSourceDataLine(af)) {
			ON = !ON;
			while (ON) {
				line.open(af, SAMPLE_RATE);
				line.start();
				byte[] arr = getData();
				line.write(arr, 0, arr.length);
				line.drain();
			}
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	private byte[] getData() {
		final int LENGTH = 1024;
		final byte[] arr = new byte[LENGTH];
		for (int i = 0; i < arr.length; i++) {
			double angle = (2.0 * Math.PI * i) / (SAMPLE_RATE / getFreq());
			arr[i] = (byte) (Math.sin(angle) * 127);
		}
		return arr;
	}

	@Override
	public void run() {
		this.togglePlay();
	}
}