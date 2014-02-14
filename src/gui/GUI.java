package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


/**
 * Write a description of class GUI here.
 * 
 * @author Felix Sch&uuml;tze
 * @version (a version number or a date)
 */
public class GUI extends Application
{
	FXSchnittstelle schnittstelle;
	Brett brett;
	BorderPane pane=new BorderPane();
	FlowPane unten = new FlowPane();
	int i1 = 0;
	int i2 = 0;
	RechteGUI rG;
	public boolean havefocus = false;
	
	public void start(Stage stage) throws Exception
	{
		brett = new Brett(this);
		rG = new RechteGUI(this);
		stage.setTitle("Super Schach");
		//stage.getIcons().add(new Image("bilder/turm_schwarz.png"));
		pane.setBottom(unten);
		pane.setLeft(brett);
		//pane.setRight(rG);
		stage.setScene(new Scene(pane, 800, 445));
        stage.show();
	}

	public static void main(String args[])
	{
		launch(args);
	}

	public void nachricht(String s)
	{

	}
}