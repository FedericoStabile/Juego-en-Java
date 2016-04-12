package RunAndRun;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import base.Animado;
import base.Dibujable;

public abstract class Elemento implements Dibujable, Animado {

	private int x;
	private int y;
	private int vx;
	private int vy;
	private int xt;
	private boolean crashed;
	protected Image imagen;
	
	public Elemento(String recurso, int x, int y, int xt, int vx, int vy) {
		this.setX(x);
		this.setY(y);
		this.setVx(vx);
		this.setVy(vy);
		this.setXt(xt);
		this.setCrashed(false);
		imagen = new ImageIcon(this.getClass().getResource(recurso)).getImage();
	}

	abstract public void anima();

	@Override
	public abstract void dibuja(Graphics g);
	
	public void setVelocidad(int vx, int vy){
		this.setVx(vx);
		this.setVy(vy);
	}

	public boolean getCrashed(){
		return this.isCrashed();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getXt() {
		return xt;
	}

	public void setXt(int xt) {
		this.xt = xt;
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

	public boolean isCrashed() {
		return crashed;
	}

	public void setCrashed(boolean crashed) {
		this.crashed = crashed;
	}
}
