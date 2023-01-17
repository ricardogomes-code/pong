package com.main;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	
	public static final int SIZE = 16;
	
	private int x;
	private int y;

	private int xVel;
	private int yVel;
	private int speed = 5;
	
	public Ball() {
		
		this.reset();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	private void reset() {

		//initial positions
		this.x = Game.WIDTH / 2 - this.SIZE / 2;
		this.y = Game.HEIGHT / 2 - this.SIZE / 2;


		//Initial velocities
		this.xVel = Game.sign(Math.random() * 2.0 -1);
		this.yVel = Game.sign(Math.random() * 2.0 -1);
	}
	
	public void changeXDir() {
		
		this.xVel *= -1;
	}
	
public void changeyDir() {
		
		this.yVel *= -1;
	}

public void draw(Graphics g) {

	g.setColor(Color.white);
	g.fillRect(x, y, SIZE, SIZE);
}

public void update(Paddle leftPaddle, Paddle rightPaddle) {

	//update movement
	this.x += this.xVel * this.speed;
	this.y += this.yVel * this.speed;

	//collisions
	if(this.y + this.SIZE >= Game.HEIGHT || y <= 0)
		this.changeyDir();
	
	//with walls
	if(this.x + this.SIZE >= Game.WIDTH) {
		leftPaddle.addPoint();
		this.reset();
	}
	
	if(x <= 0) {
		rightPaddle.addPoint();
		reset();
	}
}
}
