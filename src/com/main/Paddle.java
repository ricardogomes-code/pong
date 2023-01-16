package com.main;

import java.awt.Color;
import java.awt.Graphics;

public class Paddle {
	
	private int x;
	private int y;
	private int vel = 0;
	private int speed = 10;
	private int width = 22;
	private int height = 85;
	private int score = 0;
	private Color color;
	private boolean left;
	
	public Paddle(Color color, boolean left) {
		
		this.color = color;
		this.left = left;
		
		if(left)
			this.x = 0;
		else
			this.x = Game.WIDTH - this.width;
		
		this.y = Game.HEIGHT / 2 - this.height / 2;
	}
	
	public void addPoint() {
		
		this.score++;
	}

	public void draw(Graphics g) {

		//Draw paddle
		g.setColor(this.color);
		g.fillRect(this.x, this.y, this.width, this.height);
		
		//Draw score
	}
}
