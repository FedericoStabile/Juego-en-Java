package RunAndRun;

import java.awt.Font;
import java.awt.Graphics;

import base.Animado;
import base.Dibujable;



public class Puntuacion implements Dibujable,Animado {

	private int x;
	private int y;
	private boolean visible;
	private String str="";
	private int a;
	
	public Puntuacion(int x,int y){
		this.x=x;
		this.y=y;
		this.setVisible(true);
	}
	
	

	@Override
	public void dibuja(Graphics g) {
		if(isVisible()){
			Font fuente=new Font("Monospaced", Font.BOLD, 20);
			g.setFont(fuente);
			g.drawString(str, x, y);
		}
	}
	
	@Override
	public void anima() {
		String b;
		b=Integer.toString(getA());
		String str2;
		str2="Puntuacion: ";
		str=str2+b;
	}

	public boolean isVisible() {
		return visible;
	}



	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setA(int a) {
		this.a = a;
	}



	public int getA() {
		return a;
	}
}
