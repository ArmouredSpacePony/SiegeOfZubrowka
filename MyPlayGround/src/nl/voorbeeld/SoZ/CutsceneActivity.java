package nl.voorbeeld.SoZ;

import nl.saxion.act.playground.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

@SuppressWarnings("unused")
public class CutsceneActivity extends Activity {
	private View view;
	boolean NewGameOfNiet;
	CutsceneActivity dit = this;
	Savegame savegame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cutscene);
		view = findViewById(R.id.view);
		
		savegame=new Savegame(dit);
		
		
		Intent intent=getIntent();
		intent.getExtras();
		NewGameOfNiet=intent.getBooleanExtra(MenuSchermActivity.START_GAME_NEW_OF_NIET, false);
		if(NewGameOfNiet){
			savegame.schrijfSaveGame();
			//TODO play furst cutscene
			registerOnViewClickNewGame();
		}else{
			savegame.leesSaveGameUitFile();
			int level = savegame.getLevel();
			savegame.setLevel(level+1);
			//TODO play cutscene based on level
			registerOnViewClick();
			
		}
	}
	
	private void registerOnViewClickNewGame() {

		// Add a click listener to the button that calls initNewGame()
		view.setOnClickListener(new View.OnClickListener() {
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
		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(dit, ShopActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	

}
