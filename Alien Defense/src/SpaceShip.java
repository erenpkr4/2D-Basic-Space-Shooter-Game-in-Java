import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SpaceShip extends GameObject {
	
	ObjectHandler handler;
	
	private BufferedImage spaceship_image;

	public SpaceShip(int x, int y, ID id, ObjectHandler handler, SpriteSheet ss) {
		super(x, y, id,ss);
		this.handler = handler;
		
		spaceship_image = ss.grabImage(1, 1, 30,30);
	}

	public void tick() {
		x += speedX;
		y += speedY;
		
		collision();
		
		//SMOOTH MOVEMENT
		if(handler.isUp()) speedY=-5;
		else if(!handler.isDown()) speedY=0;
		
		if(handler.isDown()) speedY=5;
		else if(!handler.isUp()) speedY=0;
		
		if(handler.isRight()) speedX=5;
		else if(!handler.isLeft()) speedX=0;
		
		if(handler.isLeft()) speedX=-5;
		else if(!handler.isRight()) speedX=0;
		
		if(Game.gameState == Game.State.GOTOLEVEL2) {
			handler.removeObject(this);
		}
		
		if(Game.gameState == Game.State.GOTOLEVEL3) {
			handler.removeObject(this);
		}
	}
	
	private void collision() {
		for(int i=0; i<handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId()== ID.Block) {
				if(getBounds().intersects(tempObject.getBounds())) {
					x += speedX * -1;
					y += speedY * -1;
				}
			}
			
			if(tempObject.getId()== ID.Enemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					Game.playerHp--;
				}
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(spaceship_image,x,y,null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}
	
	public float getSpeed() {
		return speedX + speedY;
	}

}
