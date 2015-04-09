package nl.voorbeeld.SoZ.objects;
import nl.saxion.act.playground.model.Game;
import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;
import nl.voorbeeld.SoZ.SoZGame;
import android.util.Log;
//import java.awt.Graphics;

public class Player extends GameObject {
	public static final String PLAYER_IMAGE = "playertemplate";
	public static final String PLAYER_IMAGE2 = "playertemplate2";
	public static final String PLAYER_IMAGE3 = "playertemplate3";
	private String plaatje;
	
	public Player(GameBoard gameBoard){
		if (gameBoard.getGame().savegame.getEquiptWep().equals("ak")){
			plaatje = PLAYER_IMAGE;
		}else if(gameBoard.getGame().savegame.getEquiptWep().equals("shotgun")){
			plaatje = PLAYER_IMAGE2;
		}else{
			plaatje = PLAYER_IMAGE3;
		}
	}

	/** Returns the ImageId of the image to show. */
	@Override
	public String getImageId() {
		return plaatje;
	}

	/** Called when the user moves this player. */
	@Override
	public void onTouched(GameBoard gameBoard) {
	}

	public void moveLeft(GameBoard gameBoard) {
		Log.d(SoZGame.TAG, "Moved Player");

		// player moves to the left
		int newPosX = getPositionX() - 1;
		int newPosY = getPositionY();

		// If new position is over the edge of the board, do nothing
		if (newPosX < 0|| ((SoZGame)(gameBoard.getGame())).isGameOver()) {
			return;
		}

		// Move player to the new position and redraw the app
		gameBoard.moveObject(this, newPosX, newPosY);
		gameBoard.updateView();
	}

	public void moveRight(GameBoard gameBoard) {
		Log.d(SoZGame.TAG, "Moved Player");

		// player moves to the right
		int newPosX = getPositionX() + 1;
		int newPosY = getPositionY();

		// If new position is over the edge of the board, do nothing
		if (newPosX > gameBoard.getWidth()-1 || ((SoZGame)(gameBoard.getGame())).isGameOver()) {
			return;
		}

		((SoZGame)gameBoard.getGame()).getSoundPool().play(SoZGame.WALK_SOUND, 1, 1, 1, 0, 1);
		// Move player to the new position and redraw the app
		gameBoard.moveObject(this, newPosX, newPosY);
		gameBoard.updateView();
	}
	
	public void shoot(GameBoard gameBoard) {
		Log.d(SoZGame.TAG, "Fired Bullet");
		Log.i("fireed", ""+gameBoard.getGame().savegame.getEquiptWep());
		Projectile p = new ShotgunBullet();
		if (gameBoard.getGame().savegame.getEquiptWep().equals("ak")){
			((SoZGame)gameBoard.getGame()).getSoundPool().play(SoZGame.AK47_SOUND, 1, 1, 1, 0, 1);
			p = new MachinegunBullet();
			if (gameBoard.getObject(getPositionX(), gameBoard.getHeight()-3)instanceof Enemy){
				
				((SoZGame)gameBoard.getGame()).RemoveEnemy((Enemy) gameBoard.getObject(getPositionX(), gameBoard.getHeight()-3));
				gameBoard.removeObject(gameBoard.getObject(getPositionX(), gameBoard.getHeight()-3));
				
			}else if (gameBoard.getObject(getPositionX(), gameBoard.getHeight()-3)instanceof Projectile){
				return;
			} else {
				gameBoard.addGameObject(p, getPositionX(), gameBoard.getHeight()-3);
				((SoZGame)gameBoard.getGame()).projectileFire(p);
			}
		}else if (gameBoard.getGame().savegame.getEquiptWep().equals("shotgun")){
			Log.i("ejvowvb", "shotgun");
			((SoZGame)gameBoard.getGame()).getSoundPool().play(SoZGame.ITHACA_SOUND, 1, 1, 1, 0, 1);
			if (gameBoard.getObject(getPositionX(), gameBoard.getHeight()-3)instanceof Enemy){
				p= new ShotgunBullet(1);
				gameBoard.removeObject(gameBoard.getObject(getPositionX(), gameBoard.getHeight()-3));
				((SoZGame)gameBoard.getGame()).RemoveEnemy((Enemy) gameBoard.getObject(getPositionX(), gameBoard.getHeight()-3));
				
			}else if (gameBoard.getObject(getPositionX(), gameBoard.getHeight()-3)instanceof Projectile){
				return;
			}else{
				p = new ShotgunBullet();
			}
			gameBoard.addGameObject(p, getPositionX(), gameBoard.getHeight()-3);
			((SoZGame)gameBoard.getGame()).projectileFire(p);
			
		}else if (gameBoard.getGame().savegame.getEquiptWep().equals("sniper")){
			((SoZGame)gameBoard.getGame()).getSoundPool().play(SoZGame.M14_SOUND, 1, 1, 1, 0, 1);
			
			if (gameBoard.getObject(getPositionX(), gameBoard.getHeight()-3)instanceof Enemy){
				p= new SniperBullet(1);
				gameBoard.removeObject(gameBoard.getObject(getPositionX(), gameBoard.getHeight()-3));
				((SoZGame)gameBoard.getGame()).RemoveEnemy((Enemy) gameBoard.getObject(getPositionX(), gameBoard.getHeight()-3));
				
			}else if (gameBoard.getObject(getPositionX(), gameBoard.getHeight()-3)instanceof Projectile){
				return;
			}else{
				p = new SniperBullet();
			}
			gameBoard.addGameObject(p, getPositionX(), gameBoard.getHeight()-3);
			((SoZGame)gameBoard.getGame()).projectileFire(p);
		}
		((SoZGame)gameBoard.getGame()).checkLevelComp();
		gameBoard.updateView();
	}
	
}
