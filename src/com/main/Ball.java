package com.main;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	
	public static final int SIZE = 16;
	
	private int x;
	private int y;

	private int xVel;
	private int yVel;
	private int speed;
	
	public Ball() {
		
		reset();
	}

	private void reset() {

		//initial positions
		x = Game.WIDTH / 2 - SIZE / 2;
		y = Game.HEIGHT / 2 - SIZE / 2;


		//Initial velocities
		xVel = Game.sign(Math.random() * 2.0 -1);
		yVel = Game.sign(Math.random() * 2.0 -1);
	}
	
	public void changeXDir() {
		
		xVel *= -1;
	}
	
public void changeyDir() {
		
		yVel *= -1;
	}

public void draw(Graphics g) {

	g.setColor(Color.white);
	g.fillRect(x, y, SIZE, SIZE);
}
}
