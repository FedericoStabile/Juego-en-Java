package RunAndRun;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import base.Juego;

public class JuegoRunAndRun extends Juego {

	private ImagenEst fondo;
	private static int velocitat=20;
	private Enemigo[] enemigos;
	private int contador;
	private Jugador jugador;
	private ImagenEst gameover;
	private Punto punto;
	private Puntuacion puntuacion;
	
	public JuegoRunAndRun() {
		super("Run And Run!", 800, 600);
		
		inicializa();
		
		 start();
	}
	
	public void inicializa(){
		
		fondo = new ImagenEst("fondo.jpg",0,0,800,600);
		area.add(fondo);
		jugador= new Jugador("pj.png",20,20,60,0,0);
		enemigos = new Enemigo[100];
		contador = 0;
		punto = new Punto("punt.png",(int) ((Math.random()*600)+10),(int) ((Math.random()*400)+10),(int) ((Math.random()*40)+40),(int) (Math.random()*10+1),(int) (Math.random()*10+1));
		//añade un enemigo a la array de enemigos, el metodo esta mas abajo
		//getEnC devuelve una imagen, para que el tipo de enemigo sea al azar, metodo mas abajo
		while(contador<4){
			add(new Enemigo(getEnC(),(int) ((Math.random()*600)+50),(int) ((Math.random()*400)+50),(int) ((Math.random()*40)+40),(int) ((Math.random()*10)+10),(int) ((Math.random()*10)+10)));
		}
		puntuacion=new Puntuacion(10,20);
		
		area.add(puntuacion);
		area.add(jugador);
		area.add(punto);
		// metodo que añade todos los enemigos del array enemigos a la area
		areaAddEnemics();
		
		temporizador.add(jugador);
		temporizador.add(punto);
		temporizador.add(puntuacion);
		// metodo que añade todos los enemigos del array enemigos al temporizador
		tempAddEnemics();
	}
	
	//getEnemicColor
	private String getEnC() {
		int num=(int) (4 * Math.random());
		String color = "rojo.png";
		switch (num) {
		case 1:
			color="rojo.png";
			break;
		case 2:
			color="azul.png";
			break;
		case 3:
			color="groc.png";
			break;
		case 0:
			color="verd.png";
			break;
		}
		return color;
	}
	

	public void add(Enemigo enemigo){
	       
    	enemigos[contador] = enemigo;
    	contador++;
    }
	
	public void areaAddEnemics(){
		
        for (int i=0; i<contador;i++){
            area.add(enemigos[i]);
        }
    }
	
	public void tempAddEnemics(){
	       
        for (int i=0; i<contador;i++){
            temporizador.add(enemigos[i]);
        }
    }
	
	public static int getVelocitat() {
		return velocitat;
	}
	public static void setVelocitat(int velocitat) {
		JuegoRunAndRun.velocitat = velocitat;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP){
			if(getVelocitat()<30){
					setVelocitat(getVelocitat() + 1);
			}
			jugador.setVelocidad(0, -getVelocitat());
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			if(getVelocitat()<30){
				setVelocitat(getVelocitat() + 1);
		}
			jugador.setVelocidad(0, getVelocitat());
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			if(getVelocitat()<30){
				setVelocitat(getVelocitat() + 1);
			}
			jugador.setVelocidad(-getVelocitat(), 0);
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			if(getVelocitat()<30){
				setVelocitat(getVelocitat() + 1);
			}
			jugador.setVelocidad(getVelocitat(), 0);
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			if(jugador.getCrashed()){
				gameover.setVisible(false);
				jugador.setCrashed(false);
				velocitat=20;
				temporizador.restart();
				area.vacia();
				temporizador.vacia();
				inicializa();
				area.repaint();
			}
		}
	}

	@Override
	public void comprobaciones() {
		
		Rectangle rect = new Rectangle(jugador.getX(),jugador.getY(),jugador.getXt(),jugador.getXt());

		Rectangle[] rectangles;
		rectangles = new Rectangle[contador];
		for (int i=0; i<contador;i++){
			rectangles[i] = new Rectangle(enemigos[i].getX(),enemigos[i].getY(),enemigos[i].getXt(),enemigos[i].getXt());
			if(rect.intersects(rectangles[i])){
				if(!jugador.isInvulnerable()){
					jugador.setCrashed(true);
				}	
			}
        }
		Rectangle rectp = new Rectangle(punto.getX(),punto.getY(),punto.getXt(),punto.getXt());
		if(rect.intersects(rectp)){
			punto.setX((int) ((Math.random()*600)+10));
			punto.setX((int) ((Math.random()*400)+10));
			punto.setVx((int) ((Math.random()*10)));
			punto.setVy((int) ((Math.random()*10)));
			puntuacion.setA(puntuacion.getA()+1);
		}
		// && puntuacion.getA()>0
		if((puntuacion.getA()%2)==0){
			jugador.setInvulnerable(true);
		}else{
			jugador.setInvulnerable(false);
		}
		if(jugador.getCrashed()){
			temporizador.stop();
			gameover= new ImagenEst("gameover.png",150,200,500,200);
			area.add(gameover);	
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new JuegoRunAndRun();
	}


}

