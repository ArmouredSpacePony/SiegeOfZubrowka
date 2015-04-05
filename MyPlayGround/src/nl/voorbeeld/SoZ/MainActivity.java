package nl.voorbeeld.SoZ;

import nl.saxion.act.playground.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import nl.voorbeeld.SoZ.SoZGame;

/**
 * The main activity.
 * 
 * @author Team Wild Ponies
 */
public class MainActivity extends Activity {
	private SoZGame game;
	private SoZBoardView gameView;
	private TextView scoreLabel;
	private MediaPlayer mp_xmPlayer2;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// Load main.xml
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mp_xmPlayer2 = new MediaPlayer();
		mp_xmPlayer2 = MediaPlayer.create(this, R.raw.music);
		mp_xmPlayer2.setLooping(true);
		mp_xmPlayer2.start();
		// Find some of the user interface elements
		gameView = (SoZBoardView) findViewById(R.id.game);
		scoreLabel = (TextView) findViewById(R.id.scoreTextView);

		// Create the game object. This contains all data and functionality
		// belonging to the game
		game = new SoZGame(this);

		// Do something when user clicks new game
		// registerNewGameButton();

		// Tell user to start the game
		Toast.makeText(getApplicationContext(), "Lets play!",
				Toast.LENGTH_SHORT).show();
		moveLeftButton();
		moveRightButton();
	}

	/**
	 * Set a new score on the score label
	 * 
	 * @param newScore
	 *            The new score.
	 */
	public void updateScoreLabel(int newScore) {
		scoreLabel.setText("Score: " + newScore);
	}

	/**
	 * Returns the view on the game board.
	 */
	public SoZBoardView getGameBoardView() {
		return gameView;
	}

	/**
	 * Install a listener to the 'New game'-button so that it starts a new game
	 * when clicked.
	 */

	/*
	 * private void registerNewGameButton() { // Find the 'New Game'-button in
	 * the activity final Button button1 = (Button)
	 * findViewById(R.id.newGameButton);
	 * 
	 * // Add a click listener to the button that calls initNewGame()
	 * button1.setOnClickListener(new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { game.initNewGame(); } }); }
	 */

	private void moveLeftButton() {
		// Find the 'New Game'-button in the activity
		final Button button1 = (Button) findViewById(R.id.moveLeftButton);

		// Add a click listener to the button that calls initNewGame()
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				game.getPlayer().moveLeft(game.getGameBoard());
			}
		});
	}

	private void moveRightButton() {
		// Find the 'New Game'-button in the activity
		final Button button1 = (Button) findViewById(R.id.moveRightButton);

		// Add a click listener to the button that calls initNewGame()
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				game.getPlayer().moveRight(game.getGameBoard());
			}
		});
	}

	public void beginActivity(Intent intent) {
		mp_xmPlayer2.stop();
		startActivity(intent);

	}
	/*
	 * public void onResume(){ game=new SoZGame(this); }
	 */
}
