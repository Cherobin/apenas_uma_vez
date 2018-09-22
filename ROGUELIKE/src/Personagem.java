import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Personagem extends Sprite {
	BufferedImage charset = null;
	
	int charw = 32;
	int charh = 48;
	
	int animTimer = 0;
	int animSpeed = 200;
	int frame = 0;
	int anim = 0;
	
	int vel = 0;
	int velX = 0;
	int velY = 0;
	
	int skin = 0;
	
	int charposx = 0;
	int charposy = 0;
	
	int raio = 10;
	
	int life = 100;
	
	public Personagem(float x,float y,BufferedImage charset,int skin){
		this.X = x;
		this.Y = y;
		this.charset = charset;
		
		this.skin = skin;
		
		int skincol = skin%4;
		int skinline = skin/4;
		
		charposx = skincol*(charw*3);
		charposy = skinline*(charh*4);
	}
	
	@Override
	public void SimulaSe(int diftime) {
		// TODO Auto-generated method stub
		animTimer+=diftime;
		frame = (animTimer/animSpeed)%3;
		
//		if(this!=GamePanel.heroi){
//			float difx = GamePanel.heroi.X - X;
//			float dify = GamePanel.heroi.Y - Y;
//			
//			double ang = Math.atan2(dify, difx);
//			
//			velX = (int)(vel*Math.cos(ang));
//			velY = (int)(vel*Math.sin(ang));
//		}
		
		float xold = X;
		float yold = Y;
		
		X+=velX*diftime/1000.0f;
		Y+=velY*diftime/1000.0f;
		
		
		
		if(Math.abs(velX)>Math.abs(velY)){
			if(velX>0){
				anim = 2;
			}else{
				anim = 1;
			}
		}else{
			if(velY>0){
				anim = 0;
			}else if(velY<0){
				anim = 3;
			}else{
				anim = 0;
			}
		}
		
		if(X<0){
			X = 0;
			velX = -velX;
		}
		if(Y<0){
			Y = 0;
			velY = -velY;
		}
		
//		if(X>CanvasMain.tilemap.Largura*16-charw){
//			X = CanvasMain.tilemap.Largura*16-charw;
//			velX = -velX;
//		}
//		if(Y>CanvasMain.tilemap.Altura*16-charh){
//			Y = CanvasMain.tilemap.Altura*16-charh;
//			velY = -velY;
//		}
		
		int bx = (int)((X+charw/2)/16);
		int by = (int)((Y+charh-8)/16);
		
//		if(CanvasMain.tilemap.mapa2[by][bx]!=0){
//			X = xold;
//			Y = yold;
//			velY = -velY;
//			velX = -velX;
//		}
		
		for(int i = 0; i < CanvasMain.listaDePersonagens.size();i++){
			Personagem pers = (Personagem)CanvasMain.listaDePersonagens.get(i);
			
			if(pers!=this){
				if(ColisaoRetangular(pers)){
					X = xold;
					Y = yold;
					velY = -velY;
					velX = -velX;
					break;
				}
			}
		}
	}

	@Override
	public void DesenhaSe(Graphics2D dbg,int xMundo,int yMundo) {
		// TODO Auto-generated method stub
		dbg.drawImage(charset,(int)X-xMundo, (int)Y-yMundo,(int)X+charw-xMundo, (int)Y+charh-yMundo,charposx+charw*frame,charposy+charh*anim,charposx+charw*frame+charw,charposy+charh*anim+charh,null);
		dbg.setColor(Color.white);
		dbg.drawOval((int)X-raio-xMundo+16, (int)Y-raio-yMundo+24, raio*2, raio*2);
		dbg.setColor(Color.red);
		dbg.drawRect((int)X-xMundo+2, (int)Y-yMundo+2, 28, 44);
	}
	
	public boolean testaColisao(Personagem pers){
		float dx = (pers.X+16)-(X+16);
		float dy = (pers.Y+24)-(Y+24);
		
		float sr = pers.raio+raio;
		
		if(sr*sr>(dx*dx+dy*dy)){
			return true;
		}
		
		return false;
	}
	
	public boolean testaColisao(float x,float y,float r){
		float dx = (x+16)-(X+16);
		float dy = (y+24)-(Y+24);
		
		float sr = r+raio;
		
		if(sr*sr>(dx*dx+dy*dy)){
			return true;
		}
		
		return false;
	}
	
    public boolean ColisaoRetangular(Personagem pers){
    	float p1x1 = X+2;
    	float p1x2 = X+30;
    	float p2x1 = pers.X+2;
    	float p2x2 = pers.X+30;
    	
    	float p1y1 = Y+2;
    	float p1y2 = Y+46;
    	float p2y1 = pers.Y+2;
    	float p2y2 = pers.Y+46;
    	
        if((p1x1 < p2x2) && (p1x2>p2x1) && (p1y1 < p2y2) && (p1y2>p2y1)){
            return true;
        }
        return false;
    }
    
    public void levaDano(int dano){
    	life-=dano;
    	if(life<=0){
    		vivo = false;
    	}
    }

}
