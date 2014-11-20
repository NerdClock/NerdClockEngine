package ch.mustaffah.modules;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Sine extends Thread implements Runnable{

	private int SAMPLE_RATE = 16 * 1024;
	private int freq = 500;
	private Sine sine1;

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
			try {
				sine1.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public Sine(int sampleRate, int frequency) {
		this.SAMPLE_RATE = sampleRate;
		this.freq = frequency;
	}

	private void play() {
		final AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, true);
		try (SourceDataLine line = AudioSystem.getSourceDataLine(af)) {
			line.open(af, SAMPLE_RATE);
			line.start();
			byte[] arr = getData();
			line.write(arr, 0, arr.length);
			line.drain();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	private byte[] getData() {
		final int LENGTH = SAMPLE_RATE * 100;
		final byte[] arr = new byte[LENGTH];
		for (int i = 0; i < arr.length; i++) {
			double angle = (2.0 * Math.PI * i) / (SAMPLE_RATE / getFreq());
			arr[i] = (byte) (Math.sin(angle) * 127);
		}
		return arr;
	}

	@Override
	public void run() {
		this.play();
	}
	
	public void oscillatorStart(){
		this.sine1 = new Sine(16 * 1024, 500);
		this.sine1.start();
//		Thread s2 = new Thread(new Sine(16 * 1024, (int)(Math.random()*1000)));
//		s2.start();
//		Thread s3 = new Thread(new Sine(16 * 1024, (int)(Math.random()*1000)));
//		s3.start();
//		Thread s4 = new Thread(new Sine(16 * 1024, (int)(Math.random()*1000)));
//		s4.start();
//		Thread s5 = new Thread(new Sine(16 * 1024, (int)(Math.random()*1000)));
//		s5.start();
//		Thread s6 = new Thread(new Sine(16 * 1024, (int)(Math.random()*1000)));
//		s6.start();
	}
	public void oscillatorUpdate(){
		this.sine1.setFreq(700);
	}
}