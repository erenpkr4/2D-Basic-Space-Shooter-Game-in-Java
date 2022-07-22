import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

public class ObjectHandler {
	
	//LINKED LIST TO STORE ENEMIES AND PLAYER OBJECTS
	//LinkedList<GameObject> object = new LinkedList<GameObject>();
	ArrayList<GameObject> object = new ArrayList<GameObject>();
	
	private boolean up=false, down=false, right=false, left=false;
	
	//UPDATES ENEMY/PLAYER OBJECT'S STATUS
	public void tick() {
		for(int i=0; i< object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			if(Game.gameState == Game.State.GOTOLEVEL2) {
				removeAll();
				removeObject(tempObject);
			}
			
			else if(Game.gameState == Game.State.GOTOLEVEL3) {
				removeAll();
				removeObject(tempObject);
			}
			else {
			tempObject.tick();}
		}
	}
	
	public void render(Graphics g) {
		for(int i=0; i< object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject tempObject) {
		object.add(tempObject);
	}
	
	public void removeObject(GameObject tempObject) {
		object.remove(tempObject);
	}
	
	public void removeAll() {
		for(int i=0; i<object.size(); i++) {
			object.remove(i);
		}
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	
}
