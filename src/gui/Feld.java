package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

/**
 * Write a description of class LeeresFeld here.
 * 
 * @author Felix Sch&uuml;tze 
 * @version (a version number or a date)
 */
public class Feld extends Button
{
	private FXSchnittstelle spiel;
    private int posx;
    private int posy;
    public Feld(final FXSchnittstelle spiel,final int x,final int y)
    {
        this.spiel=spiel;
        this.posx=x;
        this.posy=y;  
        setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	spiel.klick(x,y);
            }
        });
        setOnDragDetected( new EventHandler<MouseEvent>() {
        	public void handle( MouseEvent mouseEvent ) {
        		spiel.klick(x,y);
        		setCursor( Cursor.MOVE );
        	}
        });  
        
        setOnDragDone(new EventHandler<DragEvent>() {
			public void handle(DragEvent arg0)
			{
				spiel.klick(x,y);
			}
        });
         
    }
    public int gebeInhalt()
    {
        return spiel.figur(posx,posy);
    }
}
