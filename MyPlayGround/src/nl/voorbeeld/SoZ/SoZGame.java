package nl.voorbeeld.SoZ;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import nl.saxion.act.playground.R;
import nl.saxion.act.playground.model.Game;
import nl.saxion.act.playground.model.GameBoard;
import nl.voorbeeld.SoZ.objects.Enemy;
import nl.voorbeeld.SoZ.objects.Muur;
import nl.voorbeeld.SoZ.objects.Player;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

public class SoZGame extends Game {
	public static final String TAG = "SoZGame";
	private MainActivity activity;
	private int score;
	private Player player = new Player();
	private boolean gameOver = false;
	private final int MAX_ENEMIES_ON_GAMEBOARD = 8;
	public boolean _stop = false;
	private int enemiesToSpawn;

	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	private ArrayList<Muur> muurList = new ArrayList<Muur>();

	private Timer enemyMovementTimer;

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
	 * matthijs hier is het example:
	 * game.getSoundPool().play(game.AK47_ONE_SHOT, 1, 1, 1, 0, 1);
	 */

	public SoZGame(MainActivity activity) {

		super(new SoZBoard(), new Savegame());
		this.activity = activity;
		savegame = new Savegame();
		savegame.leesSaveGameUitFile();
		currentLevel = savegame.getLevel();

		enemiesToSpawn = (currentLevel * 4) + 1;

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

		muurList.add(new Muur());
		board.addGameObject(muurList.get(muurList.size() - 1), 0,
				board.getHeight() - 2);
		muurList.add(new Muur());
		board.addGameObject(muurList.get(muurList.size() - 1), 1,
				board.getHeight() - 2);
		muurList.add(new Muur());
		board.addGameObject(muurList.get(muurList.size() - 1), 2,
				board.getHeight() - 2);
		muurList.add(new Muur());
		board.addGameObject(muurList.get(muurList.size() - 1), 3,
				board.getHeight() - 2);
		muurList.add(new Muur());
		board.addGameObject(muurList.get(muurList.size() - 1), 4,
				board.getHeight() - 2);
		muurList.add(new Muur());
		board.addGameObject(muurList.get(muurList.size() - 1), 5,
				board.getHeight() - 2);
		muurList.add(new Muur());
		board.addGameObject(muurList.get(muurList.size() - 1), 6,
				board.getHeight() - 2);
		muurList.add(new Muur());
		board.addGameObject(muurList.get(muurList.size() - 1), 7,
				board.getHeight() - 2);
		muurList.add(new Muur());
		board.addGameObject(muurList.get(muurList.size() - 1), 8,
				board.getHeight() - 2);

		for (Muur muur : muurList) {
			muur.muurDamagedCheck(board);
		}

		startEnemyMovementTimer();
		// startEnemySpawnTimer();
		board.updateView();
	}

	public void gameOver() {
		getGameBoard().removeAllObjects();

		while (muurList.size() > 0) {
			muurList.remove(0);
		}
		while (enemyList.size() > 0) {
			enemyList.remove(0);
		}
		enemyMovementTimer.cancel();
		enemyMovementTimer.purge();

		gameOver = true;
		gameBoard.updateView();

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

	public void startEnemyMovementTimer() {
		enemyMovementTimer = new Timer();
		Log.i("Timer", "EnemyMovement timer created");

		enemyMovementTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				activity.runOnUiThread(new Runnable() {
					public void run() {
						// Move enemies
						for (int i = 0; i < enemyList.size(); i++) {
							enemyList.get(i).callMovement(gameBoard);
						}
						if (!gameOver) {
							if (enemiesToSpawn > 0) {
								boolean enemySpawned = false;
								while (!enemySpawned) {
									int random = (int) (Math.random() * 9);
									if (getGameBoard().getObject(random, 0) == null) {

										enemyList.add(new Enemy());
										getGameBoard()
												.addGameObject(
														enemyList.get(enemyList
																.size() - 1),
														random, 0);
										enemySpawned = true;
										gameBoard.updateView();
									}
								}
							}
							enemiesToSpawn--;
						}
					}
				});

				Log.i("Timer", "enemyMovement and spawning timer fired");

			}
		}, 600, 1000);
	}

	private void levelCompleted(int currentLevel) {
		getGameBoard().removeAllObjects();

		while (muurList.size() > 0) {
			muurList.remove(0);
		}
		while (enemyList.size() > 0) {
			enemyList.remove(0);
		}
		enemyMovementTimer.cancel();
		enemyMovementTimer.purge();

		gameOver = true;
		gameBoard.updateView();

		Intent intent = new Intent(activity, CutsceneActivity.class);
		activity.beginActivity(intent);
	}

	public MainActivity getActivity() {
		return activity;
	}
	
}
