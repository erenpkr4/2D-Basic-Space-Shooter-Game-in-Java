import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Enemy extends GameObject {
	
	private ObjectHandler handler;
	int hp = 100;
	
	private BufferedImage enemy_image;

	public Enemy(int x, int y, ID id, ObjectHandler handler,SpriteSheet ss) {
		super(x, y, id,ss);
		this.handler = handler;
		
		enemy_image = ss.grabImage(4, 1, 32, 32);
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
		}

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(enemy_image,x,y,null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}

}
