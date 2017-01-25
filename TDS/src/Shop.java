
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Shop extends Bild
{
	// upgrade1
	private JLabel upgradeText1;
	private JLabel upgradeTextKosten1;
	private JLabel upgradeTextAnzahl1;
	private int buy1Anzahl = 0;
	private int buy1Kosten = 5;

	// upgrade2
	private JLabel upgradeText2;
	private JLabel upgradeTextKosten2;
	private JLabel upgradeTextAnzahl2;
	private int buy2Anzahl = 0;
	private int buy2Kosten = 5;

	// upgrade3
	private JLabel upgradeText3;
	private JLabel upgradeTextKosten3;
	private JLabel upgradeTextAnzahl3;
	private int buy3Anzahl = 0;
	private int buy3Kosten = 10;

	// upgrade4
	private JLabel upgradeText4;
	private JLabel upgradeTextKosten4;
	private JLabel upgradeTextAnzahl4;
	private int buy4Anzahl = 0;
	private int buy4Kosten = 10;

	public Shop()
	{
		super("Bilder/cyan.png");
		setBounds(0, 0, GUI.SIZE_X, GUI.SIZE_Y);
		setBackground(new Color(0, 0, 0, 0));
		setForeground(new Color(0, 0, 0, 0));
		setEnabled(true);
		setBorder(BorderFactory.createEmptyBorder());

		JLabel ShopText = JElementHelper.baueLabel("press ESC to leave the shop!", 150, 50, 400, 50);
		ShopText.setFont(new Font("SansSerif", Font.BOLD, 20));
		ShopText.setForeground(new Color(0, 0, 255));
		add(ShopText);

		// upgrade 1
		upgradeText1 = JElementHelper.baueLabel("Schuss Staerke Upgraden", 75, 210, 200, 10);
		upgradeTextKosten1 = JElementHelper.baueLabel("Kostet: " + buy1Kosten, 100, 210);
		upgradeTextAnzahl1 = JElementHelper.baueLabel("Du hast: " + buy1Anzahl + " Upgrades", 100, 220);
		add(upgradeText1);
		add(upgradeTextKosten1);
		add(upgradeTextAnzahl1);

		Bild buy1 = JElementHelper.baueBild("Bilder/schussStaerkeUpgrade.png", 100, 125, 75, 75);
		buy1.addMouseListener(new MouseClickedListener()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (Main.gibGeld() >= buy1Kosten)
				{
					Main.veraendereGeld(-buy1Kosten);
					PhysicsEngine.schussStaerke += 1;
					buy1Kosten = (int) (buy1Kosten * 1.5);
					buy1Anzahl += 1;
					if (buy1Anzahl % 5 == 0)
					{
						PhysicsEngine.schussStaerke += 5;
					}

					upgradeTextKosten1.setText("Kostet: " + buy1Kosten);
					upgradeTextAnzahl1.setText("Du hast: " + buy1Anzahl);
					repaint();
				}
			}
		});
		add(buy1);

		// upgrade 2
		upgradeText2 = JElementHelper.baueLabel("Schuss Geschwindigkeit Upgraden", 75, 335, 200, 10);
		upgradeTextKosten2 = JElementHelper.baueLabel("Kostet: " + buy2Kosten, 100, 335);
		upgradeTextAnzahl2 = JElementHelper.baueLabel("Du hast: " + buy2Anzahl + " Upgrades", 100, 345);
		add(upgradeText2);
		add(upgradeTextKosten2);
		add(upgradeTextAnzahl2);

		Bild buy2 = JElementHelper.baueBild("Bilder/schussGeschwindigkeitUpgrade.png", 100, 250, 75, 75);
		buy2.addMouseListener(new MouseClickedListener()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (Main.gibGeld() >= buy2Kosten)
				{
					Main.veraendereGeld(-buy2Kosten);
					PhysicsEngine.schussGeschwindigkeit += 5;
					buy2Kosten = (int) (buy2Kosten * 1.5);
					buy2Anzahl += 1;
					if (buy2Anzahl % 5 == 0)
					{
						PhysicsEngine.schussGeschwindigkeit += 25;
					}

					upgradeTextKosten2.setText("Kostet: " + buy2Kosten);
					upgradeTextAnzahl2.setText("Du hast: " + buy2Anzahl);
					repaint();
				}
			}
		});
		add(buy2);

		// upgrade 3
		upgradeText3 = JElementHelper.baueLabel("Feuerrate Upgraden", 75, 460, 200, 10);
		upgradeTextKosten3 = JElementHelper.baueLabel("Kostet: " + buy3Kosten, 100, 460);
		upgradeTextAnzahl3 = JElementHelper.baueLabel("Du hast: " + buy3Anzahl + " Upgrades", 100, 470);
		add(upgradeText3);
		add(upgradeTextKosten3);
		add(upgradeTextAnzahl3);

		Bild buy3 = JElementHelper.baueBild("Bilder/feuerrateUpgrade.png", 100, 375, 75, 75);
		buy3.addMouseListener(new MouseClickedListener()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (Main.gibGeld() >= buy3Kosten)
				{
					Main.veraendereGeld(-buy3Kosten);
					PhysicsEngine.schussCooldown *= 0.9;
					buy3Kosten *= 2;
					buy3Anzahl += 1;
					if (buy3Anzahl % 5 == 0)
					{
						PhysicsEngine.schussCooldown *= 0.729;
					}

					upgradeTextKosten3.setText("Kostet: " + buy3Kosten);
					upgradeTextAnzahl3.setText("Du hast: " + buy3Anzahl);
					repaint();
				}
			}
		});
		add(buy3);

		// upgrade 4
		upgradeText4 = JElementHelper.baueLabel("Geschwindigkeit Upgraden", 375, 210, 200, 10);
		upgradeTextKosten4 = JElementHelper.baueLabel("Kostet: " + buy4Kosten, 400, 210);
		upgradeTextAnzahl4 = JElementHelper.baueLabel("Du hast: " + buy4Anzahl + " Upgrades", 400, 220);
		add(upgradeText4);
		add(upgradeTextKosten4);
		add(upgradeTextAnzahl4);

		Bild buy4 = JElementHelper.baueBild("Bilder/feuerrateUpgrade.png", 400, 125, 75, 75);
		buy4.addMouseListener(new MouseClickedListener()
		{
			public void mouseClicked(MouseEvent e)
			{
				if ((Main.gibGeld() >= buy3Kosten) && (PhysicsEngine.ufoGeschwindigkeit < 300))
				{
					Main.veraendereGeld(-buy3Kosten);
					PhysicsEngine.ufoGeschwindigkeit *= 1.1;
					buy4Kosten *= 2;
					buy4Anzahl += 1;
					if (buy4Anzahl % 5 == 0)
					{
						PhysicsEngine.ufoGeschwindigkeit *= 1.29;
					}

					upgradeTextKosten4.setText("Kostet: " + buy4Kosten);
					upgradeTextAnzahl4.setText("Du hast: " + buy4Anzahl);
					repaint();
				}
			}
		});
		add(buy4);

		// ende
	}

	public int gibUpgradeAnzahl(String p)
	{
		if (p == "u1")
		{
			return buy1Anzahl;
		}
		if (p == "u2")
		{
			return buy2Anzahl;
		}
		if (p == "u3")
		{
			return buy3Anzahl;
		}
		return 0;
	}

	public void setzeUpgradeAnzahl(String p, int i)
	{
		if (p == "u1")
		{
			buy1Anzahl = i;
			for (int x = 0; x <= buy1Anzahl; x++)
			{
				System.out.println("u12");
				PhysicsEngine.schussStaerke += 1;
				buy1Kosten = (int) (buy1Kosten * 1.5);
				if (buy1Anzahl % 5 == 0)
				{
					PhysicsEngine.schussStaerke += 5;
				}

				upgradeTextKosten1.setText("Kostet: " + buy1Kosten);
				upgradeTextAnzahl1.setText("Du hast: " + buy1Anzahl);
				repaint();
			}

		}
		if (p == "u2")
		{
			buy2Anzahl = i;
			for (int x = 0; x <= buy2Anzahl; x++)
			{
				PhysicsEngine.schussGeschwindigkeit += 5;
				buy2Kosten = (int) (buy2Kosten * 1.5);
				if (buy2Anzahl % 5 == 0)
				{
					PhysicsEngine.schussGeschwindigkeit += 25;
				}

				upgradeTextKosten2.setText("Kostet: " + buy2Kosten);
				upgradeTextAnzahl2.setText("Du hast: " + buy2Anzahl);
				repaint();
			}
		}
		if (p == "u3")
		{
			buy3Anzahl = i;
			for (int x = 0; x <= buy3Anzahl; x++)
			{
				PhysicsEngine.schussCooldown *= 0.9;
				buy3Kosten *= 2;
				if (buy3Anzahl % 5 == 0)
				{
					PhysicsEngine.schussCooldown *= 0.729;
				}

				upgradeTextKosten3.setText("Kostet: " + buy3Kosten);
				upgradeTextAnzahl3.setText("Du hast: " + buy3Anzahl);
				repaint();
			}
		}
	}
}