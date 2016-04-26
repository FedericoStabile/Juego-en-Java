package RunAndRun;

import java.awt.Graphics;

import javax.swing.ImageIcon;

// metodo funcionando

public class Jugador extends Elemento {

	private boolean invulnerable;
	
	public Jugador(String recurso,int x, int y,int tx, int vx, int vy) {
		super(recurso, x, y, tx, vx, vy);
		invulnerable=false;
	}

	@Override
	public void dibuja(Graphics g) {
		if (isCrashed()){
			imagen = new ImageIcon(this.getClass().getResource("mort.png")).getImage();
			g.drawImage(imagen,getX(),getY(),getXt(),getXt(),null);
		}else{
			if(isInvulnerable()){
				imagen = new ImageIcon(this.getClass().getResource("pj-inv.png")).getImage();
				g.drawImage(imagen,getX(),getY(),getXt(),getXt(),null);
			}else{
				imagen = new ImageIcon(this.getClass().getResource("pj.png")).getImage();
				g.drawImage(imagen,getX(),getY(),getXt(),getXt(),null);
			}
		}
	}

	public boolean isInvulnerable() {
		return invulnerable;
	}
	
	@Override
	public void anima() {
		setX(getX()+getVx());
		setY(getY()+getVy());
		if (getX()<0 || getY()<0 || getX()>750 || getY()>550){
			if(isInvulnerable()){
				if(getX()<0){
					setX(750);
				}
				if(getX()>750){
					setX(0);
				}
				if(getY()<0){
					setY(550);
				}
				if(getY()>550){
					setY(0);
				}
			}else{
				if(getX()<0){
					setX(750);
				}
				if(getX()>750){
					setX(0);
				}
				if(getY()<0){
					setY(550);
				}
				if(getY()>550){
					setY(0);
				}
				setCrashed(false);
			}
		}
	}

	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
	}

}
