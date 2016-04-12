package RunAndRun;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

import base.Dibujable;



public class ImagenEst implements Dibujable {

	private int x;
	private int y;
	private int xt;
	private int yt;
	private Image imagen;
	private boolean visible;
	
	public ImagenEst(String recurso,int x,int y,int xt,int yt){
		this.x=x;
		this.y=y;
		this.xt=xt;
		this.yt=yt;
		this.setVisible(true);
		imagen = new ImageIcon(this.getClass().getResource(recurso)).getImage();
	}
	
	

	@Override
	public void dibuja(Graphics g) {
		if(isVisible()){
			g.drawImage(imagen,x,y,xt,yt,null);
		}
	}



	public boolean isVisible() {
		return visible;
	}



	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
