package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.Reel.Stats;

public class Statics extends JFrame{

	// labels of statics window
	private JLabel lblimge,lbl1,lbl2,lbl3;
	
	private JButton saveDetails;
	

	Container contentPane;
	
public Statics(Reel no){
	
setLayout(new GridLayout(8,8,8,8));
	
	//resizing window doesn't affect the gui when using this layout
	contentPane = getContentPane();
	contentPane.setLayout(new GridBagLayout());
    contentPane.setBackground(Color.white);//set contentpane color
       
        GridBagConstraints layout = new  GridBagConstraints();
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.insets = new Insets(5,5,5,5);
	
        //setting window appearence
		this.setTitle("Game STATISTICS");
		this.setSize(500,400);
		this.setLocationRelativeTo(null);
	    this.setResizable(true);
	    
	 
	    //getting no of wins and loss
	    int winNo= no.getNoWins();
	    int lossNo=no.getNoLoss();
	
      double counting=no.getNoTime();// no of times game is played
      int avg=no.getAvgcredit(); // avg is + or - ,win or loss
      double totAvg=avg/counting; //probability of winnig one game
      
      saveDetails=new JButton("SAVE ");
	  layout.gridx = 0;
	  layout.gridy = 4;

	  contentPane.add(saveDetails,layout); // button details
	  
	  saveDetails.addActionListener(new saveDet());
	  
	  lblimge = new JLabel(new ImageIcon(getClass().getResource("statImages/Logo.jpg")));
	  layout.gridx = 0;
	  layout.gridy = 0;
	  contentPane.add(lblimge,layout);
	  
	  lbl1=new JLabel();
	  layout.gridx = 0;
	  layout.gridy = 1;

	  contentPane.add(lbl1,layout);
	  
	  lbl2=new JLabel();
	  layout.gridx = 0;
	  layout.gridy = 2;

	  contentPane.add(lbl2,layout);
	  
	  int tot=0;
      
	  lbl3=new JLabel("THE Winnig Probability :"+tot);
	  layout.gridx = 0;
	  layout.gridy = 3;

	  contentPane.add(lbl3,layout);
      
	 // setting no of wins and loss
	  lbl1.setText("Total No Of Wins :"+winNo);
	  lbl2.setText("Total No Of Losses:"+lossNo);
	  
	  
		  //setting winning probability
		  lbl3.setText("THE Winnig Probability  :"+totAvg);
	  
	  
	  //this.add(panel2);
	  
	  this.setVisible(true);
  
  }

public void statisReset(){ // resetting values of statstics window
	 lbl1.setText("Total No Of Wins:0");
	 lbl2.setText("Total No Of Losses:0");
	 lbl3.setText("THE Winnig Probability :0");

}

class saveDet implements ActionListener{
	
	
    	 
    	 public void Writter(){
    		
        	 try {
        		 //writting the file with name of date
        		Date date = new Date() ;
        	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
        		File fw = new File(dateFormat.format(date) + ".txt") ;
				PrintWriter pw=new PrintWriter(fw);
				pw.println(lbl1.getText());
				pw.println(lbl2.getText());
				pw.close();
			} catch (IOException e) {
			}
     		
		}

		@Override
public void actionPerformed(ActionEvent e) {
                 
			Writter();
		}
     	 
}

     
}
