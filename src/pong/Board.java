package pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener 
{
	private boolean victory; 
	private Paddle paddle1, paddle2;
	private Timer tickTimer;
	private int B_WIDTH;
	private int B_HEIGHT;
	private int player1NumberOfWins;
	private int player2NumberOfWins;
	
	public Board() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setVisible(true);
		setBackground(Color.DARK_GRAY);
		setDoubleBuffered(true);
		setPreferredSize(new Dimension(600,600));
		victory = false;
		B_WIDTH = getWidth();
		B_HEIGHT = getHeight(); 
		player1NumberOfWins = 0;
		player2NumberOfWins = 0;
		
		paddle1 = new Paddle(1);
		paddle2 = new Paddle(2);
		
		tickTimer = new Timer(5,this);
		tickTimer.start();

	}
	
	public void paint(Graphics g){
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(Color.WHITE);
		g2d.drawRect(0, 0, 599, 99);
		g2d.setFont(new Font("Courier New", Font.BOLD, 100));
		g2d.drawString("" + player1NumberOfWins, 50, 80);
		g2d.drawString("" + player2NumberOfWins, 500, 80);
		
		
		g2d.setColor(Color.GREEN);
				
		g2d.fillRect(paddle1.getX(), paddle1.getY(), 10, 70);
		g2d.fillRect(paddle2.getX(), paddle2.getY(), 10, 70);
		
		g2d.fillOval(250, 250, 25, 25);
		
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	public void addNotify() {
		super.addNotify();
		B_WIDTH = getWidth();
		B_HEIGHT = getHeight();   
	}
	
	public void actionPerformed(ActionEvent e) {
		
		paddle1.move();
		paddle2.move();
		repaint();
	}


	
	private class TAdapter extends KeyAdapter { 

		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			
			if((key == KeyEvent.VK_W) || (key == KeyEvent.VK_S)) { 
				paddle1.keyReleased(e);
			}
			if((key == KeyEvent.VK_UP) || (key == KeyEvent.VK_DOWN)) {
				paddle2.keyReleased(e);
			}			
		}

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			if((key == KeyEvent.VK_W) || (key == KeyEvent.VK_S)) { 
				paddle1.keyPressed(e);
			}
			if((key == KeyEvent.VK_UP) || (key == KeyEvent.VK_DOWN)) {
				paddle2.keyPressed(e);
			}		
		}
	}
}



