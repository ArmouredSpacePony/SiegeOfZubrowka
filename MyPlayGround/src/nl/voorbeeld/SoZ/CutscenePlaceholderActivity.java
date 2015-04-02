package nl.voorbeeld.SoZ;

import nl.saxion.act.playground.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

@SuppressWarnings("unused")
public class CutscenePlaceholderActivity extends Activity {
	private View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameover);
		view = findViewById(R.id.view);
		
		//view.setBackgroundResource(R.drawable.gameoverfinal);
		registerOnViewClick();
		
		
	}
	
	private void registerOnViewClick() {

		// Add a click listener to the button that calls initNewGame()
		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
	}

	

}
