package RunAndRun;

import java.awt.Graphics;

public class Enemigo extends Elemento {

	public Enemigo(String recurso, int x, int y, int xt, int vx, int vy) {
		super(recurso, x, y, xt, vx, vy);
	}

	@Override
	public void dibuja(Graphics g) {
		g.drawImage(imagen,getX(),getY(),getXt(),getXt(),null);
	}
	
	@Override
	public void anima() {
		setX(getX()+getVx());
		setY(getY()+getVy());
		if (getX()<0){
			setVx(-getVx());
		}
		if (getY()<0){
			setVy(-getVy());
		}
		if (getX()>750){
			setVx(-getVx());
		}		
		if (getY()>550){
			setVy(-getVy());
		}
		
	}
	

}
