package nl.voorbeeld.coolgame;

import nl.saxion.act.playground.R;
import nl.saxion.act.playground.view.GameBoardView;
import nl.saxion.act.playground.view.SpriteCache;
import nl.voorbeeld.coolgame.objects.Enemy;
import nl.voorbeeld.coolgame.objects.Player;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

/**
 * A view on the Siege of Zubrowka game board.
 * 
 * @author team Wild Ponies
 */
public class CoolGameBoardView extends GameBoardView {
	private static final String TAG = "GameView";

	/**
	 * Constructor.
	 */
	public CoolGameBoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGameView();
	}

	/**
	 * Constructor.
	 */
	public CoolGameBoardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initGameView();
	}

	/**
	 * Loads all images that will be used for the game.
	 */
	private void initGameView() {
		Log.d(TAG, "Loading all images");

		SpriteCache spriteCache = SpriteCache.getInstance(); 
		spriteCache.setContext(this.getContext());		

		// Load the 'empty' cell bitmap and tell the tile view that this is the
		// image to use for cells without GameObject
		spriteCache.loadTile("empty", R.drawable.grasstile1);
		spriteCache.loadTile("wall", R.drawable.concrete1);
		setEmptyTile("empty");
		
		// Load the images for the GameObjects

		spriteCache.loadTile(Player.PLAYER_IMAGE, R.drawable.player);
		spriteCache.loadTile(Enemy.Enemy1_IMAGE, R.drawable.civilian3);
		spriteCache.loadTile(Enemy.Enemy2_IMAGE, R.drawable.civilian4);
		spriteCache.loadTile(Enemy.Enemy3_IMAGE, R.drawable.civilian5);
		// Load the image for the bullet (projectile)
		spriteCache.loadTile("bullet", R.drawable.bullet);
	}
}
