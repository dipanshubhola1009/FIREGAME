package firegame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class fallingBlocks {
	
	public int map[][];
	public int brickW;
	public int brickH;
	
	
	//constructor for bricks shown
	fallingBlocks(int row ,int col)
	{ 
		map =new int[row][col];
		for(int i=0;i<row;i++) 
		{
			
			
			int j= (int)(Math.random()*row)+0;
				map[i][j]=1;
			
		}
		brickW=610/col;
		brickH=260/row;
	}
	
	// to draw bricks on screen
	void draw(Graphics2D g)
	{
		for(int i=0;i<map.length;i++) 
		{
		  for(int j=0;j<map[0].length;j++)
		  {
			  if(map[i][j]>0) {
				  g.setColor(Color.RED);
				  g.fillRect(j*brickW+35,i*brickH+50,brickW,brickH);
				  g.setColor(Color.black);
					g.setStroke(new BasicStroke(3));
					g.drawRect(j*brickW +35,i*brickH + 50,brickW,brickH);

				  
			  }
		  }
			
		}
	}
	
	//to setvalue of brick to zero
	  void setvalue(int value,int row, int col) {
		  map[row][col]=value;
}
}
