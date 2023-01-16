package com.main;

import javax.swing.JFrame;

public class Window {
	
	public Window(String title, Game game) {
		
		JFrame frame = new JFrame(title);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		frame.setResizable(false);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	
	public static void main(String[] args) {
		
		new Game();
	}

}