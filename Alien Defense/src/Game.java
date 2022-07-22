import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {
	
	//THIS PREVENTS AN ERROR, ECLIPSE SUGGESTED IT AND IT WORKS FINE
	private static final long serialVersionUID = 1L;
    
	private boolean isRunning = false;
	private Thread thread;
	private ObjectHandler handler;
	
	private BufferedImage levelDesign1 = null;
	private BufferedImage levelDesign2 = null;
	private BufferedImage levelDesign3 = null;
	private BufferedImage spriteSheet = null;
	private BufferedImage space = null;
	private SpriteSheet ss;
	private Camera camera;
	
	private Menu menu;
	private GameOver gameover;
	private GameWon gamewon;
	
	public static int level = 1;
	
	public static int playerHp = 100;
	
	public enum State	{
		MENU,
		GAME,
		GAMEOVER,
		GOTOLEVEL2,
		GOTOLEVEL3,
		GAMEWON
		;
	}
	
	public static State gameState = State.MENU;
	
	//DECLARING THE GAME'S WINDOW
	public Game() {
		new Window(1000,563,"Alien Defense!",this);

		
		handler = new ObjectHandler();
		camera = new Camera(0,0);
		this.addKeyListener(new KeyInput(handler));

		menu = new Menu();
		gameover = new GameOver();
		gamewon = new GameWon();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		levelDesign1 = loader.loadImage("/level1.png");
		levelDesign2 = loader.loadImage("/level2.png");
		levelDesign3 = loader.loadImage("/level3.png");
		spriteSheet = loader.loadImage("/spriteSheet.png");
		ss = new SpriteSheet(spriteSheet);
		
		space = ss.grabImage(4, 2, 32, 32);
		this.addMouseListener(new MouseInput(handler,camera,ss));
		start();
		loadLevel(levelDesign1);

		//handler.addObject(new Box(100,100,ID.Block));
	}
	
	//START AND STOP THREAD
	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	private void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	//GAME FRAME LOOP
	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta=0.0;
		long timer =System.currentTimeMillis();
		int frames = 0;
		  while(isRunning) {
			  long now = System.nanoTime();
			  delta += (now-lastTime)/ns;
			  lastTime = now;
			  
			  while(delta>=1) {
				  tick();
				  delta--;
			  }
			  render();
			  frames++;
			  
			  if(System.currentTimeMillis() - timer >1000) {
				  timer+= 1000;
				  frames=0;
			  }
		  }
		  
		  stop();
		
	}
	
	
	//TICK + FINDS THE PLAYER AND MAKES THE CAMERA FOLLOW IT
	public void tick() {
		if(gameState == State.GAME) {
			for(int i=0; i< handler.object.size(); i++) {
				if(handler.object.get(i).getId()== ID.Player) {
					camera.tick(handler.object.get(i));
				}
			}
			
			handler.tick();
		}
		
	}
	
	//PRE-LOADING FRAMES BEHIND THE SCREEN
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.black);
		g.fillRect(0, 0, 1000, 563);
		
		if(gameState == State.GAME) {

		
		//----------------DRAW HERE---------------------
		g2d.translate(-camera.getX(), -camera.getY());
		
		for(int xx=0; xx < 30*72; xx+= 32) {
			for(int yy=0; yy< 30*72; yy+=32) {
				g.drawImage(space,xx,yy,null);
			}
		}
		
		g.setColor(Color.green);
		g.drawString("SHOOT WITH MOUSE", 42, 70);
		
		g.drawString("MOVE WITH", 36, 430);
		g.drawString("ARROW KEYS", 36, 440);
		
		g.drawString("PRESS ESC TO PAUSE", 700, 50);

		handler.render(g);
		
		g2d.translate(camera.getX(), camera.getY());
		
		g.setColor(Color.red);
		g.fillRect(400, 5, 200, 32);
		g.setColor(Color.green);
		g.fillRect(400, 5, playerHp*2, 32);
		g.setColor(Color.black);
		g.drawRect(400, 5, 200, 32);
		
		g.setColor(Color.green);
		g.drawString("Velocity: ", 25, 25);
		g.drawString("Angle: ",	25, 45);
		}
		
		else if(gameState == State.MENU) {
			menu.render(g);
		}
		
		else if(gameState == State.GAMEOVER) {
			gameover.render(g);
		}
		
		else if(gameState == State.GAMEWON) {
			gamewon.render(g);
		}
		
		if(gameState == State.GOTOLEVEL2) {
			handler.tick();
			camera.setX(0);
			camera.setY(0);
			gameState = State.GAME;
			handler.render(g);
			handler.tick();
			loadLevel(levelDesign2);
		}
		
		if(gameState == State.GOTOLEVEL3) {
			handler.tick();
			camera.setX(0);
			camera.setY(0);
			gameState = State.GAME;
			handler.render(g);
			handler.tick();
			loadLevel(levelDesign3);
		}
		
		if(playerHp <=0) {
			Game.gameState = Game.State.GAMEOVER;
		}
		g.dispose();
		bs.show();
	}
	
	//LOAD LEVEL
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		
		for(int xx=0; xx<w; xx++) {
			for(int yy=0; yy<h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel>>16) & 0xff;
				int green = (pixel>>8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red==255 && green==255) {
					handler.addObject(new Shield(xx*32,yy*32,ID.Enemy,handler, ss));}
					
				if(red==255 && green==0) {
					handler.addObject(new Block(xx*32,yy*32,ID.Block,ss,handler));
				}
					
				if(blue==255 && green==255) {
						handler.addObject(new Boss(xx*32,yy*32, ID.Boss, handler,ss));}
				if(blue==255 && green==0) {
						handler.addObject(new SpaceShip(xx*32, yy*32, ID.Player, handler,ss));}
				
				if(green==255 && blue==0 && red==0) {
					handler.addObject(new Enemy(xx*32,yy*32, ID.Enemy, handler,ss));}
				}
			}
		}
	
	
	//MAIN CLASS
	public static void main(String args[]) {
		new Game();
	}



}
