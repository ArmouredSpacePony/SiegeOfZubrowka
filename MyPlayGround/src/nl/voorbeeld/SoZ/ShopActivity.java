package nl.voorbeeld.SoZ;

import nl.saxion.act.playground.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopActivity extends Activity {

	Savegame savegame;

	ShopActivity dit = this;

	Button ak;
	Button shotgun;
	Button dogs;
	Button guards;
	Button muur;

	ImageView next;

	TextView score;
	TextView dogsText;
	TextView guardsText;
	TextView muurText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop);
		savegame = new Savegame(this);
		savegame.leesSaveGameUitFile();
		
		ak = (Button) findViewById(R.id.akButton);

		shotgun = (Button) findViewById(R.id.shotgunButton);
		dogs = (Button) findViewById(R.id.dogsButton);
		guards = (Button) findViewById(R.id.guardButton);
		muur = (Button) findViewById(R.id.muurButton);
		
		next= (ImageView) findViewById(R.id.imageView3);

		score = (TextView) findViewById(R.id.scoreTextView);
		dogsText = (TextView) findViewById(R.id.dogsNumberTextView);
		guardsText = (TextView) findViewById(R.id.guardsNumberTextView);
		muurText = (TextView) findViewById(R.id.muurHealthTextView);

		registerOnAkClick();
		//registerOnHandgunClick();
		registerOnShotgunClick();
		//registerOnSniperClick();
		registerOnDogsClick();
		registerOnGuardsClick();
		registerOnMuurClick();
		registerOnNextClick();

		resetButtonAndTextView();

	}

	private void resetButtonAndTextView() {
		score.setText("" + savegame.getPoints());
		if (savegame.isAk()) {
			ak.setText("Equip");
			if (savegame.getEquiptWep().equals("ak")) {
				ak.setClickable(false);
				ak.setText("Equiped");
			} else {
				ak.setClickable(true);
			}
		} else {
			ak.setText("Buy");
			if (savegame.getPoints() < 1000) {
				ak.setClickable(false);
			} else {
				ak.setClickable(true);
			}
		}
		/*
		 * if (savegame.isHandGun()) { handgun.setText("Equip"); if
		 * (savegame.getEquiptWep().equals("handgun")) {
		 * handgun.setClickable(false); handgun.setText("Equiped"); } else {
		 * handgun.setClickable(true); } } else { handgun.setText("Buy"); if
		 * (savegame.getPoints() < 500) { handgun.setClickable(false); } else {
		 * handgun.setClickable(true); } }
		 */
		if (savegame.isShotgun()) {
			shotgun.setText("Equip");
			if (savegame.getEquiptWep().equals("ak")) {
				shotgun.setClickable(false);
				shotgun.setText("Equiped");
			} else {
				shotgun.setClickable(true);
			}
		} else {
			shotgun.setText("Buy");
			if (savegame.getPoints() < 2000) {
				shotgun.setClickable(false);
			} else {
				shotgun.setClickable(true);
			}
		}/*
		 * if (savegame.isSniper()) { sniper.setText("Equip"); if
		 * (savegame.getEquiptWep().equals("ak")) { sniper.setClickable(false);
		 * sniper.setText("Equiped"); } else { sniper.setClickable(true); } }
		 * else { sniper.setText("Buy"); if (savegame.getPoints() < 4000) {
		 * sniper.setClickable(false); } else { sniper.setClickable(true); } }
		 */
		if (savegame.getPoints() < 200) {
			dogs.setClickable(false);
		} else {
			dogs.setClickable(true);
		}
		if (savegame.getPoints() < 500) {
			guards.setClickable(false);
		} else {
			guards.setClickable(true);
		}
		int destroidMuur = 0;
		int i = 0;
		while (i < 9) {
			destroidMuur = destroidMuur + (100 - savegame.getMuur(i));
			i++;
		}

		if (savegame.getPoints() < destroidMuur || destroidMuur == 0) {
			muur.setClickable(false);
		} else {
			muur.setClickable(true);
		}

		dogsText.setText(savegame.getDogs() + "");
		guardsText.setText(savegame.getGuards() + "");
		muurText.setText(900 - destroidMuur + "/900");

	}

	private void registerOnAkClick() {

		ak.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (((Button) v).getText().equals("Equip")) {
					savegame.setEquiptWep("ak");
				} else {
					savegame.setAk(true);
					savegame.setPoints(savegame.getPoints() - 1000);
				}
				resetButtonAndTextView();
			}

		});
	}

	/*
	 * private void registerOnHandgunClick() {
	 * 
	 * handgun.setOnClickListener(new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { if (((Button)
	 * v).getText().equals("Equip")) { savegame.setEquiptWep("handgun"); } else
	 * { savegame.setHandGun(true); savegame.setPoints(savegame.getPoints() -
	 * 500); } resetButtonAndTextView(); }
	 * 
	 * }); }
	 */
	private void registerOnShotgunClick() {

		shotgun.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (((Button) v).getText().equals("Equip")) {
					savegame.setEquiptWep("shotgun");
				} else {
					savegame.setShotgun(true);
					savegame.setPoints(savegame.getPoints() - 2000);
				}
				resetButtonAndTextView();
			}

		});
	}

	/*
	 * private void registerOnSniperClick() {
	 * 
	 * sniper.setOnClickListener(new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { if (((Button)
	 * v).getText().equals("Equip")) { savegame.setEquiptWep("sniper"); } else {
	 * savegame.setSniper(true); savegame.setPoints(savegame.getPoints() -
	 * 4000); } resetButtonAndTextView(); }
	 * 
	 * }); }
	 */
	private void registerOnDogsClick() {

		dogs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				savegame.setDogs(savegame.getDogs() + 1);
				savegame.setPoints(savegame.getPoints() - 200);

				resetButtonAndTextView();
			}

		});
	}

	private void registerOnGuardsClick() {

		guards.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				savegame.setGuards(savegame.getGuards() + 1);
				savegame.setPoints(savegame.getPoints() - 500);

				resetButtonAndTextView();
			}

		});
	}

	private void registerOnMuurClick() {

		muur.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				int i = 0;
				while (i < 9) {
					int levenErBij = 100 - savegame.getMuur(i);
					savegame.setPoints(savegame.getPoints() - levenErBij);
				}

				int i2 = 0;
				while (i2 < 9) {
					savegame.setMuur(1, 100);
					i2++;
				}

				resetButtonAndTextView();
			}

		});
	}

	private void registerOnNextClick() {

		next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(dit, MainActivity.class);
				startActivity(intent);
				finish();

			}

		});
	}

}
