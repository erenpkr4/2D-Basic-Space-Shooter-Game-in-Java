import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected int x,y;
	protected float speedX,speedY;
    protected ID id;
	protected SpriteSheet ss;
    
	public GameObject(int x, int y, ID id, SpriteSheet ss) {
		this.x=x;
		this.y=y;
		this.id=id;
		this.ss=ss;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract Rectangle getBounds();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}

	public float getSpeedY() {
		return speedY;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	} 
	
	
	

}
