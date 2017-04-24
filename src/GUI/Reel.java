package GUI;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.*;

public class Reel extends JFrame{
	
	
	private static double noOfTime;    
	private static int noOfWins;
    private static int noOfLoss;
    private static int Avgcredit;
	
    symbol obj=new symbol();
    
    
    
    public static int getNoWins() {
		return noOfWins;
	}
	public static void setCountWinn(int countWinn) {
		Reel.noOfWins = countWinn;
	}
	
	
    
	public static double getNoTime() {
		return noOfTime;
	}
	public static void setNoTime(double timeCount) {
		Reel.noOfTime = timeCount;
	}
	
	
	
	
	
	public static int getAvgcredit() {
		return Avgcredit;
	}
	public static void setAvgcredit(int avgcredit) {
		Avgcredit = avgcredit;
	}
	
	
	
	
	public static int getNoLoss() {
		return noOfLoss;
	}
	public static void setNoLoss(int countLoss) {
		Reel.noOfLoss = countLoss;
	}
	
	
	
	
	public ArrayList<ImageIcon> img=obj.images;//Image Array
	
			//Buttons
	private JButton btnSpin,btnOne,btnMax,btnReset,btnAdd, btnStat;
	
			//Labels
	private JLabel lblimg1,lblimg2,lblimg3,lblcredit,lblbet,lblstat,getcredits,getBets,getStats;
	
	
	private JTextArea creditArea,betArea,stats;

	Container contentPane;
	
	int Coins=10;//stating number of add coins in the begin
	int betCoin=1;//starting number of bet coins in the begining
	int betMax;
	int countNumWinn=0;//number of winn times in the game
 	int countNumloss=0;//number of loss time in the game
 	int countTimes=0;//number of time game play
    
 	Thread reelOneThread;//thread that use for first image 
    Thread reelTwoThread;//thread that use for second image 
    Thread reelThreeThread;//thread that use for third image 
	Random r=new Random();
	 
	Reel num;//object that pass statistics to the statistic window
	 
	 int value;//image values
	 int avgCredit=0;//sum of bet credits in a game
	 int avgCredit1=0;//no of win bet credits
	 int avgCredit2=0;//no of loss bet credits

	 int Credit=0;
	 
	public Reel(){
		
   setLayout(new GridLayout(5,5,5,5));
	
	//resizing window doesn't affect the gui when using this layout
	contentPane = getContentPane();
	contentPane.setLayout(new GridBagLayout());
    contentPane.setBackground(Color.CYAN);//set contentpane color
       
        GridBagConstraints layout = new  GridBagConstraints();
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.insets = new Insets(10,10,10,10);
	
	obj.SetImage();
	addLabel();
	addButton();	
	
	}
	

	
	//adding labels to gui
private void addLabel(){
	
	GridBagConstraints layout = new  GridBagConstraints();
	layout.fill = GridBagConstraints.HORIZONTAL;
	layout.insets = new Insets(3,3,3,3);
	layout.ipadx = 2;
	layout.ipady =2;
	
	lblimg1 = new JLabel();
	 lblimg1.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));   //reel one image
	 lblimg1.addMouseListener(new click());//it wait the first thread until it start
	 
	lblimg2 = new JLabel();//reel two image
	 lblimg2.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
	 lblimg2.addMouseListener(new click());//it wait the second thread until it start

	lblimg3 = new JLabel();//reel three image
	 lblimg3.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
	 lblimg3.addMouseListener(new click());//it wait the third thread until it start

	
	lblimg1.setIcon(img.get(1));//starting image of first label in begin
	lblimg2.setIcon(img.get(2));//starting image of second label in begin
	lblimg3.setIcon(img.get(4));//starting image of fourthlabel in begin
	
	layout.gridx = 0;//x axis in lblimg1
	layout.gridy = 0;//y axis in lblimg1

	layout.gridx = 0;
	layout.gridy = 5;
    contentPane.add(lblimg1,layout);
    
    layout.gridx = 1;
    layout.gridy = 1;
    contentPane.add(lblimg2,layout);
    
    layout.gridx = 2;
    layout.gridy = 1;
    contentPane.add(lblimg3,layout);
		
}

	
 // adding buttons to the gui
	private void addButton(){
	    contentPane.setLayout(new GridBagLayout());
	    GridBagConstraints layout = new  GridBagConstraints();
	    layout.fill = GridBagConstraints.HORIZONTAL;
	    layout.insets = new Insets(3,3,3,3);
	    
	    
	    btnSpin = new JButton("SPIN");//spin button processes
	    btnSpin.addActionListener(new spinReelOne());
	    btnSpin.addActionListener(new spinReelTwo());
	    btnSpin.addActionListener(new spinReelThree());
	 
	    btnOne = new JButton("BET ONE");//bet one button action
	    btnOne.addActionListener(new betOneCoin());
	   
	    btnMax = new JButton("BET MAX");//betmax button procees
	    btnMax.addActionListener(new betCoinMax());
	    
	    btnReset = new JButton("RESET");//reset button process
	    btnReset.addActionListener(new resetVal());
	    
	    btnAdd = new JButton("ADD COIN");//add coin button process
	    btnAdd.addActionListener(new addCoin());
	    
	    btnStat = new JButton("STATISTIC");//statistics button process
	    btnStat.addActionListener(new Stats());
	    
	    
	    
	    layout.gridx = 1;
	    layout.gridy = 3;
	    layout.insets = new Insets(50,5,5,5);
	    contentPane.add(btnSpin,layout);
	    
	    layout.gridx = 0;
	    layout.gridy = 6;
	    layout.insets = new Insets(5,5,5,5);
	    contentPane.add(btnOne,layout);;
	    
	    layout.gridx = 0;
	    layout.gridy = 7;
	    
	    contentPane.add(btnMax,layout);
	    
	    layout.gridx = 0;
	    layout.gridy = 8;
	    contentPane.add(btnReset,layout);
	    
	    layout.gridx = 0;
	    layout.gridy = 5;
	    layout.insets = new Insets(50,5,5,5);
	    contentPane.add(btnAdd,layout);
	    
	    layout.gridx = 2;
        layout.gridy = 12;
    contentPane.add(btnStat,layout);
    
	    lblcredit=new JLabel("Available Credit -");
	    layout.gridx = 1;
	    layout.gridy = 5;
	    layout.insets = new Insets(55,50,5,5);
	    

       contentPane.add(lblcredit,layout);

       getcredits=new JLabel("10");
        layout.gridx = 2;
        layout.gridy = 5;

        contentPane.add(getcredits,layout);
        lblbet=new JLabel("Bet Amount        -");
        
        layout.gridx = 1;
	    layout.gridy = 6;
	    layout.insets = new Insets(5,50,5,5);

       contentPane.add(lblbet,layout);

       getBets=new JLabel("0");
        layout.gridx = 2;
        layout.gridy = 6;

        contentPane.add(getBets,layout);
        
       
       
	}
	
	//Add coin class
	
	class addCoin implements ActionListener{
		
		 public void actionPerformed(ActionEvent event) {
			 
			 
			 String coins=getcredits.getText(); // getting the avaulable credits
			 int coinNO=Integer.parseInt(coins);


			
				 coinNO++;// adding one to it
 
			 
             String count=String.valueOf(coinNO);
			 
             getcredits.setText(count);//setting amount
		 }
		 
	}
	
	//bet coin class
	
	class betOneCoin implements ActionListener{                 //Only one coin is bet
		
		 int count;
		 
		 public void actionPerformed(ActionEvent event1) {
			 String Availablecount;
			  String countAddcoin=getcredits.getText(); // get the current bet credits
				 int Coins=Integer.parseInt(countAddcoin);
			 
				 if(Coins<=0 ){
				 
				 JOptionPane.showMessageDialog(null,"No coins,Please Add a coin before bet! ",  "Coin Alert", JOptionPane.INFORMATION_MESSAGE);

				}else{
			    
					if(getBets.getText().isEmpty()){
						Availablecount=String.valueOf(betCoin++);

					}else{
						
			 int countInt=Integer.parseInt(getBets.getText());
			// int cre=Integer.parseInt(getcredits.getText());
			// int bet=Integer.parseInt(getBets.getText());
			// int x=cre-bet;
			 
			  count=++countInt; // adding one to bet amount

			 if(getcredits.getText().equals("0")){
				 JOptionPane.showMessageDialog(null,"Please add coins first",  "Alert", JOptionPane.INFORMATION_MESSAGE);

			 }else{

			 }
			// betCoin++;
			 Availablecount=String.valueOf(count);
			 
			 }
					getBets.setText(Availablecount); // setting bet amount
			 String betArea=getBets.getText();
		   

		     int betInt=Integer.parseInt(betArea);
			  if(!betArea.isEmpty() && Coins>0){
				 // substracting one from credits
				  int credit=Coins-1;
				  if(credit>=0){
				  String credit1=String.valueOf(credit);
				  getcredits.setText(credit1);  // setting available credit
				  }
			  }
		}
			 
	}	 
		 
}
	class betCoinMax implements ActionListener{
		 String count;
		 int bet;

		 public void actionPerformed(ActionEvent event) {
			 String creditArea=getcredits.getText(); //getting credits
			 int crInt=Integer.parseInt(creditArea);
			 if(getBets.getText().isEmpty()){ // checking bet amount is empty
				  bet=3;
				 
			 }else{
			 int countInt=Integer.parseInt(getBets.getText()); 
				 bet=countInt+3;  // adding 3 to the available bet
			
			 
			 }
			 int betting=Integer.parseInt(getBets.getText());
			 int Total=crInt-3;
			 if(Total>=0 ){  // reducing bet amount from credit
				 int Total1=crInt-3;

				 getcredits.setText(String.valueOf(Total1));// setting the available credits
			 count=String.valueOf(bet);
			 }else{
				 JOptionPane.showMessageDialog(null,"ERROR",  "Please add coins first", JOptionPane.INFORMATION_MESSAGE);

				 
			 }
			 getBets.setText(count);

		 }
	}
	
	class resetVal implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) { // resetting the values to 10 and zero
			getcredits.setText("10");	//credit value
			getBets.setText("0"); // bet value
			Statics object3=new Statics(num);
			object3.statisReset();
		
	
		}
		
	}
	
	
	
	
	public class spinReelOne implements Runnable,ActionListener{  // spinning the first reel

		@Override
		public void run() {
            firstReel();
           

           }
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(getBets.getText().isEmpty() || getBets.getText().equals("0")){ //checking bet amount is zero
				 JOptionPane.showMessageDialog(null,"Please bet coins first",  "No Bet Alert", JOptionPane.INFORMATION_MESSAGE);

				
			}else{
				
				reelOneThread=new Thread(new spinReelOne());  //running the first reel
                  
				reelOneThread.start();
			     

				
			}
		}
		}
	 public void firstReel(){
		int num=0;
		for(int i=0;i<1000;i++){
			
			num=r.nextInt(6);//getting a random no less than 6
			lblimg1.setIcon(img.get(num));
			try {
				reelOneThread.sleep(10); //waiting 10 miliseconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	 
	
	 
	 public class spinReelTwo implements Runnable,ActionListener{

			@Override
			public void run() {
				secondReel();
	           

	           }
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(getBets.getText().isEmpty() || getBets.getText().equals("0")){
					 //JOptionPane.showMessageDialog(null,"Plaese bet first",  "Alert", JOptionPane.INFORMATION_MESSAGE);

					
				}else{
					
					reelTwoThread=new Thread(new spinReelTwo()); 
	                  
					reelTwoThread.start();
				     

					
				}
			}
			}
		 public void secondReel(){
			int num=0;
			for(int i=0;i<1000;i++){
				
				num=r.nextInt(6);
				lblimg2.setIcon(img.get(num));
				try {
					reelTwoThread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
		}
		 public class spinReelThree implements Runnable,ActionListener{

				@Override
				public void run() {
					thirdReel();
		           

		           }
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(getBets.getText().isEmpty() || getBets.getText().equals("0")){
						 //JOptionPane.showMessageDialog(null,"Plaese bet first",  "Alert", JOptionPane.INFORMATION_MESSAGE);

						
					}else{
						
						reelThreeThread=new Thread(new spinReelThree()); 
		                  
						reelThreeThread.start();
					     

						
					}
				}
				}
			 public void thirdReel(){
				int num=0;
				for(int i=0;i<1000;i++){
					
					num=r.nextInt(6);
					lblimg3.setIcon(img.get(num));
					try {
						reelThreeThread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				
				
			}
			

	 class click implements MouseListener{  // stopping reels when one is clicked

	        @Override
	        public void mouseClicked(MouseEvent e) {
	            try{
	               synchronized (reelOneThread) {
	            	   reelOneThread.stop();
	                } 
	               synchronized (reelTwoThread) {
	            	   reelTwoThread.stop();
		                } synchronized (reelThreeThread) {
		                	reelThreeThread.stop();
		                } 
		                
		                similar();
		                
            }catch(Exception ex){}
	        }

	        @Override
	        public void mousePressed(MouseEvent e) {
	        }

	        @Override
	        public void mouseReleased(MouseEvent e) {
	        }

	        @Override
	        public void mouseEntered(MouseEvent e) {
	        }

	        @Override
	        public void mouseExited(MouseEvent e) {
	        }
	        
	        }
	 
	 
	 public Reel similar(){
		 
		 
		 boolean status=false;
		 if(lblimg1.getIcon()==lblimg2.getIcon()){// checking first two images
			 status=true;
		       for(int i=0;i<6;i++){
		        	if(lblimg1.getIcon()==img.get(i)){// getting the index value of the icons
		        		 
		        		// assinig values according to their index
		        		if(i==0)
		        			value=7;
		        		else if(i==1)
		        			value=6;    
		        		else if(i==2)
		        			value=5;
		        		else if(i==3)
		        			value=4;
		        		else if(i==4)
		        			value=3;
		        		else if(i==5)
		        			value=2;
		        		
		              	}	
		              }
		        	
		        	}else if(lblimg1.getIcon()==lblimg3.getIcon()){
		        		status=true;

		        		for(int i=0;i<6;i++){
		    	        	if(lblimg1.getIcon()==img.get(i)){
		    	        		if(i==0)
				        			value=7;
				        		else if(i==1)
				        			value=6;
				        		else if(i==2)
				        			value=5;
				        		else if(i==3)
				        			value=4;
				        		else if(i==4)
				        			value=3;
				        		else if(i==5)
				        			value=2;
		    	              	}	
		    	              }

		        	}else if(lblimg2.getIcon()==lblimg3.getIcon()){
		        		status=true;

		        		for(int i=0;i<6;i++){
		    	        	if(lblimg2.getIcon()==img.get(i)){
		    	        		if(i==0)
				        			value=7;
				        		else if(i==1)
				        			value=6;
				        		else if(i==2)
				        			value=5;
				        		else if(i==3)
				        			value=4;
				        		else if(i==4)
				        			value=3;
				        		else if(i==5)
				        			value=2;
		    	              	}	
		    	              }

		        		
		        	}else if(lblimg2.getIcon()==lblimg3.getIcon() && lblimg1.getIcon()==lblimg3.getIcon()){
		   			 status=true;

		        		for(int i=0;i<6;i++){
		    	        	if(lblimg1.getIcon()==img.get(i)){
		    	        		if(i==0)
				        			value=7;
				        		else if(i==1)
				        			value=6;
				        		else if(i==2)
				        			value=5;
				        		else if(i==3)
				        			value=4;
				        		else if(i==4)
				        			value=3;
				        		else if(i==5)
				        			value=2;
		    	              	}	
		    	              }

		        		
		        	}
		 
		 if(status){
	        	
	        	countNumWinn++; // increasing no of wins by one
             num.setCountWinn(countNumWinn);
	        	countTimes++; // incrimenting the no of times
	        	 Credit=Integer.parseInt(getBets.getText());
	        	avgCredit1=avgCredit1+Credit; // winning credits
				 //JOptionPane.showMessageDialog(null,"Congrats,You have Won this Match",  "Well Done", JOptionPane.INFORMATION_MESSAGE);
				   int credits=Integer.parseInt(getcredits.getText()); // getting the credits
			        int bet=Integer.parseInt(getBets.getText());  // getting the bet amount
			        int winAmt=bet*value;
			        int Total=credits+bet*value;   // getting the winning amount
			        JOptionPane.showMessageDialog(null,"Congrats,You have won "+winAmt+" credits !",  "Well Done", JOptionPane.INFORMATION_MESSAGE);
			        String Tot=String.valueOf(Total);  // converting to string
			        getcredits.setText(Tot); // setting the new credit value
			        getBets.setText("0"); // setting the bet to zero

	        }else{ // losses
	        	
	        	countNumloss++; // incrimenting the no of losses by one
	        	num.setNoLoss(countNumloss);
	        	countTimes++;// incrimenting no of times
	        	 Credit=Integer.parseInt(getBets.getText());
		        	avgCredit2=avgCredit2-Credit; //loosing credits

				 JOptionPane.showMessageDialog(null,"Sorry, You have loose ",  "Bad Luck", JOptionPane.INFORMATION_MESSAGE);
				 getBets.setText("0");

	        }
		 avgCredit=avgCredit2+avgCredit1; // difference of win and loss
		 num.setAvgcredit(avgCredit); 
		 num.setNoTime(countTimes);
	      
			return num;
		 	 
	 }
	 
	 
	 
	 class Stats implements ActionListener{
		 
			public void actionPerformed(ActionEvent arg0) {
                       new Statics(num);	//statics 			
					}
			}
	 
}



