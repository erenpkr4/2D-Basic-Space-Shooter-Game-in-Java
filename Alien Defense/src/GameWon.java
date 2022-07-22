import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GameWon {
	
	public Rectangle quitButton = new Rectangle(430, 450,130,50);
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font font0 = new Font("italic", Font.ITALIC, 60);
		g.setFont(font0);
		g.setColor(Color.green);
		g.drawString("YOU WIN! CONGRATS!", 200, 200);
		g.drawString("Score: "+ Game.playerHp, 250, 350);
		
		Font font1 = new Font("arial", Font.BOLD, 30);
		g.setFont(font1);
		g.drawString("Quit", 440, 480);
		g2d.draw(quitButton);
		
	}

}
