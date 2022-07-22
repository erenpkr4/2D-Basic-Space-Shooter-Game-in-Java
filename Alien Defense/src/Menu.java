import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playButton = new Rectangle(430, 250,130,50);
	public Rectangle creditsButton = new Rectangle(430, 350,130,50);
	public Rectangle quitButton = new Rectangle(430, 450,130,50);
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font font0 = new Font("italic", Font.ITALIC, 60);
		g.setFont(font0);
		g.setColor(Color.green);
		g.drawString("ALIEN DEFENSE", 260, 200);
		
		Font font1 = new Font("arial", Font.BOLD, 30);
		g.setFont(font1);
		g.drawString("Play", 440, 280);
		g.drawString("Credits", 440, 380);
		g.drawString("Quit", 440, 480);
		g2d.draw(playButton);
		g2d.draw(creditsButton);
		g2d.draw(quitButton);
		
		Font font2 = new Font("tutorial", Font.PLAIN, 20);
		g.setFont(font2);
		g.drawString("Mission: Kill 3 Alien Bosses.", 700, 450);
		g.drawString("Lose as little health as possible", 700, 470);
		g.drawString("to achieve a higher score", 700, 490);
		
	}

}
