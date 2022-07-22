import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Boss extends GameObject {
	
	private ObjectHandler handler;
	int hp = 500;
	private BufferedImage boss_image;

	public Boss(int x, int y, ID id, ObjectHandler handler,SpriteSheet ss) {
		super(x, y, id,ss);
		this.handler = handler;
		
		boss_image = ss.grabImage(5, 1, 32, 32);
	}

	@Override
	public void tick() {
		for(int i=0; i<handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Bullet) {
				if(getBounds().intersects(tempObject.getBounds())){
					hp -= 50;
					handler.removeObject(tempObject);
					
				}

			}
		}
		
		if(hp <= 0) {
		   handler.removeObject(this);
		   if(Game.level == 1) {
			   Game.gameState = Game.State.GOTOLEVEL2;
			   Game.level = 2;
		   }
		   
		   else if(Game.level ==2) {
			   Game.gameState = Game.State.GOTOLEVEL3;
			   Game.level = 3 ;
		   }
		   
		   else if(Game.level == 3) {
			   Game.gameState = Game.State.GAMEWON;
		   }
		}
		
		if(Game.gameState == Game.State.GOTOLEVEL2) {
			handler.removeObject(this);
		}
		
		if(Game.gameState == Game.State.GOTOLEVEL3) {
			handler.removeObject(this);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(boss_image,x,y,null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}

}
