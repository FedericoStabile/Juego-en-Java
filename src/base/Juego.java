package base;

import java.awt.event.*;

public abstract class Juego implements ActionListener, KeyListener {
	
	protected AreaDibujo area;
	protected Temporizador temporizador;
	
	public Juego(String titulo, int ancho, int alto){
		area = new AreaDibujo(titulo, ancho, alto);
		area.addKeyListener(this);
		temporizador = new Temporizador(50,this);
	}
	
	public void start(){
		temporizador.start();
	}
	
	public abstract void comprobaciones();
	
	@Override
	public void actionPerformed(ActionEvent e){
		comprobaciones();
		area.repaint();
	}
	
	@Override
	public void keyTyped(KeyEvent e){	
	}
	
	@Override
	public void keyPressed(KeyEvent e){	
	}
	
	@Override
	public void keyReleased(KeyEvent e){	
	}
	
	
}
