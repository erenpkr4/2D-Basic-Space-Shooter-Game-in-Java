import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Shield extends GameObject{
	
	private ObjectHandler handler;
	int hp = 50;
	
	private BufferedImage shield_image;

	public Shield(int x, int y, ID id,ObjectHandler handler, SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
		
		shield_image = ss.grabImage(6, 2, 32, 32);
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
        g.drawImage(shield_image, x,y,null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}

}
