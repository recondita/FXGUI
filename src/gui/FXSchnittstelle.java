package gui;

import java.io.File;

import spiel.Schnittstelle;

/**
 * Write a description of class SwingSchnittstelle here.
 * 
 * @author Felix Sch&uuml;tze
 * @version super
 */
public class FXSchnittstelle extends Schnittstelle
{
	Brett brett;
	static FXSchnittstelle schnittstelle;

	public FXSchnittstelle(Brett brett)
	{
		this.brett = brett;
		schnittstelle = this;
	}

	public void zugGemacht()
	{
		brett.gUI.rG.zug();
	}

	public void aktualisieren()
	{
		brett.aktualisieren();
	}

	public void aktualisieren(int x, int y)
	{
		brett.aktualisieren(x, y);
	}

	public void meldungAusgeben(String s)
	{
		brett.gUI.rG.anzeige(s);
	}

	public void farbe(int x, int y, int farbe)
	{
		brett.farbe(x, y, farbe);
	}

	static public void eingabe(int x, int y)
	{
		FXSchnittstelle.schnittstelle.klick(x, y);
	}

	public void blink()
	{
		brett.blink();
	}

	public void stirb(int typ)
	{
		//brett.gUI.stirb(typ);
	}

	public void stirb(int[] geworfen)
	{
		//brett.gUI.stirb(geworfen);
	}

	public void chaterhalten(String s) throws Exception
	{
		brett.gUI.rG.anzeige(s);
	}

	public int figurMenu()
	{
		Figurenwahl fW = new Figurenwahl(brett.gUI, Player0());
		brett.gUI.rG.addTab("Auswahl", fW);
		brett.gUI.rG.setSelectedIndex(2);
		//brett.gUI.update();
		// fW.bild=Player0();
		return fW.wahl();
	}

	public void nachricht(String s)
	{
		brett.gUI.nachricht(s);
	}

	public File getFile()
	{
		return brett.gUI.rG.getFile();
	}
}
