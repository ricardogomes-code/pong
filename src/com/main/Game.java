package com.main;

import java.awt.Canvas;
import java.awt.Dimension;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 1000;
	public static final int WEIDTH = WIDTH * 9/16;
	
	public boolean running = false;
	private Thread gameThread;
	private Ball ball;
	private Paddle paddle1;
	private Paddle paddle2;
	
	public Game() {
		
		canvasSetup();
		new Window("SimplePong", this);
	}
	
	
	private void canvasSetup() {
		
		Dimension dimension = new Dimension(WIDTH, WEIDTH);
		this.setPreferredSize(dimension);
		this.setMaximumSize(dimension);
	}


	@Override
	public void run() {

		
	}

	public void start() {

		gameThread = new Thread(this);
		gameThread.start();
		running = true;
	}

	public void stop() {
		
		try {
			gameThread.join();
			running = false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}