package nl.voorbeeld.SoZ;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.FileHandler;

import nl.voorbeeld.SoZ.objects.Muur;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class Savegame {

	private Scanner in;

	private int level;

	private int points;

	private String equiptWep;

	private boolean ak;
	private boolean handgun;
	private boolean shotgun;
	private boolean sniper;
	private File file;
	private int bodyArmor;

	private int guards;
	private int dogs;

	private final int maxMuur = 9;

	private ArrayList<Integer> muur = new ArrayList<Integer>();

	private Activity activity;

	public Savegame(Activity activity) {
		this.activity = activity;
		file = new File(activity.getApplicationContext().getFilesDir(),
				"savegame.txt");

		level = 1;

		points = 0;

		equiptWep = "ak";

		ak = true;
		handgun = false;
		shotgun = false;
		sniper = false;

		bodyArmor = 0;

		guards = 0;
		dogs = 0;

		muur.add(100);
		muur.add(100);
		muur.add(100);
		muur.add(100);
		muur.add(100);
		muur.add(100);
		muur.add(100);
		muur.add(100);
		muur.add(100);
		/*
		 * for (int i : muur) { muur[i] = 100; Log.d("lol", ""+muur[i]); }
		 */
	}

	public void leesSaveGameUitFile() {
		try {
			in = new Scanner(file);
		} catch (IOException e) {
			return;
		}

		int loadedLevel;
		int loadedPoints;
		String loadedEquiptWep;
		boolean loadedAk;
		boolean loadedShotgun;
		boolean loadedSniper;
		int loadedGuards;
		int loadedDogs;
		ArrayList<Integer> loadedMuuren = new ArrayList<Integer>(9);

		in.useDelimiter("/");

		if (in.hasNext()) {
			String input = in.next();
			Log.i("saveing", input);
			loadedLevel = Integer.parseInt(input);
			if (loadedLevel < 1) {
				return;
			}
		} else {
			return;
		}
		if (in.hasNext()) {
			loadedPoints = Integer.parseInt(in.next());
			if (loadedPoints < 0) {
				return;
			}
		} else {
			return;
		}
		if (in.hasNext()) {
			String i = in.next();
			Log.i("saveing", "equip");
			if (i.equals("ak")) {
				Log.i("saveing", "ak");
				loadedEquiptWep = "ak";
			} else if (i.equals("handgun")) {
				loadedEquiptWep = "handgun";
			} else if (i.equals("shotgun")) {
				loadedEquiptWep = "shotgun";
			} else if (i.equals("sniper")) {
				loadedEquiptWep = "sniper";
			} else {
				return;
			}
		}else{
			return;
		}

		if (in.hasNext()) {
			String i = in.next();
			if(i.equals("true")){
				Log.i("saveing", "ownes ak");
				loadedAk = true;
			} else if (i.equals("false")) {
				loadedAk = false;
			} else {
				return;
			}
		} else {
			return;
		}
		
		if (in.hasNext()) {
			String i = in.next();
			if(i.equals("true")){
				Log.i("saveing", "ownes ak");
				loadedShotgun = true;
			} else if (i.equals("false")) {
				loadedShotgun = false;
			} else {
				return;
			}
		} else {
			return;
		}
		
		if (in.hasNext()) {
			String i = in.next();
			if(i.equals("true")){
				Log.i("saveing", "ownes sniper");
				loadedSniper = true;
			} else if (i.equals("false")) {
				loadedSniper = false;
			} else {
				return;
			}
		} else {
			return;
		}
		/*if (in.hasNext()) {
			Log.i("saveing", "armor");
			loadedBodyArmor = Integer.parseInt(in.next());
			if (loadedBodyArmor < 0) {
				return;
			}
		} else {
			return;
		}*/
		if (in.hasNext()) {
			Log.i("saveing", "jevnvobe");
			loadedGuards = Integer.parseInt(in.next());
			if (loadedGuards < 0) {
				return;
			}
		} else {
			return;
		}
		if (in.hasNext()) {
			loadedDogs = Integer.parseInt(in.next());
			if (loadedDogs < 0) {
				return;
			}
		} else {
			return;
		}
		int i=0;
		while (i < muur.size()) {
			if (in.hasNext()) {
				Log.i("array", i+"");
				loadedMuuren.add(Integer.parseInt(in.next()));
				
			}
			i++;
			Log.i("saveing", "loaded muur");
		}

		Log.i("saveing", "loaded");
		
		level = loadedLevel;

		points = loadedPoints;

		equiptWep = loadedEquiptWep;

		ak = loadedAk;
		shotgun = loadedShotgun;
		sniper = loadedSniper;

		guards = loadedGuards;
		dogs = loadedGuards;

		int i2 = 0;
		while (i2< muur.size()) {
			Log.i("array2", i2+"");
		muur.set(i2, loadedMuuren.get(i2));
		i2++;
		}
		
		Log.i("point", ""+points);
		return;
	}

	public void schrijfSaveGame() {
		String save = (level + "/" + points + "/" + equiptWep + "/" + ak + "/"
				+ shotgun + "/" + sniper + "/" + guards + "/"
				+ dogs +  "/" + muur.get(0) + "/" + muur.get(1) + "/"
				+ muur.get(2) + "/" + muur.get(3) + "/" + muur.get(4) + "/"
				+ muur.get(5) + "/" + muur.get(6) + "/" + muur.get(7) + "/" + muur
				.get(8));
		try {
			file.delete();
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
					file));
			bufferedWriter.write(save);
			bufferedWriter.close();

			Log.i("saveing", "saved");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return the level
	 */

	public int getLevel() {
		return level;
	}

	/**
	 * @param set
	 *            level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @param set
	 *            the points
	 */
	public void setPoints(int points) {
		
		this.points = points;
	}

	public String getEquiptWep() {
		return equiptWep;
	}

	public void setEquiptWep(String equiptWep) {
		this.equiptWep = equiptWep;
	}

	/**
	 * @return the ak
	 */
	public boolean isAk() {
		return ak;
	}

	/**
	 * @param set
	 *            the ak
	 */
	public void setAk(boolean ak) {
		this.ak = ak;
	}

	/**
	 * @return the handGun
	 */
	public boolean isHandGun() {
		return handgun;
	}

	/**
	 * @param set
	 *            the handGun
	 */
	public void setHandGun(boolean handGun) {
		this.handgun = handGun;
	}

	/**
	 * @return the shotgun
	 */
	public boolean isShotgun() {
		return shotgun;
	}

	/**
	 * @param set
	 *            the shotgun
	 */
	public void setShotgun(boolean shotgun) {
		this.shotgun = shotgun;
	}

	/**
	 * @return the sniper
	 */
	public boolean isSniper() {
		return sniper;
	}

	/**
	 * @param set
	 *            the sniper
	 */
	public void setSniper(boolean sniper) {
		this.sniper = sniper;
	}

	/**
	 * @return the bodyArmor
	 */
	public int getBodyArmor() {
		return bodyArmor;
	}

	/**
	 * @param set
	 *            the bodyArmor
	 */
	public void setBodyArmor(int bodyArmor) {
		this.bodyArmor = bodyArmor;
	}

	/**
	 * @return number of guards left
	 */
	public int getGuards() {
		return guards;
	}

	/**
	 * @param set
	 *            the number of guards left
	 */
	public void setGuards(int guards) {
		if (guards < 0) {
			this.guards = 0;
		} else {
			this.guards = guards;
		}
	}

	/**
	 * @return the number of dogs left
	 */
	public int getDogs() {
		return dogs;
	}

	/**
	 * @param set
	 *            the number of dogs left
	 */
	public void setDogs(int dogs) {
		if (dogs < 0) {
			this.dogs = 0;
		} else {
			this.dogs = dogs;
		}
	}

	/**
	 * returns het overige leven van een muur, geef de x coordinaat van de muur
	 * mee
	 */
	public int getMuur(int xCoordinaat) {
		return muur.get(xCoordinaat);
	}

	/**
	 * set het overige leven van een muur, geef de x coordinaat van de muur mee
	 * en hoeveel leven hij moet krijgen
	 */
	public void setMuur(int xCoordinaat, int leven) {
		if (leven < 0) {
			muur.set(xCoordinaat, 0);
		} else if (leven > 100) {
			muur.set(xCoordinaat, 100);
		} else {
			muur.set(xCoordinaat, leven);
		}
	}
}
