import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Personagem extends Sprite {
	BufferedImage charset = null;
	
	// Variaveis temporarias de controle
		double heroiDist = 0;
		double heoiAng = 0;
	// vars

	static int basex = 496;
	static int basey = 496;

	int animTimer = 0;
	int animSpeed = 200;
	int frame = 0;
	int anim = 0;

	double angulo = 0;


	int raio = 496;



	Rectangle boundingbox;

	int layer0[][] = new int[31][31];
	int layer1[][] = new int[31][31];
	
	StatusArmas statusArm[][] = new StatusArmas[31][31];

	boolean FIRE = false;
	boolean DASH = false;
	boolean HEALING = false;
	
	float xAlvo = 0;
	float yAlvo = 0;
	int timertiro = 0;
	
	double xy[] = new double[2];
	
	boolean rodaia = false;
	
	int lifeMax = 1000;
	int life = 1000;
	float enegiaPorSegundo;
	float bateriaMaxima;
	float bateria;
	float gastoEnegia;
	float velocidadeMaxima;
	double vel = 0;

	public Personagem(float x, float y,int lifemax, NaveBase naveBase) {
		this.X = x;
		this.Y = y;
		


		boundingbox = new Rectangle(0, 0, 100, 100);

		if(naveBase!=null) {
			layer0 = naveBase.layer0;
	        layer1 = naveBase.layer1;
		}
		
		for(int i = 0; i < statusArm.length;i++) {
			for(int j = 0; j < statusArm[i].length;j++) {
				statusArm[i][j] = new StatusArmas();
			}
		}
		
		calculaAtributos();
		life = lifeMax;
	}
	
	public void calculaAtributos() {
		lifeMax = 0;
		enegiaPorSegundo = 0;
		bateriaMaxima = 0;
		//bateria;
		gastoEnegia = 0;
		velocidadeMaxima = 0;
		
		for (int i = 0; i < 31; i++) {
			for (int j = 0; j < 31; j++) {

				if (layer0[i][j] != 0) {
					Base b = Constantes.bases.get(layer0[i][j]-1);
					lifeMax += b.vida;
					enegiaPorSegundo += b.energia;
					gastoEnegia+= b.propulsao/2;
					velocidadeMaxima+= b.propulsao;
					bateriaMaxima+=b.bateria;
				}
			}
		}
		velocidadeMaxima-=(lifeMax/50);
		if(velocidadeMaxima<0) {
			velocidadeMaxima = 0;
		}
		
		//System.out.println(" "+velocidadeMaxima+" "+enegiaPorSegundo+" "+gastoEnegia+" "+lifeMax+" "+bateriaMaxima);
		if(life>lifeMax) {
			life = lifeMax;
		}
		vel = velocidadeMaxima/2;
		if(bateria>bateriaMaxima) {
			bateria = bateriaMaxima;
		}
	}

	@Override
	public void SimulaSe(int diftime) {
		
		bateria += (enegiaPorSegundo-gastoEnegia)*diftime/1000.0f;
		
		if(bateria>bateriaMaxima) {
			bateria = bateriaMaxima;
		}
		
		if(rodaia) {
			rodaia(diftime);
		}
		
		if(DASH) {
			double dashenergi = (gastoEnegia*10)*diftime/1000.0f;
			if(bateria>dashenergi) {
				bateria-=dashenergi;
				vel = velocidadeMaxima*4;
			}
		}else {
			if(vel > velocidadeMaxima) {
				vel = velocidadeMaxima;
			}
		}
		
		if(HEALING) {
			double lifeenergi = (lifeMax/5)*diftime/1000.0f;
			if(bateria>lifeenergi && life < lifeMax) {
				bateria-=lifeenergi;
				life += lifeenergi/2;
				if(life > lifeMax ) {
					life = lifeMax;
				}
			}
		}
		
		// TODO Auto-generated method stub
		animTimer += diftime;
		timertiro += diftime;

		frame = (animTimer / animSpeed) % 3;

		// if(this!=GamePanel.heroi){
		// float difx = GamePanel.heroi.X - X;
		// float dify = GamePanel.heroi.Y - Y;
		//
		// double ang = Math.atan2(dify, difx);
		//
		// velX = (int)(vel*Math.cos(ang));
		// velY = (int)(vel*Math.sin(ang));
		// }

		double velX = vel * Math.cos(angulo);
		double velY = vel * Math.sin(angulo);

		float xold = X;
		float yold = Y;

		X += velX * diftime / 1000.0f;
		Y += velY * diftime / 1000.0f;
		
		



		// if(Math.abs(velX)>Math.abs(velY)){
		// if(velX>0){
		// anim = 2;
		// }else{
		// anim = 1;
		// }
		// }else{
		// if(velY>0){
		// anim = 0;
		// }else if(velY<0){
		// anim = 3;
		// }else{
		// anim = 0;
		// }
		// }

		// if(X<0){
		// X = 0;
		// velX = -velX;
		// }
		// if(Y<0){
		// Y = 0;
		// velY = -velY;
		// }

		// if(X>CanvasMain.tilemap.Largura*16-charw){
		// X = CanvasMain.tilemap.Largura*16-charw;
		// velX = -velX;
		// }
		// if(Y>CanvasMain.tilemap.Altura*16-charh){
		// Y = CanvasMain.tilemap.Altura*16-charh;
		// velY = -velY;
		// }

		// if(CanvasMain.tilemap.mapa2[by][bx]!=0){
		// X = xold;
		// Y = yold;
		// velY = -velY;
		// velX = -velX;
		// }

		for (int i = 0; i < CanvasMain.listaDePersonagens.size(); i++) {
			Personagem pers = (Personagem) CanvasMain.listaDePersonagens.get(i);

			if (pers != this) {
				if (ColisaoRetangular(pers)) {
					X = xold;
					Y = yold;
					velY = -velY;
					velX = -velX;
				
					break;
				}
			}
		}
		

		for (int i = 0; i < 31; i++) {
			for (int j = 0; j < 31; j++) {

				if (layer1[i][j] != 0) {
					Arma arma = Constantes.armas.get(layer1[i][j]-1);
					
					basetToXY(j,i,xy);
					
					double dx = xAlvo - (xy[0]+X);
					double dy = yAlvo - (xy[1]+Y);

					double ang = Math.atan2(dy, dx);
					statusArm[i][j].angulo = ang;
					statusArm[i][j].timertir+=diftime;
					
					if (FIRE && (statusArm[i][j].timertir > arma.candecia_tiro) && bateria > arma.energia) {
						
						float velo = arma.velocidade_tiro;
						Projetil proj = null;
						
						if(layer1[i][j]==1) {
							proj = new Projetil((float)(xy[0]+X), (float)(xy[1]+Y), (float) (velo * Math.cos(ang)), (float) (velo * Math.sin(ang)),this);
						}
						if(layer1[i][j]==2) {
							proj = new Tiro01((float)(xy[0]+X), (float)(xy[1]+Y), (float) (velo * Math.cos(ang)), (float) (velo * Math.sin(ang)),this,0);
						}
						if(layer1[i][j]==3) {
							proj = new Tiro01((float)(xy[0]+X), (float)(xy[1]+Y), (float) (velo * Math.cos(ang)), (float) (velo * Math.sin(ang)),this,2);
						}
	
						if(layer1[i][j]==4) {
							proj = new Tiro01((float)(xy[0]+X), (float)(xy[1]+Y), (float) (velo * Math.cos(ang)), (float) (velo * Math.sin(ang)),this,1);
						}
						if(layer1[i][j]==5) {
							proj = new Tiro01((float)(xy[0]+X), (float)(xy[1]+Y), (float) (velo * Math.cos(ang)), (float) (velo * Math.sin(ang)),this,2);
						}
						if(layer1[i][j]==6) {
							proj = new Tiro01((float)(xy[0]+X), (float)(xy[1]+Y), (float) (velo * Math.cos(ang)), (float) (velo * Math.sin(ang)),this,2);
						}
						if(layer1[i][j]==7) {
							proj = new Tiro01((float)(xy[0]+X), (float)(xy[1]+Y), (float) (velo * Math.cos(ang)), (float) (velo * Math.sin(ang)),this,2);
						}
						if(layer1[i][j]==8) {
							proj = new Tiro01((float)(xy[0]+X), (float)(xy[1]+Y), (float) (velo * Math.cos(ang)), (float) (velo * Math.sin(ang)),this,2);
						}
						if(layer1[i][j]==9) {
							proj = new Tiro02((float)(xy[0]+X), (float)(xy[1]+Y), (float) (velo * Math.cos(ang)), (float) (velo * Math.sin(ang)),this);
						}

						
						if(proj!=null) {
							proj.dano = arma.dano;
							bateria-=arma.energia;
							CanvasMain.listaDeProjeteis.add(proj);
							statusArm[i][j].timertir = 0;
						}
					}
				}
			}
		}

	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int xMundo, int yMundo) {
		// TODO Auto-generated method stub
		// dbg.drawImage(charset,(int)X-xMundo, (int)Y-yMundo,(int)X+charw-xMundo,
		// (int)Y+charh-yMundo,charposx+charw*frame,charposy+charh*anim,charposx+charw*frame+charw,charposy+charh*anim+charh,null);

		
	
		
		AffineTransform trans = dbg.getTransform();

		dbg.translate((int)(X - xMundo),(int)(Y - yMundo));

		dbg.rotate(angulo);

		dbg.setColor(Color.white);
		dbg.drawOval((int) -raio, (int) -raio, raio * 2, raio * 2);

		// dbg.setColor(Color.red);
		// dbg.drawRect((int)X-xMundo-boundingbox.width/2,
		// (int)Y-yMundo-boundingbox.height/2,boundingbox.width,boundingbox.height);

		for (int i = 0; i < 31; i++) {
			for (int j = 0; j < 31; j++) {
				//dbg.setColor(Color.red);
				//dbg.drawRect((int) -basex + j * 32, (int) -basey + i * 32, 32, 32);
//				if (layer0[i][j] == 1) {
//					dbg.setColor(Color.red);
//					dbg.fillRect((int) -basex + j * 32, (int) -basey + i * 32, 32, 32);
//				}
//				if (layer0[i][j] == 2) {
//					dbg.setColor(Color.blue);
//					dbg.fillRect((int) -basex + j * 32, (int) -basey + i * 32, 32, 32);
//				}
				
				if (layer0[i][j] != 0) {
					dbg.drawImage(Constantes.bases.get(layer0[i][j]-1).imagem,(int) -basex + j * 32, (int) -basey + i * 32,32,32,null);
				}
				if (layer1[i][j] != 0) {
					AffineTransform trans2 = dbg.getTransform();
					dbg.translate((int) -basex + j * 32+16, (int) -basey + i * 32+16);
					dbg.rotate(statusArm[i][j].angulo-angulo);
					
					dbg.drawImage(Constantes.armas.get(layer1[i][j]-1).imagem,-16,-16,32,32,null);
					
					dbg.setTransform(trans2);
				}
			}
		}

		dbg.setColor(Color.green);
		dbg.drawLine(0, -basey, basex, 0);
		dbg.drawLine(0, basey, basex, 0);

		
		
		
		
		dbg.setTransform(trans);
		
		
		if(this!=Constantes.heroi) {
			dbg.setColor(Color.white);
			dbg.fillRoundRect((int)(X - xMundo-1),(int)(Y - yMundo-2), (int)(69*(lifeMax/(float)lifeMax)+3), 10, 2, 2);
		
			dbg.setColor(Color.black);
			dbg.fillRect((int)(X - xMundo),(int)(Y - yMundo-1), (int)(69*(lifeMax/(float)lifeMax)), 8);
	
			dbg.setColor(Color.red);
			dbg.fillRect((int)(X - xMundo),(int)(Y - yMundo-1), (int)(70*(life/(float)lifeMax)), 8);
		}
	}

	public boolean testaColisao(Personagem pers) {
		float dx = (pers.X + 16) - (X + 16);
		float dy = (pers.Y + 24) - (Y + 24);

		float sr = pers.raio + raio;

		if (sr * sr > (dx * dx + dy * dy)) {
			return true;
		}

		return false;
	}

	public boolean testaColisao(float x, float y, float r, Projetil proj) {
		float dx = x - X;
		float dy = y - Y;
		float sr = r + raio;

		if (sr * sr > (dx * dx + dy * dy)) {
			
			for (int i = 0; i < 31; i++) {
				for (int j = 0; j < 31; j++) {
					if (layer0[i][j] != 0) {
						
						basetToXY(j, i, xy);
						double dx2 = x - (X+xy[0]);
						double dy2 = y - (Y+xy[1]);
						double sr2 = r + 16;
						
						if (sr2 * sr2 > (dx2 * dx2 + dy2* dy2)) {
							levaDano((int)proj.dano);
							return true;
						}
					}
				}
			}
			
			
		}

		return false;
	}

	public boolean ColisaoRetangular(Personagem pers) {
		float p1x1 = X + 2;
		float p1x2 = X + 30;
		float p2x1 = pers.X + 2;
		float p2x2 = pers.X + 30;

		float p1y1 = Y + 2;
		float p1y2 = Y + 46;
		float p2y1 = pers.Y + 2;
		float p2y2 = pers.Y + 46;

		if ((p1x1 < p2x2) && (p1x2 > p2x1) && (p1y1 < p2y2) && (p1y2 > p2y1)) {
			return true;
		}
		return false;
	}
	
	

	public void levaDano(int dano) {
		life -= dano;
		if (life <= 0) {
			
			vivo = false;
			
			for (int i = 0; i < 31; i++) {
				for (int j = 0; j < 31; j++) {
					if (layer0[i][j] != 0) {
						basetToXY(j,i,xy);
						CanvasMain.listaDeParticulas.add(new ParticulaExplosao((int)(X+xy[0]), (int)(Y+xy[1]), 0, 0, 400+GamePanel.rnd.nextInt(3000), Constantes.expolsao));
						for(int ii = 0; ii < 4;ii++) {
							double angproj = GamePanel.rnd.nextDouble()*2*Math.PI;
							double velproj = 1000+GamePanel.rnd.nextInt(1500);
							Projetil proj = new Projetil((float)(xy[0]+X), (float)(xy[1]+Y), (float) (velproj * Math.cos(angproj)), (float) (velproj * Math.sin(angproj)),this);
							proj.tempoDeVida = 800+GamePanel.rnd.nextInt(400);
							CanvasMain.listaDeProjeteis.add(proj);
							}
					}
				}
			}
		}
	}

	public void basetToXY(int j, int i, double ret[]) {
		int pi = i - 15;
		int pj = j - 15;

		int ix = pi * 32;
		int jx = pj * 32;

		double x = jx * Math.cos(angulo) - ix * Math.sin(angulo);
		double y = jx * Math.sin(angulo) + ix * Math.cos(angulo);
		ret[0] = x;
		ret[1] = y;
	}
	
	public void rodaia(int diftime) {
		if(angulo>Math.PI*2) {
			angulo-=(Math.PI*2);
		}
		if(angulo < 0 ) {
			angulo+=(Math.PI*2);
		}
		//System.out.println("angulo "+angulo);
		
		float dx = Constantes.heroi.X - X;
		float dy = Constantes.heroi.Y - Y;
		
		
		
		if((dx*dx+dy*dy) < 10000000 && Constantes.heroi.vivo) {
			vel = velocidadeMaxima;
			double ang = Math.atan2(dy, dx);
			if(ang<0) {
				ang = (Math.PI*2)+ang;
			}
			
			//System.out.println("angulo "+angulo+" "+ang);
			//System.out.println(" RODA IA "+dx+" "+dy+" "+ang);
			
			if(Math.abs(angulo-ang)>0.2) {
				if(angulo-ang < 0) {
					angulo += (Math.PI*0.5)*diftime/1000.0f;
				}else {
					angulo -= (Math.PI*0.5)*diftime/1000.0f;
				}
			}else {
				if((dx*dx+dy*dy) < 8000000) {
					FIRE = true;
					xAlvo = Constantes.heroi.X - 500 + GamePanel.rnd.nextInt(1000);
					yAlvo = Constantes.heroi.Y - 500 + GamePanel.rnd.nextInt(1000);
				}
			}
		}else {
			FIRE = false;
		}
	}

}
