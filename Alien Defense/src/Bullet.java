import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject {
	
	private ObjectHandler handler;

	public Bullet(int x, int y, ID id, ObjectHandler handler, int mx, int my,SpriteSheet ss) {
		super(x, y, id,ss);
		this.handler = handler;
		int speed =10;
		
		double bulletAngle = Math.toDegrees(Math.atan2(my-y, mx-x));
		speedX = (float)(Math.cos(Math.toRadians(bulletAngle))*speed);
		speedY = (float)(Math.sin(Math.toRadians(bulletAngle))*speed);
	}

	public void tick() {
		x += speedX;
		y += speedY;
		
		for(int i=0; i<handler.object.size(); i++) {
			GameObject tempGameObject = handler.object.get(i);
			
			if(tempGameObject.getId() == ID.Block) {
				if(getBounds().intersects(tempGameObject.getBounds())) {
					handler.removeObject(this);
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillOval(x, y, 8, 8);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,8,8);
	}

}
