import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

//much to change!
public class PhysicsEngine extends Thread
{
	private boolean laeuft;
	private ArrayList<SpielObjekt> schuesse;
	private SpielObjekt laser;
	private ArrayList<SpielObjekt> gegner;
	private ArrayList<SpielObjekt> schuesseGegner;
	private ArrayList<SpielObjekt> drops;
	private SpielObjekt ufoSpieler;
	private GUI g;
	private Shop shop;
	private Drop drop;

	// durch shop oder drops veraenderbar
	public static int schussStaerke = 1;
	public static long schussCooldown = 750;
	public static int schussGeschwindigkeit = 40;
	public static int ufoGeschwindigkeit = 100;
	public static int maxHuelle = 10;
	public static int huelle = maxHuelle;
	public static int leben = 3;
	public static int schild = 0;

	public static boolean autoshoot = true;

	// DebugLog
	private static String className = "PhysicsEngine";

	public PhysicsEngine(GUI gui)
	{
		g = gui;
		// g.wartenAufStart();
		drop = new Drop();
		laeuft = true;
		schuesse = new ArrayList<SpielObjekt>();
		gegner = new ArrayList<SpielObjekt>();
		Bild b = JElementHelper.baueBild("Bilder/spaceship.png", 262, 425, 75, 35);
		ufoSpieler = new SpielObjekt(b, g.gibHintergrund());
		ufoSpieler.leben = leben;
		ufoSpieler.huelle = huelle;
		Bild l = JElementHelper.baueBild("Bilder/red.png", -1, -1);
		laser = new SpielObjekt(l, g.gibHintergrund());
		laser.entferneSelbst();
	}

	public void stopp()
	{
		laeuft = false;
		Debugger.log("stopp ausgefuehrt!", className, Debugger.gibDebugMain());
	}

	public double gibUfoSpielerX()
	{
		return ufoSpieler.gibIntX();
	}

	public double gibUfoSpielerY()
	{
		return ufoSpieler.gibIntY();
	}

	public SpielObjekt gibUfoSpieler()
	{
		return ufoSpieler;
	}

	public double gibmaxHuelleDurchHuelle()
	{
		return maxHuelle / huelle;
	}

	public void ufoSpielerMaxHuelleErhoehen(int L)
	{
		maxHuelle += L;
		ufoSpieler.huelle += L;
	}

	public void ufoSpielerHuelleAufMaxSetzen()
	{
		ufoSpieler.huelle = maxHuelle;
	}

	// Ufo gegner erzeugen
	public void standardGegnerStufe1Erzeugen()
	{
		Bild b = JElementHelper.baueBild("Bilder/spaceship1.png", RNDM.rndmX(), 0, 75, 35);
		SpielObjekt ufoGegner = new SpielObjekt(b, g.gibHintergrund());
		ufoGegner.setDeltaY(30);
		ufoGegner.leben = 2;
		ufoGegner.geldReward = 1;
		ufoGegner.punkte = 10;
		gegner.add(ufoGegner);
		Debugger.log("standardGegnerStufe1Erzeugen ausgefuehrt!", className, Debugger.gibDebugMain());
	}

	public void standardGegnerStufe2Erzeugen()
	{
		Bild b = JElementHelper.baueBild("Bilder/spaceship2.png", RNDM.rndmX(), 0, 75, 35);
		SpielObjekt ufoGegner = new SpielObjekt(b, g.gibHintergrund());
		ufoGegner.setDeltaY(30);
		ufoGegner.leben = 5;
		ufoGegner.geldReward = 7;
		ufoGegner.punkte = 20;
		gegner.add(ufoGegner);
		Debugger.log("standardGegnerStufe2Erzeugen ausgef�hrt!", className, Debugger.gibDebugMain());
	}

	public void gegnerSkalierendErzeugen()
	{
		Bild b = JElementHelper.baueBild("Bilder/spaceship_skalierend.png", RNDM.rndmX(), 0, 75, 35);
		SpielObjekt ufoGegner = new SpielObjekt(b, g.gibHintergrund());
		ufoGegner.setDeltaY(30 + g.gibShop().gibUpgradeAnzahl("u1") + g.gibShop().gibUpgradeAnzahl("u2")
				+ g.gibShop().gibUpgradeAnzahl("u3"));
		ufoGegner.leben = 1 + (int) ((g.gibShop().gibUpgradeAnzahl("u1") + g.gibShop().gibUpgradeAnzahl("u2")
				+ (g.gibShop().gibUpgradeAnzahl("u3") * 2)) / 3);
		ufoGegner.geldReward = 3 + (int) ((g.gibShop().gibUpgradeAnzahl("u1") + g.gibShop().gibUpgradeAnzahl("u2")
				+ (g.gibShop().gibUpgradeAnzahl("u3") * 2)) / 3);
		ufoGegner.punkte = 10 + (int) ((g.gibShop().gibUpgradeAnzahl("u1") + g.gibShop().gibUpgradeAnzahl("u2")
				+ (g.gibShop().gibUpgradeAnzahl("u3") * 2)) / 3);
		gegner.add(ufoGegner);
		Debugger.log("standardGegnerStufe2Erzeugen ausgefuehrt!", className, Debugger.gibDebugMain());
	}

	public void bossGegnerStufe1Erzeugen()
	{
		Bild b = JElementHelper.baueBild("Bilder/spaceship1.png", 125, 0, 350, 75);
		SpielObjekt ufoGegner = new SpielObjekt(b, g.gibHintergrund());
		ufoGegner.setDeltaY(5);
		ufoGegner.leben = 150;
		ufoGegner.geldReward = 200;
		ufoGegner.punkte = 1000;
		gegner.add(ufoGegner);
		Debugger.log("bossGegnerStufe1Erzeugen ausgefuehrt!", className, Debugger.gibDebugMain());
	}

	// schuss erzeugen
	public void schussErzeugen()
	{
		int ufoX;
		int anzahlSchuesse = g.gibShop().gibUpgradeAnzahl("u3") + 1;
		int mitteX = (ufoSpieler.getWidth() / 2);
		Bild b = JElementHelper.baueBild("Bilder/green.png", (int) ufoSpieler.gibX() + (ufoSpieler.getWidth() / 2),
				(int) ufoSpieler.gibY(), 4, 12);
		SpielObjekt schussSpieler = new SpielObjekt(b, g.gibHintergrund());
		schussSpieler.setDeltaY(-schussGeschwindigkeit);
		schussSpieler.leben = schussStaerke;
		schuesse.add(schussSpieler);
		Debugger.log("schuss ausgefuehrt!", className, Debugger.gibDebugMain());
	}

	// laser erzeugen
	public void laserErzeugen()
	{
		laser.entferneSelbst();
		int ufoX;
		int mitteX = (ufoSpieler.getWidth() / 2);
		Bild b = JElementHelper.baueBild("Bilder/red.png", (int) ufoSpieler.gibX() + (ufoSpieler.getWidth() / 2), ufoSpieler.gibIntY()-g.SIZE_Y, 16,
				g.SIZE_Y);
		laser = new SpielObjekt(b, g.gibHintergrund());
		laser.leben = schussStaerke / 2;
		laser.entfernt = false;
		Debugger.log("schuss ausgefuehrt!", className, Debugger.gibDebugMain());
	}

	public void dropErzeugen(int i)
	{

		if (i < 0)
		{
			// do nothing
		} else
		{
			Bild b = JElementHelper.baueBild("Bilder/green.png", RNDM.rndmX(), 0, 4, 12);
			if (i == 0)
			{

			}
			if (i == 1)
			{

			}
			if (i == 2)
			{

			}
			if (i == 3)
			{

			}
			if (i == 4)
			{

			}

			SpielObjekt dropObjekt = new SpielObjekt(b, g.gibHintergrund());
			dropObjekt.drop = i;
			drops.add(dropObjekt);
		}
	}

	public void dropNutzen(SpielObjekt drop)
	{
		ufoSpieler.leben += drop.leben;
		if (drop.huelle > 0)
		{
			ufoSpielerHuelleAufMaxSetzen();
		}

	}

	@Override
	public void run()
	{
		double deltaT = 20.0D;
		long cooldownSchuss = 0;
		long cooldownLaser = 1000;
		long cooldownLaserMax = 1000;
		long cooldownDebugMode = 0;
		long cooldownPause = 0;
		long cooldownUfoStufe1 = 0;
		long cooldownUfoStufe2 = 100000;
		long cooldownUfoSkalierend = 10000;
		long cooldownUfoBossStufe1 = 100000;
		g.wartenAufStart();

		while (laeuft)
		{
			// zeitmessung anfang
			long timeStart = System.currentTimeMillis();

			// cooldowns reduzieren
			if (cooldownSchuss >= 0)
			{
				cooldownSchuss -= deltaT;
				Debugger.log("Schuss cooldown gesenkt!", className, Debugger.gibDebugMain());
				Debugger.log("Neuer SchussCooldown wert: " + cooldownSchuss + "!", className, Debugger.gibDebugMain());
			}
			if (cooldownLaser > 0)
			{
				cooldownLaser -= deltaT;
				Debugger.log("Laser cooldown gesenkt!", className, Debugger.gibDebugMain());
				Debugger.log("Neuer LaserCooldown wert: " + cooldownSchuss + "!", className, Debugger.gibDebugMain());
			}
			g.setzeLaser((cooldownLaser) / 500);

			if (cooldownUfoStufe1 >= 0)
			{
				cooldownUfoStufe1 -= deltaT;
				Debugger.log("UfoStufe1 cooldown gesenkt!", className, Debugger.gibDebugMain());
				Debugger.log("Neuer UfoStufe1Cooldown wert: " + cooldownUfoStufe1 + "!", className,
						Debugger.gibDebugMain());
			}
			if (cooldownUfoStufe2 >= 0)
			{
				cooldownUfoStufe2 -= deltaT;
				Debugger.log("UfoStufe2 cooldown gesenkt!", className, Debugger.gibDebugMain());
				Debugger.log("Neuer UfoStufe2Cooldown wert: " + cooldownUfoStufe2 + "!", className,
						Debugger.gibDebugMain());
			}
			if (cooldownUfoSkalierend >= 0)
			{
				cooldownUfoSkalierend -= deltaT;
				Debugger.log("UfoSkalierend cooldown gesenkt!", className, Debugger.gibDebugMain());
				Debugger.log("Neuer UfoSkalierendCooldown wert: " + cooldownUfoSkalierend + "!", className,
						Debugger.gibDebugMain());
			}
			if (cooldownUfoBossStufe1 >= 0)
			{
				cooldownUfoBossStufe1 -= deltaT;
				Debugger.log("UfoBossStufe1 cooldown gesenkt!", className, Debugger.gibDebugMain());
				Debugger.log("Neuer UfoBossStufe1Cooldown wert: " + cooldownUfoBossStufe1 + "!", className,
						Debugger.gibDebugMain());
			}
			if (cooldownDebugMode >= 0)
			{
				cooldownDebugMode -= deltaT;
				Debugger.log("DebugMode cooldown gesenkt!", className, Debugger.gibDebugMain());
				Debugger.log("Neuer DebugMode wert: " + cooldownDebugMode + "!", className, Debugger.gibDebugMain());
			}
			if (cooldownPause >= 0)
			{
				cooldownPause -= deltaT;
				Debugger.log("Pause cooldown gesenkt!", className, Debugger.gibDebugMain());
				Debugger.log("Neuer PauseCooldown wert: " + cooldownPause + "!", className, Debugger.gibDebugMain());
			}

			// irgendein schuss kollidiert mit einem ufo?
			for (int i = 0; i < schuesse.size(); i++)
			{
				SpielObjekt schuss = schuesse.get(i);
				for (int j = 0; j < gegner.size(); j++)
				{
					SpielObjekt gegnerS = gegner.get(j);
					if (schuss.kollidiertMit(gegnerS))
					{
						gegnerS.leben -= schuss.leben;
						schuss.leben = 0;
						schuesse.remove(schuss);
						schuss.entferneSelbst();
						if (gegnerS.leben <= 0)
						{
							gegner.remove(gegnerS);
							gegnerS.entferneSelbst();
							Main.veraenderePunkte(gegnerS.punkte);
							Main.veraendereGeld(gegnerS.geldReward);
							// dropErzeugen(RNDM.rndmDrops(drop.dropWertungen));
						}
						break;
					}
				}
			}
			// laser kollidiert mit einem ufo?
			for (int j = 0; j < gegner.size(); j++)
			{
				SpielObjekt gegnerS = gegner.get(j);
				if (laser.kollidiertMit(gegnerS))
				{
						gegnerS.leben -= laser.leben;
					if (gegnerS.leben <= 0)
					{
						gegner.remove(gegnerS);
						gegnerS.entferneSelbst();
						Main.veraenderePunkte(gegnerS.punkte);
						Main.veraendereGeld(gegnerS.geldReward);
						// dropErzeugen(RNDM.rndmDrops(drop.dropWertungen));
					}

				}
			}

			// schuesse bewegen
			for (int i = 0; i < schuesse.size(); i++)
			{
				SpielObjekt schuss = schuesse.get(i);
				schuss.setzeX(schuss.gibX() + (schuss.getDeltaX() * deltaT) / 1000D);
				schuss.setzeY(schuss.gibY() + (schuss.getDeltaY() * deltaT) / 1000D);
				if (schuss.gibY() < 0)
				{
					schuesse.remove(schuss);
					schuss.entferneSelbst();
					// schuss.Leben = 0;
				}
			}

			// gegner bewegen
			for (int j = 0; j < gegner.size(); j++)
			{
				SpielObjekt gegnerS = gegner.get(j);
				gegnerS.setzeX(gegnerS.gibX() + (gegnerS.getDeltaX() * deltaT) / 1000D);
				gegnerS.setzeY(gegnerS.gibY() + (gegnerS.getDeltaY() * deltaT) / 1000D);
				if (gegnerS.gibY() > 500)
				{
					gegner.remove(gegnerS);
					gegnerS.entferneSelbst();
					Main.veraenderePunkte((int) (gegnerS.punkte * 2.5));
					// gegnerS.Leben = 0;
				}
				if (gegnerS.kollidiertMit(ufoSpieler))
				{
					gegner.remove(gegnerS);
					gegnerS.entferneSelbst();
					ufoSpieler.huelle -= gegnerS.leben;
					double huelleBerechnung = maxHuelle;
					double huelleX = ufoSpieler.huelle / huelleBerechnung;
					g.setzeHuelle(huelleX);
					if (ufoSpieler.huelle <= 0)
					{
						ufoSpieler.leben -= 1;
						ufoSpieler.huelle = maxHuelle;
						g.setzeLeben(ufoSpieler.leben);
						g.setzeHuelle(1);
					}
				}
			}

			// schiesst Spieler?
			if (Tastatur.isPressed(KeyEvent.VK_SPACE) && (cooldownLaser <= 0))
			{
				laserErzeugen();
			}
			laser.setzeX(ufoSpieler.gibX() + ufoSpieler.getWidth() / 2 - 6);
			laser.setzeY(ufoSpieler.gibY()-g.SIZE_Y);
			//laser.gibBild().setBounds((int)ufoSpieler.gibX() + ufoSpieler.getWidth() / 2 - 6, 0, 12, (int)ufoSpieler.gibY());
			if ((cooldownLaser >= cooldownLaserMax) && (!laser.entfernt))
			{
				laser.entferneSelbst();
				cooldownLaser =3*cooldownLaserMax;
				laser.leben = 0;
			} else if (!laser.entfernt)
			{
				cooldownLaser += deltaT * 2;
			}
			if (autoshoot && (cooldownSchuss <= 0))
			{
				schussErzeugen();
				cooldownSchuss = schussCooldown;
			}

			// ufo erzeugen
			if (cooldownUfoStufe1 <= 0)
			{
				int anzahlUfos = RNDM.rndmAnzahl();
				for (int i = 0; i < anzahlUfos; i++)
				{
					standardGegnerStufe1Erzeugen();
				}
				cooldownUfoStufe1 = 8000;
			}
			if (cooldownUfoStufe2 <= 0)
			{
				int anzahlUfos = RNDM.rndmAnzahl();
				for (int i = 0; i < anzahlUfos; i++)
				{
					standardGegnerStufe2Erzeugen();
				}
				cooldownUfoStufe2 = 23000;
			}
			if (cooldownUfoSkalierend <= 0)
			{
				int anzahlUfos = RNDM.rndmAnzahl();
				for (int i = 0; i < anzahlUfos; i++)
				{
					gegnerSkalierendErzeugen();
				}
				cooldownUfoSkalierend = 15000;
			}
			if (cooldownUfoBossStufe1 <= 0)
			{
				bossGegnerStufe1Erzeugen();
				cooldownUfoBossStufe1 = 150000;
			}

			// Spieler bewegen
			int deltaX = 0;
			int deltaY = 0;
			if (Tastatur.isPressed(KeyEvent.VK_LEFT) || Tastatur.isPressed(KeyEvent.VK_A))
			{
				deltaX -= ufoGeschwindigkeit;
			}
			if (Tastatur.isPressed(KeyEvent.VK_RIGHT) || Tastatur.isPressed(KeyEvent.VK_D))
			{
				deltaX += ufoGeschwindigkeit;
			}
			if (Tastatur.isPressed(KeyEvent.VK_UP) || Tastatur.isPressed(KeyEvent.VK_W))
			{
				deltaY -= ufoGeschwindigkeit;
			}
			if (Tastatur.isPressed(KeyEvent.VK_DOWN) || Tastatur.isPressed(KeyEvent.VK_S))
			{
				deltaY += ufoGeschwindigkeit;
			}
			ufoSpieler.setzeX(ufoSpieler.gibX() + (deltaX * deltaT) / 1000D);
			ufoSpieler.setzeY(ufoSpieler.gibY() + (deltaY * deltaT) / 1000D);

			double huelleBerechnung = maxHuelle;
			double huelleX = ufoSpieler.huelle / huelleBerechnung;
			g.setzeHuelle(huelleX);

			// Zeitunterschied
			long timeEnd = System.currentTimeMillis();
			long timeDiff = timeEnd - timeStart;

			// zeitunterschied zwischen deltaT und vergangener zeit zu gross?
			if (timeDiff >= deltaT + 7)
			{
				deltaT += 5;
				System.out.println("ich brauch " + timeDiff + "! Ich kann nicht so schnell wie " + deltaT + " :(");
			}
			if ((timeDiff <= deltaT - 7) && (deltaT > 5))
			{
				deltaT -= 5;
				System.out.println("ich brauch nur " + timeDiff + "! Ich kann schneller als " + deltaT + " :D");
			}

			// debug mode aktivieren
			if (cooldownDebugMode <= 0 && Tastatur.isPressed(KeyEvent.VK_SHIFT)
					&& Tastatur.isPressed(KeyEvent.VK_CONTROL) && Tastatur.isPressed(KeyEvent.VK_ALT)
					&& Tastatur.isPressed(KeyEvent.VK_F7))
			{
				Debugger.toggleDebugMode();
				cooldownDebugMode = 500;
				Debugger.log("DebugMode getoggled!", className, Debugger.gibDebugMain());
			}

			// pause menue / shop menue
			if (Tastatur.isPressed(KeyEvent.VK_P) && cooldownPause <= 0)
			{
				g.pause();
				cooldownPause = 500;
				Debugger.log("Pause getoggled!", className, Debugger.gibDebugMain());
			}

			// verbleibene Zeit warten
			if (timeDiff < deltaT)
			{
				try
				{
					Thread.sleep((long) deltaT - timeDiff);
				} catch (InterruptedException IE)
				{
					System.out.println("error: " + IE);
				}
			}
			Debugger.log("Spielschleife ausgefuehrt!", className, Debugger.gibDebugMain());
		}
	}
}
