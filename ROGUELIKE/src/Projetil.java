import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class Projetil extends Sprite {

BufferedImage charset = null;
	
	float vel = 0;
	float velX = 0;
	float velY = 0;

	float raio = 4;
	
	Object pai = null;
	
	int tempoDeVida = 2000;
	
	float dano = 25;
	double angulo = 0; 
	
	public Projetil(float x,float y,float velX,float velY,Object pai){
		this.X = x;
		this.Y = y;
		
		this.velX = velX;
		this.velY = velY;
		
		this.pai = pai;
		
		angulo = Math.atan2(velY, velX);
	}
	
	@Override
	public void SimulaSe(int diftime) {
		// TODO Auto-generated method stub
		
		tempoDeVida-=diftime;
		if(tempoDeVida<=0) {
			vivo = false;
		}

		float xold = X;
		float yold = Y;
		
		X+=velX*diftime/1000.0f;
		Y+=velY*diftime/1000.0f;
		
		
		/*
		for(int i = 0; i < CanvasMain.listaDePersonagens.size();i++){
			Personagem pers = (Personagem)CanvasMain.listaDePersonagens.get(i);
			
			if(pers!=pai){
				if(pers.testaColisao(X,Y,raio,this)){
					X = xold;
					Y = yold;
					vivo = false; 
					
					break;
				}
			}
		}
		
		for(int i = 0; i < CanvasMain.listaDeAsteroides.size();i++){
			Asteroides pers = (Asteroides)CanvasMain.listaDeAsteroides.get(i);
			 
				if(pers.testaColisao(X,Y,raio,this)){
					X = xold;
					Y = yold;
					vivo = false; 
					break;
				}
		}
		*/
		
		int bx =  (int)((X+100000)/1000);
		int by =  (int)((Y+100000)/1000);
		if(bx<0) {
			bx = 0;
		}
		if(by<0) {
			by = 0;
		}
		if(bx>199){
			bx = 199;
		}
		if(by>199) {
			by = 199;
		}
		
		LinkedList<Sprite> list = CanvasMain.colisionLists[by][bx];
		
		for (Sprite sprite : list) {
			Personagem pers = (Personagem)sprite;
			
			if(pers!=pai){
				if(pers.testaColisao(X,Y,raio,this)){
					X = xold;
					Y = yold;
					vivo = false; 
					
					break;
				}
			}
		}

	}

	@Override
	public void DesenhaSe(Graphics2D dbg,int xMundo,int yMundo) {
		// TODO Auto-generated method stub
		
		if(Constantes.telaRect.contains((int)X, (int)Y)) {
			dbg.setColor(Color.white);
			dbg.fillOval((int)(X-raio-xMundo), (int)(Y-raio-yMundo), (int)(raio*2), (int)(raio*2));
	//		dbg.setColor(Color.red);
	//		dbg.drawRect((int)X-xMundo+2, (int)Y-yMundo+2, 28, 44);
		}
	}
	
//	public boolean testaColisao(Personagem pers){
//		float dx = (pers.X+pers.raio)-(X);
//		float dy = (pers.Y+pers.raio)-(Y);
//		
//		float sr = pers.raio+raio;
//		
//		if(sr*sr>(dx*dx+dy*dy)){
//			return true;
//		}
//		
//		return false;
//	}
	
//    public boolean ColisaoRetangular(Personagem pers){
//    	float p1x1 = X-1;
//    	float p1x2 = X+1;
//    	float p2x1 = pers.X+2;
//    	float p2x2 = pers.X+30;
//    	
//    	float p1y1 = Y-1;
//    	float p1y2 = Y+1;
//    	float p2y1 = pers.Y+2;
//    	float p2y2 = pers.Y+46;
//    	
//        if((p1x1 < p2x2) && (p1x2>p2x1) && (p1y1 < p2y2) && (p1y2>p2y1)){
//            return true;
//        }
//        return false;
//    }


}
