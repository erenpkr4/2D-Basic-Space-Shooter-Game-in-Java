import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;


import javax.swing.event.MouseInputAdapter;

public class MouseInput extends MouseInputAdapter implements MouseListener {
	
	private ObjectHandler handler;
	private Camera camera;
	private SpriteSheet ss;
	
	public MouseInput(ObjectHandler handler, Camera camera, SpriteSheet ss) {
		this.handler = handler;
		this.camera = camera;
		this.ss = ss;
	}
	
	public void mousePressed(MouseEvent e) {
		int menuX = e.getX();
		int menuY = e.getY();
		
		//Play Button
		//	public Rectangle playButton = new Rectangle(430, 250,130,50);
		if(Game.gameState == Game.State.MENU) {
		if(menuX >= 430 && menuX <= 560) {
			if(menuY >= 250 && menuY <= 300) {
				//PRESSED PLAY BUTTON
				Game.gameState = Game.State.GAME;
			}
		}
		
		//Quit Button
		//	public Rectangle quitButton = new Rectangle(430, 450,130,50);
		if(menuX >= 430 && menuX <= 560) {
			if(menuY >= 450 && menuY <= 500) {
				//PRESSED QUIT BUTTON
				System.exit(0);
			}
		}
		}
		
		if(Game.gameState == Game.State.GAMEOVER) {
			if(menuX >= 430 && menuX <= 560) {
				if(menuY >= 450 && menuY <= 500) {
					//PRESSED QUIT BUTTON
					System.exit(0);
				}
			}
		}
		if(Game.gameState == Game.State.GAMEWON) {
			if(menuX >= 430 && menuX <= 560) {
				if(menuY >= 450 && menuY <= 500) {
					//PRESSED QUIT BUTTON
					System.exit(0);
				}
			}
		}
		int mx = (int) (e.getX() + camera.getX());
		int my = (int) (e.getY() + camera.getY());
		
		for(int i=0; i<handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() ==  ID.Player) {
				handler.addObject(new Bullet(tempObject.getX()+16, tempObject.getY()+24, ID.Bullet, handler, mx , my,ss));

			}
			

		}
	}

}
