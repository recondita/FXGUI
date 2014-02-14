package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * Das SpielBrett
 * 
 * @author Felix Sch&uuml;tze, Johann Keller, Jan Hofmeier
 * @version (super-pre-alpha Milestone 00000000.1)
 */
public class Brett extends GridPane
{
	public SwingSchnittstelle spiel;
	private Feld[][] aKnopf;
	private StackPane[][] stapel;
	private GridPane[] panel;
	public GUI gUI;
	private boolean farbe = true;

	int breite = 50;
	int hoehe = 50;

	// BufferedImage a;

	public Brett(GUI gUI)
	{
		// System.out.println("Checkpoint 3");
		this.gUI = gUI;
		spiel = new SwingSchnittstelle(this);
		spielfeldBauen(true);
		groesse();
		aktualisieren();

		// spiel.aufbauen();
		/*
		 * try { a = ImageIO.read(new File("gui/bilder/turm_schwarz.png")); }
		 * catch (Exception e) { }
		 */
		// icon=new TrayIcon();
		// System.out.println("Checkpoint 4");
	}

	private void groesse()
	{
		try
		{
			if (aKnopf[0][0].getWidth() > 0 && aKnopf[0][0].getHeight() > 0)
			{
				breite = (int) aKnopf[0][0].getWidth();
				hoehe = (int) aKnopf[0][0].getHeight();
			}
		} catch (Exception e)
		{
		}
	}

	public void spielfeldLeeren()
	{
		getChildren().clear();
	}

	public boolean farbe()
	{
		return farbe;
	}

	public void setzeFarbe()
	{
		farbe = !farbe;
	}

	public void spielfeldBauen(boolean dreh)
	{
		aKnopf = new Feld[spiel.getXMax() + 1][spiel.getYMax() + 1];
		stapel = new StackPane[spiel.getXMax() + 1][spiel.getYMax() + 1];
		panel = new GridPane[spiel.getYMax() + 1];
		if (dreh)
		{
			for (int a = spiel.getYMax(); a > -1; a--)
			{
				panel[a] = new GridPane();
				add(panel[a], 1, a);
			}
			for (int a = 0; a < spiel.getYMax() + 1; a++)
			{
				for (int b = 0; b < spiel.getXMax() + 1; b++)
				{
					bauen(a,b);
				}
			}
		} else
		{
			for (int a = 0; a < spiel.getYMax() + 1; a++)
			{
				panel[a] = new GridPane();
				add(panel[a], 1, a);
			}
			for (int a = 0; a < spiel.getYMax() + 1; a++)
			{
				for (int b = spiel.getXMax(); b > -1; b--)
				{
					bauen(a,b);
				}
			}
		}

		aktualisieren();
	}
	
	public void bauen(int a, int b)
	{
		aKnopf[b][a] = new Feld(spiel, b, a);
		
		stapel[b][a]=new StackPane();
		stapel[b][a].getChildren().add(aKnopf[b][a]);
		panel[a].add(stapel[b][a], b, a);
	}

	public void meldungAusgeben(final String s)
	{
		gUI.rG.anzeige(s);
	}

	public void aktualisieren()
	{

		for (int x = 0; x < aKnopf.length; x++)
			for (int y = 0; y < aKnopf[x].length; y++)
			{
				muster(x, y);
				aktualisierenFigur(x, y);
			}
	}

	public void klick(int x, int y)
	{
		spiel.klick(x, y);
	}

	public void aufbauen()
	{
		spiel.aufbauen();
		// aktualisieren();
		aktualisieren();
	}

	public boolean zurueck()
	{
		boolean ret = spiel.zurueck();
		aktualisieren();
		return ret;
	}

	public void farbe(int x, int y, int farbe)
	{
		if (this.farbe)
		{
			muster(x, y);
			switch (farbe)
			{
			case 3:
				aKnopf[x][y].setStyle("-fx-background-color: #088A08;");
				break;
			case 4:
				aKnopf[x][y].setStyle("-fx-background-color: #FF0000;");
				break;
			case 5:
				aKnopf[x][y].setStyle("-fx-background-color: #FFFF00;");
				break;
			}
			aktualisierenFigur(x, y);
		}
	}

	private void muster(int b, int a)
	{
		if (((a & 1) != (b & 1)))
		{
			// aKnopf[b][a].setBackground(Color.white);
			aKnopf[b][a].setStyle("-fx-background-color: #FFFFFF;");
		} else
		{
			aKnopf[b][a].setStyle("-fx-background-color: #000000;");

		}
	}

	public void aktualisieren(int x, int y)
	{
		muster(x, y);
		aktualisierenFigur(x, y);
	}

	private void aktualisierenFigur(int x, int y)
	{
		groesse();
		int bild = aKnopf[x][y].gebeInhalt(); // switch w�r besser... N�
		String farbe = "weiss";
		if (bild < 0)
		{
			farbe = "schwarz";
			bild = bild * (-1);
		}
		String s = "";
		switch (bild)
		{
		case 1:
			s = "gui/bilder/turm_" + farbe + ".png";
			break;
		case 4:
			s = "gui/bilder/springer_" + farbe + ".png";
			break;
		case 2:
			s = "gui/bilder/laeufer_" + farbe + ".png";
			break;
		case 3:
			s = "gui/bilder/dame_" + farbe + ".png";
			break;
		case 16:
			s = "gui/bilder/koenig_" + farbe + ".png";
			break;
		case 8:
			s = "gui/bilder/bauer_" + farbe + ".png";
			break;
		default:
			// aKnopf[x][y].setIcon(new ImageIcon(""));
			break;
		}
		stapel[x][y].getChildren().add(new ImageView(new Image("file:"+s)));
	}

	public void blink()
	{
		/*
		 * Blink bLINK=new Blink(gUI); if(!bLINK.fokus()) bLINK.start();
		 * 
		 * JOptionPane.showMessageDialog(null,
		 * "Sie haben eine Nachricht erhalten", "Nachricht",
		 * JOptionPane.WARNING_MESSAGE);
		 */
	}
}