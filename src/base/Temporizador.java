package base;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;

public class Temporizador extends Timer {
	private ArrayList<Animado> objs;
	
	public Temporizador(int delay, Juego juego){
		super(delay,juego);
		objs = new ArrayList<Animado>();
	}
	
	public void add(Animado obj){
		objs.add(obj);
	}
	
	public void vacia(){
		objs.clear();
	}
	
	@Override
	protected void fireActionPerformed(ActionEvent e){
		
		for (Animado obj : objs){
			obj.anima();
		}
		super.fireActionPerformed(e);
	}
}
