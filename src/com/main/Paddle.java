package com.main;

import java.awt.Color;
import java.awt.Font;
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

		if (left)
			this.x = 0;
		else
			this.x = Game.WIDTH - this.width;

		this.y = Game.HEIGHT / 2 - this.height / 2;
	}

	public void addPoint() {

		this.score++;
	}

	public void draw(Graphics g) {

		// Draw paddle
		g.setColor(this.color);
		g.fillRect(this.x, this.y, this.width, this.height);

		// Draw score
		int sx;
		String scoreText = Integer.toString(score);
		Font font = new Font("Roboto", Font.PLAIN, 50);

		int strWidth = g.getFontMetrics(font).stringWidth(scoreText);

		int padding = 25;

		if (left)
			sx = Game.WIDTH / 2 - padding - strWidth;
		else
			sx = Game.WIDTH / 2 + padding;

		g.setFont(font);
		g.drawString(scoreText, sx, 50);
	}

	public void update(Ball ball) {

		// update position
		y = Game.ensureRange(y + vel, 0, Game.HEIGHT - height);
		//y = 200;

		// collisions with ball
		int ballX = ball.getX();
		int ballY = ball.getY();

		if (left) {

			if (ballX <= width + x 
			&& ballY + Ball.SIZE >= y 
			&& ballY <= y + height)
				ball.changeXDir();

		} else {

			if (ballX + Ball.SIZE >= x 
			&& ballY + Ball.SIZE >= y 
			&& ballY <= y + height)
				ball.changeXDir();

		}
	}

	public void switchDirections(int direction) {
		
		this.vel = this.speed * direction;
	}
	
	public void stop() {
		this.vel = 0;
	}
}
