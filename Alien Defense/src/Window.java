import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	
	public Window(int width,int height, String title, Game game) {
		
		//DECLARING THE SIZE AND TITLE
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));
		
		//DECLARING WINDOW FRAME PROPERTIES
		frame.add(game);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
