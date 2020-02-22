package ihm;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class GUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	Image image;
	
	//Space between the cells
	int spacing = 2;
	
	public int mx = -100;
	public int my = -100;
	
	Random rand = new Random();
	
	//ftfyigft
	int [][] obstacle = new int [5][5];
	int [][] perso = new int[5][5];
	int [][] objective = new int[5][5];
	
	
	public GUI() {
		this.setTitle("Map");
		this.setSize(1286, 829);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);

		/**
		int nbObstacle = rand.nextInt(6);
		while(nbObstacle < 2) {
			nbObstacle = rand.nextInt(6);
		}
		for(int o = 1; o <= nbObstacle; o++) {
			int x = rand.nextInt(5);
			int y = rand.nextInt(5);
			obstacle[x][y] = 1;
			System.out.println("The number of obstacles is " + nbObstacle);
			
		}
		**/
		obstacle[1][0]=1;
		obstacle[1][1]=1;
		obstacle[3][1]=1;
		obstacle[3][2]=1;
		obstacle[0][3]=1;
		obstacle[2][3]=1;
		obstacle[3][3]=1;

		perso[0][0]=2;
		
		objective[4][4]=3;
		

		Board board = new Board();
		this.setContentPane(board);
		
		Move move = new Move();
		this.addMouseMotionListener(move);
		
		Click click = new Click();
		this.addMouseListener(click);
	}
	public class Board extends JPanel{
		
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {
			//ImageIcon ic = new ImageIcon("/grille/src/images/Naruto.jpg");
			//image = ic.getImage();
			
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, 1286, 829);
			//g.drawImage(image, 100, 100, 200, 200, 120, 0, 160, 60,Color.white);
			//g.drawImage()
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 5; j++) {
					g.setColor(Color.gray);
					if(obstacle[i][j] == 1) {
						g.setColor(Color.red);
					}
					if(perso[i][j]==2) {
						g.setColor(Color.yellow);
						
					}
					if(objective[i][j]==3) {
						g.setColor(Color.green);
					}
					if(mx >= spacing+i*80 && mx < i*80+80-2*spacing && my >= spacing+j*80+80+26 && my < spacing+j*80+26+80+80-2*spacing) {
						g.setColor(Color.white);
					}
					g.fillRect(spacing+i*80, spacing+j*80+80, 80-2*spacing, 80-2*spacing);
				}
			}
			
		}
	}
	public class Move implements MouseMotionListener{

		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println("The mouse was moved");
			mx = e.getX();
			my = e.getY();
			System.out.println("X:" + mx + ", Y :" + my);
			
			
		}
		
	}
	public class Click implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println("The mouse was clicked");
			
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
