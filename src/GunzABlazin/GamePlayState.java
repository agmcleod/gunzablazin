package GunzABlazin;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author  aaronmcleod
 */
public class GamePlayState implements GameState {
	/**
	 * @uml.property  name="stateID"
	 */
	private int stateID;	
	/**
	 * @uml.property  name="player"
	 * @uml.associationEnd  
	 */
	private Player player;	
	/**
	 * @uml.property  name="levels"
	 */
	private ArrayList<Level> levels;	
	private int currentLevel = 0;	
	private Image groundImage;
	private boolean jumpInitiated = false;
	private boolean isGrounded = false;
	private float jumpSpeedLimit = 0.8f;
	private float jumpSpeed = jumpSpeedLimit;
	private float playerSpeed = 0.3f;
	private float offsetX = 0.0f;
	private float offsetY = 0.0f;
	private float playerStartX = 100.0f;
	private float playerStartY = 100.0f;
	private boolean gameOver = false;
	private boolean lost = false;
	
	public GamePlayState(int stateID) {
		this.setStateID(stateID);
	}

	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
		gc.setVSync(true);
		Image spriteOne = new Image("resources/sprite1.png");
		Image playerImage = spriteOne.getSubImage(0, 0, 38, 83);
		groundImage = spriteOne.getSubImage(38, 0, 38, 83);
		player = new Player(playerStartX, playerStartY, (float)playerImage.getWidth(), 
			(float)playerImage.getHeight(), playerImage);
		playerImage.draw(player.getX(), player.getY());
		initializeLevels();
		
	}
	
	/**
	 * Initializes all the levels at the beginning of the game
	 */
	public void initializeLevels() {
		levels = new ArrayList<Level>();
		Level levelOne = new Level("Level One");
		levelOne.addGround(new Sprite(0.0f, 570.0f, 400.0f, 30.0f, groundImage));
		levelOne.addGround(new Sprite(400.0f, 540.0f, 300.0f, 30.0f, groundImage));
		levelOne.addGround(new Sprite(770.0f, 520.0f, 600.0f, 30.0f, groundImage));
		levelOne.addGround(new Sprite(900.0f, 490.0f, 300.0f, 30.0f, groundImage));
		levelOne.addGround(new Sprite(200.0f, 300.0f, 250.0f, 10.0f, groundImage));
		levels.add(levelOne);
		Level levelTwo = new Level("Level Two");
		levelTwo.addGround(new Sprite(0.0f, 450.0f, 300.0f, 30.0f, groundImage));
		levelTwo.addGround(new Sprite(315.0f, 445.0f, 300.0f, 10.0f, groundImage));
		levels.add(levelTwo);
	}
	
	/**
	 * Draws the surfaces for the given level
	 */
	public void drawSurfaces() {
		ArrayList<Sprite> tempGrounds = levels.get(currentLevel).getGrounds();
		for(int g = 0; g < tempGrounds.size(); g++) {
			Sprite ground = tempGrounds.get(g);
			Image groundGraphic = ground.getGraphic();
			tileImage((int)ground.getX() + (int)offsetX, (int)ground.getY() + (int)offsetY, 
				(int)ground.getSizeX(), (int)ground.getSizeY(), groundGraphic);
			
		}
	}
	
	/**
	 * Tiles an image correctly across a given surface area
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param pattern
	 */
	public void tileImage(int x, int y, int width, int height, Image pattern) {
		int cols = (int)Math.floor(width / pattern.getWidth()); 
	    int rows = (int)Math.floor(height / pattern.getHeight()); 
	       
	    for(int c = 0; c < cols; c++) { 
	       for(int r = 0; r < rows; r++) { 
	          pattern.draw(c * pattern.getWidth() + x, r * pattern.getHeight() + y); 
	       } 
	    } 
	       
	    // Draw last col 
	    int neww = (int)(width - (cols * pattern.getWidth())); 
	    Image sub = pattern.getSubImage(0, 0, neww, pattern.getHeight()); 

	    for(int r = 0; r < rows; r++) { 
	       sub.draw(cols * pattern.getWidth() + x, r * pattern.getHeight() + y); 
	    } 
	       
	    // Draw last row 
	    int newh = (int)(height - (rows * pattern.getHeight())); 
	    sub = pattern.getSubImage(0, 0, pattern.getWidth(), newh); 

	    for(int c = 0; c < cols; c++) { 
	       sub.draw(c * pattern.getWidth() + x, rows * pattern.getHeight() + y); 
	    } 
	       
	    // Draw bottom corner 
	    sub = pattern.getSubImage(0, 0, neww, newh); 
	    sub.draw(cols * pattern.getWidth() + x, rows * pattern.getHeight() + y);
	}

	@Override
	public void leave(GameContainer gc, StateBasedGame sbg)
			throws SlickException {		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		player.getGraphic().draw(player.getX() + offsetX, player.getY());
		drawSurfaces();
		g.translate(offsetX, offsetY);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		if(gameOver) {
			if(lost) {
				sbg.enterState(GunzABlazin.GAMEOVERSTATE);
			}
			else {
				sbg.enterState(GunzABlazin.ENDGAMESTATE);
			}
		}
		checkKeyInput(sbg, delta);
		pullPlayerDownByGravity(delta);
		if(player.getY() > 600) {
			boolean livesLeft = removeLifeOrEndGame();
			if(livesLeft) {
				resetPlayerPosition();
			}
		}
		updateCamera(delta, sbg);
		isGrounded = checkGroundCollision();
	}
	
	/**
	 * Checks whether the player is colliding with a surface below
	 * @return
	 */
	public boolean checkGroundCollision() {
		boolean intersects = false;
		ArrayList<Sprite> grounds = levels.get(currentLevel).getGrounds();
		for(int i = 0; i < grounds.size(); i++) {
			Sprite surface = grounds.get(i);
			intersects = surface.intersects(player);
			if(intersects) {
				player.setY(surface.getY() - player.getSizeY());
				jumpInitiated = false;
				break;
			}
		}
		return intersects;
	}
	
	/**
	 * Checks the side collisions, excepting a boolean parameters whether to check against the left
	 * side or the right side		
	 * @param leftSide
	 * @return
	 */
	public boolean checkSideCollisions(boolean leftSide) {
		boolean intersects = false;
		ArrayList<Sprite> grounds = levels.get(currentLevel).getGrounds();
		for(int i = 0; i < grounds.size(); i++) {
			Sprite surface = grounds.get(i);
			if(leftSide) {
				intersects = surface.intersectsToTheLeftSide(player);
			}
			else {
				intersects = surface.intersectsToTheRightSide(player);
			}
			
			if(intersects) {
				player.setY(surface.getY() - player.getSizeX());
				jumpInitiated = false;
				break;
			}
		}
		return intersects;
	}
	
	/**
	 * If the player is not on a surface and is not jumping,
	 * let them fall down.
	 * @param delta - time passed modifier
	 */
	public void pullPlayerDownByGravity(int delta) {
		if(!isGrounded && !jumpInitiated) {
			jumpSpeed = jumpSpeedLimit;
			float increment = (jumpSpeed * delta);
			player.incrementY(increment);
		}
	}
	
	/**
	 * Checks for key input
	 * @param sbg
	 * @param delta
	 */
	public void checkKeyInput(StateBasedGame sbg, int delta) {
		Input input = sbg.getContainer().getInput();
		if(input.isKeyDown(Keyboard.KEY_UP) || jumpInitiated) {
			if(!jumpInitiated && isGrounded) {
				jumpInitiated = true;
				jumpSpeed = jumpSpeedLimit * -1;
				player.incrementY(jumpSpeed * delta);
			}
			else {
				if(jumpSpeed < 0) {
					jumpSpeed *= 1 - jumpSpeedLimit / 19.0f;
					if(jumpSpeed > -jumpSpeedLimit / 2.5f) {
						jumpSpeed *= -1;
					}
				}
				if(jumpSpeed > 0 && jumpSpeed <= jumpSpeedLimit) {
					jumpSpeed *= 1 + jumpSpeedLimit / 10.0f;
				}
				player.incrementY(jumpSpeed * delta);
			}
		}
		if(input.isKeyDown(Keyboard.KEY_LEFT)) {
			// move left
			float x = player.getX();
			float amount = playerSpeed * delta;
			x -= amount;
			if(x - (player.getSizeX() / 2) <= 0) {
				if(!checkSideCollisions(false)) {
					player.setX(player.getSizeX() / 2);
				}
			}
			else if(!checkSideCollisions(false)) {
				player.setX(x);
			}
		}
		if(input.isKeyDown(Keyboard.KEY_RIGHT)) {
			// move right
			float amount = playerSpeed * delta;
			if(!checkSideCollisions(true)) {
				player.incrementX(amount);
				if(endOfLevel()) {
					goToNextLevelOrEndGame(sbg);
				}
			}
		}		
	}
	
	public void goToNextLevelOrEndGame(StateBasedGame sbg) {
		// if the current level is the last
		if(currentLevel >= levels.size() -1) {
			// go to the next state.
			gameOver = true;
			lost = false;
		}
		else {
			currentLevel++;
			resetPlayerPosition();
		}
	}
	
	public void resetPlayerPosition() {
		player.setX(playerStartX);
		player.setY(playerStartY);
		offsetX = 0.0f;
	}
	
	/**
	 * Checks if the player is at the end of the level
	 * @return boolean
	 */	
	public boolean endOfLevel() {
		boolean atEnd = false;		
		if(player.getRightX() >= levels.get(currentLevel).furthestXPointInLevel()) {
			atEnd = true;
		}		
		return atEnd;
	}
	
	/**
	 * Updates the camera as required when the player moves
	 * @param delta
	 * @param sbg
	 */
	public void updateCamera(int delta, StateBasedGame sbg) {
		Input input = sbg.getContainer().getInput();
		if(input.isKeyDown(Keyboard.KEY_LEFT) || input.isKeyDown(Keyboard.KEY_RIGHT)) {
			float x = player.getX();
			if(x <= 400.0f) {
				offsetX = 0.0f;				
			}
			else {
				offsetX = -(x - 400.0f);
				// offsetX = playerSpeed * delta;
			}
			
		}
	}
	
	/**
	 * This method removes a life from the player, and checks if they have any left.
	 * If none are left, they are taken to the game over state with the option to restart
	 */
	public boolean removeLifeOrEndGame() {
		if(player.removeAndReturnLives() <= 0) {
			lost = true;
			gameOver = true;
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setInput(Input arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(int keyval, char keyChar) {

	}

	@Override
	public void keyReleased(int arg0, char arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerButtonPressed(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerButtonReleased(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerDownPressed(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerDownReleased(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerLeftPressed(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerLeftReleased(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerRightPressed(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerRightReleased(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerUpPressed(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerUpReleased(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(int arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param stateID
	 * @uml.property  name="stateID"
	 */
	public void setStateID(int stateID) {
		this.stateID = stateID;
	}

	/**
	 * @return
	 * @uml.property  name="stateID"
	 */
	public int getStateID() {
		return stateID;
	}
	
	/**
	 * @return
	 * @uml.property  name="levels"
	 */
	public ArrayList<Level> getLevels() {
		return levels;
	}

}
