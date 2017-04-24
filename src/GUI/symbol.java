package GUI;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class symbol implements Isymbol{
	
	

	@Override
	public void SetImage() {
	
		images.add(getImage("images/redseven.png", "Seven"));
		images.add(getImage("images/bell.png", "Bell"));
		images.add(getImage("images/watermelon.png", "Watermelon"));
		images.add(getImage("images/plum.png", "Plum"));
		images.add(getImage("images/lemon.png", "Lemon"));
		images.add(getImage("images/cherry.png", "Cherry"));
		
		
	}

	@Override
	public ImageIcon getImage(String location,String name) {
		java.net.URL imgURL = getClass().getResource(location);
		if (imgURL != null) {
			return new ImageIcon(imgURL, name);
		} else {
			System.err.println("Couldn't find file: " + location);
			return null;
		}
		// TODO Auto-generated method stub
		
	}
	
	

}
