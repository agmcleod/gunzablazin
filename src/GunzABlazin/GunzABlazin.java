package GunzABlazin;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class GunzABlazin extends StateBasedGame {
	private Image player;
	
	public static final int GAMEPLAYSTATE = 1;
	public static final int ENDGAMESTATE = 2;
	public static final int GAMEOVERSTATE = 3;
	
	public GunzABlazin() {
		super("GunzABlazin - platform game");
		addState(new GamePlayState(GAMEPLAYSTATE));
		addState(new EndGameState(ENDGAMESTATE));
		addState(new GameOverState(GAMEOVERSTATE));
	}

	@Override
	public void initStatesList(GameContainer gc) 
		throws SlickException {
		// TODO Auto-generated method stub
		this.getState(GAMEPLAYSTATE).init(gc, this);		
	}
	
	public static void main(String args[]) 
		throws SlickException {
		AppGameContainer app = new AppGameContainer(new GunzABlazin());
		app.setDisplayMode(800, 600, false);
		app.start();
	}
	
}
