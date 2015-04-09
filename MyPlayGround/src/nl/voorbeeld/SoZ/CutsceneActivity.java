package nl.voorbeeld.SoZ;

import nl.saxion.act.playground.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

@SuppressWarnings("unused")
public class CutsceneActivity extends Activity {
	private ImageView cutscene;
	boolean NewGameOfNiet;
	private MediaPlayer musicPlayer;
	CutsceneActivity dit = this;
	Savegame savegame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cutscene);
		cutscene = (ImageView) findViewById(R.id.cutsceneImage);

		savegame = new Savegame(dit);

		Intent intent = getIntent();
		intent.getExtras();
		NewGameOfNiet = intent.getBooleanExtra(
				MenuSchermActivity.START_GAME_NEW_OF_NIET, false);
		if (NewGameOfNiet) {
			savegame.schrijfSaveGame();
			cutscene.setBackgroundResource(R.drawable.cutscene);
			registerOnViewClickNewGame();
		} else {
			savegame.leesSaveGameUitFile();
			if (savegame.getLevel() == 2) {

				int level = savegame.getLevel();
				cutscene.setBackgroundResource(R.drawable.cutscene2);
				registerOnViewClick();
			} else if (savegame.getLevel() ==3) {
				int level = savegame.getLevel();

				// TODO play cutscene based on level

				cutscene.setBackgroundResource(R.drawable.cutscene3);

				registerOnViewClick();
			} else if (savegame.getLevel() ==4) {
				int level = savegame.getLevel();

				// TODO play cutscene based on level

				cutscene.setBackgroundResource(R.drawable.cutscene4);

				registerOnViewClick();
			} else if (savegame.getLevel() >= 5) {
				int level = savegame.getLevel();

				// TODO play cutscene based on level

				cutscene.setBackgroundResource(R.drawable.victoryscreen);

				registerOnViewClick();
			}

		}
	}

	private void registerOnViewClickNewGame() {

		// Add a click listener to the button that calls initNewGame()
		cutscene.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(dit, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	private void registerOnViewClick() {

		// Add a click listener to the button that calls initNewGame()
		cutscene.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(dit, ShopActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

}
