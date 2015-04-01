package nl.voorbeeld.SoZ;

import java.util.Random;

import nl.saxion.act.playground.R;
import nl.saxion.act.playground.model.*;
import nl.saxion.act.playground.view.GameBoardView;
import nl.voorbeeld.SoZ.objects.*;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

@SuppressWarnings("unused")
public class SoZGame extends Game {
	public static final String TAG = "SoZGame";
	private MainActivity activity;
	private int score;
	private Player player = new Player();
	private boolean gameOver = false;
	private final int MAX_ENEMIES_ON_GAMEBOARD = 8;
	public boolean _stop = false;
	private int enemiesToSpawn;
	private Handler mHandler;
	private Enemy enemy1, enemy2, enemy3, enemy4, enemy5, enemy6, enemy7,
			enemy8, enemy9, enemy10;

	// init desoundpool om gamesounds te laden
	private SoundPool soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC,
			0);

	// geeft de soundpool die gebruikt word voor de game
	public SoundPool getSoundPool() {
		return soundPool;
	}

	// laad een sound in
	public final int AK47_ONE_SHOT_SOUND;

	/*
	 * mathijs hier is het example: game.getSoundPool().play(game.AK47_ONE_SHOT,
	 * 1, 1, 1, 0, 1);
	 */

	public SoZGame(MainActivity activity) {
		super(new SoZBoard());
		this.activity = activity;
		enemy1 = new Enemy();
		enemy2 = new Enemy();
		enemy3 = new Enemy();
		enemy4 = new Enemy();
		enemy5 = new Enemy();
		enemy6 = new Enemy();

		initNewGame();

		SoZBoardView gameView = activity.getGameBoardView();
		GameBoard gameBoard = getGameBoard();
		gameView.setGameBoard(gameBoard);

		gameView.setFixedGridSize(gameBoard.getWidth(), gameBoard.getHeight());
		AK47_ONE_SHOT_SOUND = soundPool.load(activity.getApplicationContext(),
				R.raw.ak47_1, 1);
	}
	
	final Runnable spawn = new Runnable() {
		
		@Override
		public void run() {
			/*
			int days = 4;
			enemiesToSpawn = (int) (12 + 3.35 * days * 3.5);
			int enemiesLeft = 5;
			while (enemiesLeft > 1) {
				Random r = new Random();
				int x = r.nextInt(8);
				int y = 0;
				GameObject objectAtNewPos = gameBoard.getObject(x, y);
				if (objectAtNewPos != null) {
				} else {
					gameBoard.addGameObject(new Enemy(), x, y);
					enemiesLeft--;
				}
			}
			spawnEnemies(gameBoard);
			*/

		};
	};

	final Runnable eMovement = new Runnable() {
		@Override
		public void run() {
			/*

			enemy1.callMovement(gameBoard);
			enemy2.callMovement(gameBoard);
			enemy3.callMovement(gameBoard);
			enemy4.callMovement(gameBoard);
			enemy5.callMovement(gameBoard);
			enemy6.callMovement(gameBoard);
			*/
		};
	};

	public void initNewGame() {
		score = 0;
		gameOver = false;

		GameBoard board = getGameBoard();
		board.removeAllObjects();

		board.addGameObject(player, 4, 17);

		mHandler = new Handler(Looper.getMainLooper());

		mHandler.postAtTime(spawn, 1000);
		mHandler.postAtTime(eMovement, 1000);

		board.updateView();
	}

	/**
	 * days=placeholder for days(level)
	 */
	public void spawnEnemies(GameBoard gameboard) {
		// TODO

		/*
		 * while (enemiesLeft > 1) { Random r = new Random(); int x =
		 * r.nextInt(8); int y = 0; GameObject objectAtNewPos =
		 * gameBoard.getObject(x, y); if (objectAtNewPos != null) { } else {
		 * board.addGameObject(new Enemy(), x, y); enemiesLeft--; } }
		 */

	}

	public void gameOver() {
		getGameBoard().removeAllObjects();
		mHandler.removeCallbacks(spawn, null);
		mHandler.removeCallbacks(eMovement, null);

		gameOver = true;
		_stop = true;
		player = null;

		Intent intent = new Intent(activity, GameOverActivity.class);
		activity.beginActivity(intent);
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void changeScore() {
		score++;
		activity.updateScoreLabel(score);
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

}
