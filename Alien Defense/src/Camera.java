
public class Camera {
	private float x,y;
	
	Camera(float x, float y){
		this.x=x;
		this.y=y;
		
	}
	
	public void tick(GameObject object) {
		
		//STATIC CAMERA
		//x= object.getX()-1000/2;
		//y=object.getY()-563/2;
		
		//DYNAMIC CAMERA
		x += ((object.getX()-x)-1000/2) * 0.05f;
		y += ((object.getY()-y)-563/2) * 0.05f;
		
		if(x<=0) x=0;
		if(x>=1060) x= 1060;
		if(y<=0) y=0;
		if(y>620) y=620;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}