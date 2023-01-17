package com.main;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 1000;
	public static final int HEIGHT = WIDTH * 9/16;
	
	public boolean running = false;
	private Thread gameThread;
	private Ball ball;
	private Paddle leftPaddle;
	private Paddle rightPaddle;
	
	public Game() {
		
		canvasSetup();
		initialize();
		
		new Window("SimplePong", this);
		
		this.addKeyListener(new KeyInput(leftPaddle, rightPaddle));
		this.setFocusable(true);
	}
	
	
	private void initialize() {

		//initialize ball
		this.ball = new Ball();
		
		//initialize paddles
		this.leftPaddle = new Paddle(Color.green, true);
		this.rightPaddle = new Paddle(Color.red, false);

	}


	private void canvasSetup() {
		
		Dimension dimension = new Dimension(WIDTH, HEIGHT);
		this.setPreferredSize(dimension);
		this.setMaximumSize(dimension);
	}


	@Override
	public void run() {

		this.requestFocus();
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running) {
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while (delta >= 1) {
				this.update();
				delta--;
				this.draw();
				frames++;
			}
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void update() {

		//update ball
		this.ball.update(this.leftPaddle, this.rightPaddle);
		
		//update paddles
		this.leftPaddle.update(this.ball);
		this.rightPaddle.update(this.ball);
	}


	private void draw() {

		//Initialize drawing tools
		BufferStrategy buffer = this.getBufferStrategy();
		
		if (buffer == null) {
			
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = buffer.getDrawGraphics();
		
		//draw background
		drawBackground(g);
		
		//draw ball
		this.ball.draw(g);
		
		//draw paddles and score
		this.leftPaddle.draw(g);
		this.rightPaddle.draw(g);
		
		//dispose, actualy draw
		g.dispose();
		buffer.show();
	}


	private void drawBackground(Graphics g ) {

		//black background
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//dotted line
		g.setColor(Color.white);
		Graphics2D g2d = (Graphics2D) g;
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {10}, 0);
		g2d.setStroke(dashed);
		g2d.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
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


	public static int sign(double d) {

		if(d <= 0)
			return -1;
		else
			return 1;
	}


	public static int ensureRange(int value, int min, int max) {
		return Math.min(Math.max(value, min), max);
	}

}