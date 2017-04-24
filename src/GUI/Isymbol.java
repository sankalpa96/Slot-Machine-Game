package GUI;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;

 interface Isymbol {
	 
	 public ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();
	 
	void SetImage();
	ImageIcon getImage(String location,String name);
	

}
