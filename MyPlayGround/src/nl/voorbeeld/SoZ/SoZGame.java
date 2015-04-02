package nl.voorbeeld.SoZ;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import nl.saxion.act.playground.R;
import nl.saxion.act.playground.model.*;
import nl.saxion.act.playground.view.GameBoardView;
import nl.voorbeeld.SoZ.objects.*;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
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

	private Enemy enemy1, enemy2, enemy3, enemy4, enemy5, enemy6, enemy7,
			enemy8, enemy9, enemy10;
	private Savegame savegame;
	private Muur muur1, muur2, muur3, muur4, muur5, muur6, muur7, muur8, muur9;

	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();

	final Handler ENEMYMOVEMENTHANDLER = new Handler();
	final Handler ENEMYSPAWNHANDLER = new Handler();

	private Timer enemyMovementTimer;
	private Timer enemySpawnTimer;

	private int currentLevel;

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
		enemyList.add(enemy1 = new Enemy());
		enemyList.add(enemy2 = new Enemy());
		enemyList.add(enemy3 = new Enemy());
		enemyList.add(enemy4 = new Enemy());
		enemyList.add(enemy5 = new Enemy());
		enemyList.add(enemy6 = new Enemy());
		enemyList.add(enemy7 = new Enemy());
		enemyList.add(enemy8 = new Enemy());
		enemyList.add(enemy9 = new Enemy());
		enemyList.add(enemy10 = new Enemy());
		
		muur1 = new Muur();
		muur2 = new Muur();
		muur3 = new Muur();
		muur4 = new Muur();
		muur5 = new Muur();
		muur6 = new Muur();
		muur7 = new Muur();
		muur8 = new Muur();
		muur9 = new Muur();

		savegame = new Savegame();

		if (enemy1 == null) {

		}
		initNewGame();

		SoZBoardView gameView = activity.getGameBoardView();
		GameBoard gameBoard = getGameBoard();
		gameView.setGameBoard(gameBoard);

		gameView.setFixedGridSize(gameBoard.getWidth(), gameBoard.getHeight());
		AK47_ONE_SHOT_SOUND = soundPool.load(activity.getApplicationContext(),
				R.raw.ak47_1, 1);
	}



	public void initNewGame() {
		score = 0;
		gameOver = false;

		GameBoard board = getGameBoard();
		board.removeAllObjects();

		board.addGameObject(player, 4, board.getHeight() - 1);

		board.addGameObject(muur1, 0, board.getHeight() - 2);
		board.addGameObject(muur2, 1, board.getHeight() - 2);
		board.addGameObject(muur3, 2, board.getHeight() - 2);
		board.addGameObject(muur4, 3, board.getHeight() - 2);
		board.addGameObject(muur5, 4, board.getHeight() - 2);
		board.addGameObject(muur6, 5, board.getHeight() - 2);
		board.addGameObject(muur7, 6, board.getHeight() - 2);
		board.addGameObject(muur8, 7, board.getHeight() - 2);
		board.addGameObject(muur9, 8, board.getHeight() - 2);


		startEnemyMovementTimer();
		startEnemySpawnTimer();
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
	
		gameOver = true;
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

	public Savegame getSavegame() {
		return savegame;
	}

	final Runnable enemySpawnRunnable = new Runnable() {
		public void run() {
			//Log.i("Runnable", "Launching enemySpawnRunnable");
			enemiesToSpawn = (int) ((currentLevel * 4)+2);
			while (enemiesToSpawn > 1) {
				Random r = new Random();
				int x = r.nextInt(8);
				int y = 0;
				GameObject objectAtNewPos = gameBoard.getObject(x, y);
				if (objectAtNewPos != null) {
				} else {
					gameBoard.addGameObject(new Enemy(), x, y);
					enemiesToSpawn--;
					if (enemiesToSpawn <= 0) {
						levelCompleted(currentLevel);
					}
				}
			}
		}
	};

	final Runnable enemyMovementRunnable = new Runnable() {
		public void run() {
			for (int i = 0; i < enemyList.size(); i++) {
				enemyList.get(i).callMovement(gameBoard);
			}
		}
	};

	private void UpdateEnemyMovement() {
		ENEMYMOVEMENTHANDLER.post(enemyMovementRunnable);
	}

	private void UpdateEnemySpawn() {

		ENEMYSPAWNHANDLER.post(enemySpawnRunnable);
	}

	public void startEnemyMovementTimer() {
		enemyMovementTimer = new Timer();
		Log.i("Timer", "EnemyMovement timer created");

		enemyMovementTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				UpdateEnemyMovement();

				Log.i("Timer", "enemyMovement timer fired");

			}
		}, 600, 1000);
	}

	public void startEnemySpawnTimer() {
		enemySpawnTimer = new Timer();
		Log.v("Timer", "EnemySpawning timer created");

		enemySpawnTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				UpdateEnemySpawn();
				Log.i("Timer", "enemySpawn timer fired");
			}

		}, 500, 1000);
	}

	private void levelCompleted(int currentLevel) {
		getGameBoard().removeAllObjects();
		gameOver = true;
		player = null;

		Intent intent = new Intent(activity, CutscenePlaceholderActivity.class);
		activity.beginActivity(intent);
	}

}
