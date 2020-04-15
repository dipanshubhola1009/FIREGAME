package firegame;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class block extends JPanel implements KeyListener,ActionListener {
    public int score=0;
	public int position=300;
	public int positionfire=560;
	public int attackfireposition=0;
	public static int attackfirepositionX=10;
	public int attackspeed=7;
	public int positionfireX;
	
	
	public boolean play=false;
	public boolean Fire=false;
	public boolean over=false;
	public boolean won=false;
	
	
	
	public Timer time;
	public int delay=10;
	
	public int row =10;
	public int col=10;
	public int Noblocks=row;
	
	public fallingBlocks fall;
	
	
	public block() {
		fall = new fallingBlocks(row,col);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(delay,this);
		time.start();
		}
	
	
	
	public void paint(Graphics g)
	{
	//if play is true
	if(play) {
		
		//score board
		g.setColor(Color.black);
		g.setFont(new Font("serif",Font.BOLD,24));
		g.drawString(""+score,630,50);
		
		//background
		g.setColor(Color.white);
		g.fillRect(0,0,700,600);
		
		//to draw bricks
		fall.draw((Graphics2D)g);
		
		//tanker
		g.setColor(Color.green);
		g.fillRect(position,520,50,50);
		g.setColor(Color.black);
		g.fillRect(position+15,500,20,70);
		
		//if we press space
		 if (Fire )
		 {      positionfireX=position;
			    positionfire-=10;
				g.setColor(Color.black);
				g.fillRect(positionfireX+15,positionfire,20,20);
				if(positionfire<=0)
				{
					positionfire=560;
					Fire=false;
				}
		 }
		
		//for attack by pc
	    attackfireposition+=attackspeed;
		g.setColor(Color.black);
		g.fillRect(attackfirepositionX+20,attackfireposition,20,20);
		if(attackfireposition>=600)
		{
			attackfireposition=0;
			attackfirepositionX+=(int)(Math.random()*100);
			if(attackfirepositionX>=660)
			{
				attackfirepositionX=0;
			}	
		
	    }
	}
	else {
		
		//background
		g.setColor(Color.black);
		g.fillRect(0,0,700,600);
		
		//background bricks
		fall.draw((Graphics2D)g);
		
		//welcome text
		g.setColor(Color.red);
		g.setFont(new Font("serif",Font.BOLD,60));
		g.drawString(" WELCOME ",160,400);
		g.setColor(Color.yellow);
		g.setFont(new Font("serif",Font.BOLD,60));
		g.drawString("BATTLE FIELD",125,450);
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD,24));
		g.drawString("Press ENTER to start",235,470);
		
		
	   //for main page tank
		g.setColor(Color.green);
		g.fillRect(position,520,50,50);
	    g.setColor(Color.white);
		g.fillRect(position+15,500,20,70);
		
		
		//if tank got damage
		if(over) {
			g.setColor(Color.black);
			g.fillRect(0,0,700,600);
			
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,60));
			g.drawString("GAME OVER",160,300);
			
			g.setColor(Color.white);
			g.setFont(new Font("serif",Font.BOLD,24));
			g.drawString("Press ENTER to Restart",230,550);
		}
		
	}
	g.dispose();


	}


	@Override
	public void actionPerformed(ActionEvent e) {
		time.start();
	    repaint();
	    
	    //to remove the brick 
		 for(int i=0;i<fall.map.length;i++) {
				for(int j=0;j<fall.map[0].length;j++) {
					if(fall.map[i][j]>0)
					{
						int brickX= j * fall.brickW + 35;
						int brickY=i* fall.brickH + 50;
						int brickW= fall.brickW;
						int brickH= fall.brickH;
						
						Rectangle rect = new Rectangle(brickX,brickY,brickW,brickH);
						Rectangle ballrect= new Rectangle(position+15,positionfire,20,20);
						Rectangle brickrect= rect;
						
						if(ballrect.intersects(brickrect)) {
							  fall.setvalue(0,i,j);
							  Fire=false;
							  positionfire=560;
							  Noblocks--;
							  score+=5;
					}
				}
				}	
		 }
		 
		 
		 //when tank get hit by attack
		 if(new Rectangle(attackfirepositionX+20,attackfireposition,20,20).intersects(new Rectangle(position+15,500,20,70)))
		 {   play=false;
			 over=true;
			
		 }
		 
		 //no brick left
		 if(Noblocks<=0) {
			 row+=5;
			 col+=5;
			 attackspeed+=5;
			 fall = new fallingBlocks(row,col);
			 Noblocks=row*col;
		 }
		
	}



	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{   position+=20;
			if(position>=580) position=580;
			
		}
			
			
	    if(e.getKeyCode()==KeyEvent.VK_LEFT)
	    {
	    	position-=20;
	    	if(position<=20) position=20;
	    }
	    if(e.getKeyCode()==KeyEvent.VK_SPACE) {
	    	positionfireX=position;
	    	Fire=true;
	    }
	    if(e.getKeyCode()==KeyEvent.VK_ENTER) {
	    if(!play) {	
	    	score=0;
	    	attackfireposition=0;
	    	fall =new fallingBlocks(row,col);
	    	play=true;
	    	won=false;
	    	over=false;
	    	
	    	time.start();
	    }                  
	    }
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}