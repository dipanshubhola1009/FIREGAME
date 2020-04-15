package firegame;


import javax.swing.JFrame;

public class Main  {
	
	public static void main(String [] args) {
		
		JFrame g = new JFrame();
		block NewBlock = new block();
		g.setBounds(350,100,700,600);
		g.setTitle("Firebrick");
		g.setResizable(false);
		g.setVisible(true);
		g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g.add(NewBlock);
		
	}

}
