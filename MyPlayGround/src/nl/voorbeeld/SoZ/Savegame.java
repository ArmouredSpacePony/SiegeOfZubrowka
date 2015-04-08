package nl.voorbeeld.SoZ;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

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

	private int bodyArmor;

	private int guards;
	private int dogs;

	private final int maxMuur = 9;

	private ArrayList <Integer> muur = new ArrayList<Integer>();



	public Savegame() {
		level = 1;

		points = 0;
		
		equiptWep="ak";

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
		for (int i : muur) {
			muur[i] = 100;
			Log.d("lol", ""+muur[i]);
		}*/
	}

	public void leesSaveGameUitFile() {
		File file = new File("savegame.txt");
		try {
			in = new Scanner(file);

		} catch (FileNotFoundException e) {
			return;
		}

		int loadedLevel;
		int loadedPoints;
		String loadedEquiptWep = equiptWep;
		boolean loadedAk;
		boolean loadedHandgun;
		boolean loadedShotgun;
		boolean loadedSniper;
		int loadedBodyArmor;
		int loadedGuards;
		int loadedDogs;
		int[] loadedMuur = new int[maxMuur];

		in.useDelimiter("/");

		if (in.hasNext() && in.hasNextInt()) {
			loadedLevel = Integer.parseInt(in.next());
			if (loadedLevel < 1) {
				return;
			}
		} else {
			return;
		}
		if (in.hasNext() && in.hasNextInt()) {
			loadedPoints = Integer.parseInt(in.next());
			if (loadedPoints < 0) {
				return;
			}
		} else {
			return;
		}
		if (in.hasNext()){
			if (in.next().equals("ak")) {

				loadedEquiptWep = "ak";
			} else if (in.next().equals("handgun")) {
				loadedEquiptWep = "handgun";
			} 
			else if (in.next().equals("shotgun")) {
				loadedEquiptWep = "shotgun";
			}
			else if (in.next().equals("sniper")) {
				loadedEquiptWep = "sniper";
			}else {
				return;
			}
		}
		
		if (in.hasNext()) {
			if (in.next().equals("true")) {

				loadedAk = true;
			} else if (in.next().equals("false")) {
				loadedAk = false;
			} else {
				return;
			}
		} else {
			return;
		}
		if (in.hasNext()) {
			if (in.next().equals("true")) {

				loadedHandgun = true;
			} else if (in.next().equals("false")) {
				loadedHandgun = false;
			} else {
				return;
			}
		} else {
			return;
		}
		if (in.hasNext()) {
			if (in.next().equals("true")) {

				loadedShotgun = true;
			} else if (in.next().equals("false")) {
				loadedShotgun = false;
			} else {
				return;
			}
		} else {
			return;
		}
		if (in.hasNext()) {
			if (in.next().equals("true")) {

				loadedSniper = true;
			} else if (in.next().equals("false")) {
				loadedSniper = false;
			} else {
				return;
			}
		} else {
			return;
		}
		if (in.hasNext() && in.hasNextInt()) {
			loadedBodyArmor = Integer.parseInt(in.next());
			if (loadedBodyArmor < 0) {
				return;
			}
		} else {
			return;
		}
		if (in.hasNext() && in.hasNextInt()) {
			loadedGuards = Integer.parseInt(in.next());
			if (loadedGuards < 0) {
				return;
			}
		} else {
			return;
		}
		if (in.hasNext() && in.hasNextInt()) {
			loadedDogs = Integer.parseInt(in.next());
			if (loadedDogs < 0) {
				return;
			}
		} else {
			return;
		}
		for (int i : loadedMuur) {
			if (in.hasNext() && in.hasNextInt()) {
				loadedMuur[i] = Integer.parseInt(in.next());
				if (loadedMuur[i] < 0) {
					return;
				}
			} else {
				return;
			}
		}

		level = loadedLevel;

		points = loadedPoints;
		
		equiptWep =loadedEquiptWep;
		
		ak = loadedAk;
		handgun = loadedHandgun;
		shotgun = loadedShotgun;
		sniper = loadedSniper;

		guards = loadedGuards;
		dogs = loadedGuards;

	//	for (int i : muur) {
	//		muur.get(i) = loadedMuur[1];
	//	}
		return;
	}

	public void schrijfSaveGame() {
		File file = new File("savegame.txt");
		try {
			PrintWriter out = new PrintWriter(file);
			out.print(level + "/" + points + "/" + equiptWep + "/" + ak + "/" + handgun + "/"
					+ shotgun + "/" + sniper + "/" + guards + "/" + dogs + "/"
					+ "/" + muur.get(0)+ "/" + muur.get(1)+ "/" + muur.get(2)+ "/" + muur.get(3)
					+ "/" + muur.get(4)+ "/" + muur.get(5)+ "/" + muur.get(6)+ "/" + muur.get(7)
					+ "/" + muur.get(8));
		} catch (FileNotFoundException e) {

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
		if(guards < 0){
			this.guards = 0;
		}else{
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