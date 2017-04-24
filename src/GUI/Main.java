package GUI;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[]args){
		Reel object=new Reel();
		//graphicalInterface object=new graphicalInterface();
		object.setTitle("Slot Game");
		object.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		object.setSize(600,600);
		object.setVisible(true);
		
	}

}
