package nl.voorbeeld.SoZ;

import nl.saxion.act.playground.R;
import nl.saxion.act.playground.view.GameBoardView;
import nl.saxion.act.playground.view.SpriteCache;
import nl.voorbeeld.SoZ.objects.Enemy;
import nl.voorbeeld.SoZ.objects.MachinegunBullet;
import nl.voorbeeld.SoZ.objects.Muur;
import nl.voorbeeld.SoZ.objects.Player;
import nl.voorbeeld.SoZ.objects.ShotgunBullet;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

/**
 * A view on the Siege of Zubrowka game board.
 * 
 * @author team Wild Ponies
 */
public class SoZBoardView extends GameBoardView {
	private static final String TAG = "GameView";

	/**
	 * Constructor.
	 */
	public SoZBoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGameView();
	}

	/**
	 * Constructor.
	 */
	public SoZBoardView(Context context, AttributeSet attrs, int defStyle) {
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
		spriteCache.loadTile(Muur.MUUR_IMAGE, R.drawable.concrete1);
		// Load the image for the bullet (projectile)
		spriteCache.loadTile(MachinegunBullet.MACHINEGUN_IMAGE, R.drawable.bullet);
		spriteCache.loadTile(ShotgunBullet.SHOTGUN_IMAGE, R.drawable.bullet);
	}
}
