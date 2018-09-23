import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random; 


//35 por 55
public class Asteroides extends Personagem {
	
	BufferedImage charset = null;
	float dano = 15;
	Random rnd = new Random();
	Double rotate = 0.0;
	boolean recreate = false;
	float timeLife = 25;
	
	public Asteroides(float x, float y, int lifemax, BufferedImage charset, boolean recreate) {
		super(x, y, lifemax, null);
		// TODO Auto-generated constructor stub
		this.charset = charset;
		vel = rnd.nextInt(200);
		angulo = rnd.nextDouble();
		this.recreate = recreate;
		life = lifemax;
		raio = charset.getWidth()/2;
	}
 
	@Override
	public void SimulaSe(int diftime) { 
		//super.SimulaSe(diftime);

		if(!recreate) {
			timeLife-=1* diftime / 1000.0f; 
		}
		
		rotate+= 0.5* diftime / 1000.0f;
		
		if(rotate>360) {
			rotate = 0.0;
		}
		double velX = vel * Math.cos(angulo);
		double velY = vel * Math.sin(angulo);
 

		X += velX * diftime / 1000.0f;
		Y += velY * diftime / 1000.0f;
		
		if (ColisaoRetangular(Constantes.heroi)) {
			levaDano((int)dano);
			Constantes.heroi.life-=dano;
		}
	 
		if(timeLife < 0) {
			vivo = false;
		}
		
		/*
		for (int i = 0; i < CanvasMain.listaDePersonagens.size(); i++) {
			Personagem pers = (Personagem) CanvasMain.listaDePersonagens.get(i);

			if (pers != this) {
				if (ColisaoRetangular(pers)) {
					if(!recreate) {
						pers.levaDano((int)dano);
					}
					break;
				}
			}
		}
		
	*/
	}
	@Override
	public void levaDano(int dano) { 
		//super.levaDano(dano);
		life-= dano;
		
		if(life < 0) {
			vivo = false;
			if(recreate) {
				if(rnd.nextInt(100)>80) {		
					Item item = new Item(Constantes.goldImage,X+charset.getWidth()/2, Y+charset.getHeight()/2,rnd.nextInt(500), 0 ,100, 7, 38, 55, 30, true); 
					CanvasMain.listaDeItens.add(item);
				}else {
					Item item = new Item(Constantes.lifeImage,X+charset.getWidth()/2, Y+charset.getHeight()/2,0, rnd.nextInt(500),100, 1, 44, 44, 50, false);
					CanvasMain.listaDeItens.add(item);
				}
				for(int i = 0; i < 20;i++){
					Asteroides pers = new Asteroides(X+charset.getWidth()/2, Y+charset.getHeight()/2,10,Constantes.imgSmallAsteroide.get(rnd.nextInt(Constantes.imgSmallAsteroide.size()-1)),false);
					pers.vel = 50+GamePanel.rnd.nextInt(50);
					pers.angulo = (Math.PI*2)*GamePanel.rnd.nextDouble();
					CanvasMain.listaDeAsteroides.add(pers);
				}
				
			}
		} 
	}
	
	@Override
	public boolean testaColisao(float x, float y, float r, Projetil proj) {
		float dx = x - X;
		float dy = y - Y;
		float sr = r + raio;

		if (sr * sr > (dx * dx + dy * dy)) {
			levaDano((int)proj.dano);
		}

		return false;
	}
	
	@Override
	public void DesenhaSe(Graphics2D dbg, int xMundo, int yMundo) {
		
		 
		AffineTransform trans = dbg.getTransform();

		dbg.translate((int)(X - xMundo),(int)(Y - yMundo));

		dbg.rotate(rotate);
  
		dbg.drawImage(charset, - charset.getWidth()/2,- charset.getHeight()/2, null);
 
		 
		dbg.setTransform(trans);
	}
 
}
