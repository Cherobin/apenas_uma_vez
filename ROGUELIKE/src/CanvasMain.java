import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


public class CanvasMain extends MyCanvas {
	
 
	public static BufferedImage fumaca = null;
	
	BufferedImage imagefundo;

	boolean LEFT, RIGHT,UP,DOWN,FIRE,DASH,HEALING;


	int MouseX,MouseY;

	float x,y;
	float x2,y2;
	int tempomovimento = 0;
	int tempomovimento2 = 0;



	double posx = 0;
	double posy = 0;

	double rotx = 0;
	public Random rnd;
	Double rotate_ponteiro = 0.0;

	public static ArrayList<Sprite> listaDePersonagens = new ArrayList<Sprite>();

	public static ArrayList<Sprite> listaDeProjeteis = new ArrayList<Sprite>();

	public static ArrayList<Sprite> listaDeParticulas = new ArrayList<Sprite>();

	public static ArrayList<Sprite> listaDeAsteroides = new ArrayList<Sprite>();
	
	public static ArrayList<Sprite> listaDeItens = new ArrayList<Sprite>();
	
	public static ArrayList<Bilboard> listaDeBilboards = new ArrayList<Bilboard>();
	
	public static GerenciadorDeEventos gerenciadorEventos = new GerenciadorDeEventos();
	
	int timerfps = 0;
	int somacor = 0;
	int timertiro = 0;
	
	int MapX = 0;
	int MapY = 0;
	
	double zoom;
	double newzoom;
	 
	
	public Color lifebarcolor = new Color(0, 155, 0,155) ;
	
	
	public CanvasMain() {
		//imgFundo = GamePanel.instance.carregaImagem("nebulosa.jpg");
		//imgCharset = GamePanel.instance.carregaImagem("rmxp004tw4.png");
		//tileset = GamePanel.instance.carregaImagem("Bridge.png");
		//fumaca = GamePanel.instance.carregaImagem("fumaca.png");
		
		DASH = false;
		HEALING = false;
		
		rnd = new Random();
		x = 0;
		y = 50; 
		
		x2 = 0;
		y2 = 200;

		Constantes.heroi = new Personagem(100, 100,5000, Constantes.navePLayer);


		listaDePersonagens.add(Constantes.heroi);
		
		
		Random maprnd = new Random(Constantes.randomSeed); 
		
		for(int i = 0; i < 1200;i++){
			int posx = 0;
			int posy = 0;


			posx = maprnd.nextInt(200000)-100000;
			posy = maprnd.nextInt(200000)-100000;
			
			double dist = Math.sqrt(posx*posx + posy*posy);
				
			Personagem pers = null;
			
			if(dist < 10000) {
				pers = new Personagem(posx, posy,1,Constantes.navesBase.get(0));
			}else if(dist < 60000) {
				if(maprnd.nextInt(4)==0) {
					pers = new Personagem(posx, posy,1,Constantes.navesBase.get(maprnd.nextInt(10)+11));
				}else {
					pers = new Personagem(posx, posy,1,Constantes.navesBase.get(maprnd.nextInt(4)+24));
				}
			}else {
				if(maprnd.nextInt(4)==0) {
					pers = new Personagem(posx, posy,1,Constantes.navesBase.get(maprnd.nextInt(10)+11));
				}else {
					pers = new Personagem(posx, posy,1,Constantes.navesBase.get(maprnd.nextInt(3)+28));
				}
				//pers = new Personagem(posx, posy,1,Constantes.navesBase.get(rnd.nextInt(Constantes.navesBase.size()-1)));
			}
			

			pers.angulo = (Math.PI*2)*GamePanel.rnd.nextDouble();
			pers.rodaia = true;

			
			listaDePersonagens.add(pers);
		}
		
		for(int i = 0; i < 20;i++){
			int posx = 0;
			int posy = 0;

			posx = maprnd.nextInt(20000)-10000;
			posy = maprnd.nextInt(20000)-10000;
			
			double dist = posx*posx+posy*posy;
				
			Personagem pers = null;

			pers = new Personagem(posx, posy,1,Constantes.navesBase.get(maprnd.nextInt(10)));

			pers.angulo = (Math.PI*2)*GamePanel.rnd.nextDouble();
			pers.rodaia = true;

			
			listaDePersonagens.add(pers);
		}
		
		Personagem pers1 = new Personagem(0, -120000,1,Constantes.navesBase.get(21));
		pers1.rodaia = true;
		listaDePersonagens.add(pers1);
		pers1 = new Personagem(0,120000,1,Constantes.navesBase.get(21));
		pers1.rodaia = true;
		listaDePersonagens.add(pers1);
		pers1 = new Personagem(-120000,0,1,Constantes.navesBase.get(21));
		pers1.rodaia = true;
		listaDePersonagens.add(pers1);
		pers1 = new Personagem(120000,0,1,Constantes.navesBase.get(21));
		pers1.rodaia = true;
		listaDePersonagens.add(pers1);
		
		for(int i = 0; i < 5000;i++){
			int posx = 0;
			int posy = 0;
			
			int bx = 0;
			int by = 0;
			
			boolean colidiu = false;
				
			do{
				
					
				colidiu = false;
				posx = GamePanel.rnd.nextInt(240000)-120000;
				posy = GamePanel.rnd.nextInt(240000)-120000;
				
				bx = (int)((posx+16)/16);
				by = (int)((posy+40)/16);
				
			 
				
			}while(colidiu);
 
			Asteroides pers = new Asteroides(posx, posy,500,Constantes.imgBigAsteroide.get(rnd.nextInt(Constantes.imgBigAsteroide.size()-1)), true);
			pers.vel = 50+GamePanel.rnd.nextInt(50);
			pers.angulo = (Math.PI*2)*GamePanel.rnd.nextDouble();
			pers.rodaia = true;
			//pers.vel = (int)Math.sqrt(pers.velX*pers.velX + pers.velY*pers.velY);
			
			listaDeAsteroides.add(pers);
		}
		
		zoom = 0.5;
		newzoom = 0.5;
		
//		gerenciadorEventos.carregaEventos(this.getClass().getResourceAsStream("ecentos.csv"));
		
		
		listaDeBilboards.add(new Bilboard(0, 0, 80,Constantes.bilboards[0]));
		listaDeBilboards.add(new Bilboard(800,800, 80,Constantes.bilboards[4]));
		listaDeBilboards.add(new Bilboard(-1000,400, 80,Constantes.bilboards[5]));
		listaDeBilboards.add(new Bilboard(400,400, 80,Constantes.bilboards[6]));
		listaDeBilboards.add(new Bilboard(-400,-400, 80,Constantes.bilboards[3]));
		listaDeBilboards.add(new Bilboard(400, 400, 40,Constantes.bilboards[1]));
		listaDeBilboards.add(new Bilboard(-100, 0, 20,Constantes.bilboards[2]));
		
		
		
		//GamePanel.telaAtiva = new CanvasCostrucao(this,Color.blue);
	}
	@Override
	public void SimulaSe(int diftime) {
		timertiro+=diftime;
		zoom = newzoom;
 
		  
		rotate_ponteiro+= 1* diftime / 1000.0f;
	 
		if(rotate_ponteiro>6.05) {
			rotate_ponteiro = 0d;
		} 
			
		if(LEFT){
			Constantes.heroi.angulo-=Math.PI*diftime/1000.0f;

		}else if(RIGHT){
			Constantes.heroi.angulo+=Math.PI*diftime/1000.0f;
		}
		
		if(UP){
			Constantes.heroi.vel += Constantes.heroi.velocidadeMaxima*diftime/1000.0f;
			
			System.out.println("Constantes.heroi.vel "+Constantes.heroi.vel);
			
			if(Constantes.heroi.vel>=Constantes.heroi.velocidadeMaxima) {
				Constantes.heroi.vel=Constantes.heroi.velocidadeMaxima;
			}
		}else if(DOWN){
			Constantes.heroi.vel-=Constantes.heroi.velocidadeMaxima*diftime/1000.0f;
			
			//System.out.println("DDD Constantes.heroi.vel "+Constantes.heroi.vel);
			
			if(Constantes.heroi.vel<=0) {
				Constantes.heroi.vel=0;
			}
		}
		

		Constantes.heroi.FIRE = FIRE;
		Constantes.heroi.DASH = DASH;
		Constantes.heroi.HEALING = HEALING;

		
		Constantes.heroi.xAlvo = (float)(MouseX/zoom+MapX);
		Constantes.heroi.yAlvo = (float)(MouseY/zoom+MapY);


//		heroi.SimulaSe((int)diftime);
		
		double distsimula = 32000*32000;
		for(int i = 0; i < listaDePersonagens.size();i++){
			Personagem sp = (Personagem)listaDePersonagens.get(i);
			
			double dx =  sp.X - Constantes.heroi.X;
			double dy =  sp.Y - Constantes.heroi.Y;
			double iang = Math.atan2(dy, dx);
			double idist = dx*dx+dy*dy;
			
			sp.heoiAng = iang;
			sp.heroiDist = idist;
			
			if(idist < distsimula) {
				sp.SimulaSe((int)diftime);
				if(!sp.vivo){
					listaDePersonagens.remove(i);
					i--;
				}else {
					dx =  sp.X - Constantes.heroi.X;
					dy =  sp.Y - Constantes.heroi.Y;
					iang = Math.atan2(dy, dx);
					idist = dx*dx+dy*dy;
					
					sp.heoiAng = iang;
					sp.heroiDist = idist;
				}
			}
		}
		
		
		for(int i = 0; i < listaDeProjeteis.size();i++){
			Sprite sp = listaDeProjeteis.get(i);
			sp.SimulaSe((int)diftime);
			if(!sp.vivo){
				listaDeProjeteis.remove(i);
				i--;
			}
		}
		
		for(int i = 0; i < listaDeParticulas.size();i++){
			Sprite sp = listaDeParticulas.get(i);
			sp.SimulaSe((int)diftime);
			if(!sp.vivo){
				listaDeParticulas.remove(i);
				i--;
			}
		}	
		
		for(int i = 0; i < listaDeAsteroides.size();i++){
			Sprite sp = listaDeAsteroides.get(i);
			sp.SimulaSe((int)diftime);
			if(!sp.vivo){
				listaDeAsteroides.remove(i);
				i--;
			}
		}	
		
		for(int i = 0; i < listaDeItens.size();i++){
			Sprite sp = listaDeItens.get(i);
			sp.SimulaSe((int)diftime);
			if(!sp.vivo){
				listaDeItens.remove(i);
				i--;
			}
		}
		
		
		
//		gerenciadorEventos.testaEventos(heroi);
		
		MapX = (int)(Constantes.heroi.X-(Constantes.telaW/zoom)/2);
		MapY = (int)(Constantes.heroi.Y-(Constantes.telaH/zoom)/2);
		
		Constantes.telaRect.setBounds(MapX, MapY, (int)(Constantes.telaW/zoom), (int)(Constantes.telaH/zoom));
	}

	@Override
	public void DesenhaSe(Graphics2D dbg) {
		// clear the background
		dbg.setColor(Color.BLACK);
		dbg.fillRect (0, 0, Constantes.telaW, Constantes.telaH);
		

		
		//dbg.drawImage(imgFundo,(int)(-500+(heroi.X/100)),(int)(-500+(heroi.Y/100)),null);
		
		for(int i = 0; i < listaDeBilboards.size();i++){
			AffineTransform trans = dbg.getTransform();
			Bilboard bilb = listaDeBilboards.get(i);
			
			dbg.translate(bilb.x - Constantes.heroi.X/(double)bilb.z , bilb.y - Constantes.heroi.Y/(double)bilb.z );
			

			dbg.drawImage(bilb.img, - bilb.img.getWidth()/2, - bilb.img.getHeight()/2, null);
			
			dbg.setTransform(trans);
			
		}
		
		AffineTransform trans = dbg.getTransform();	
		
		dbg.scale(zoom, zoom);

//		dbg.drawImage(imgFundo, null,0,0);

		for(int i = 0; i < 40; i++) {
			for(int j = 0; j < 40; j++) {
				Rectangle bquadrante = new Rectangle((j-20)*8000, (i-20)*8000, 8000, 8000);
				
				if(Constantes.telaRect.intersects(bquadrante)) {
					if(Constantes.quadrantes[i][j]!=null) {
						//System.out.println(Constantes.telaRect+" "+bquadrante);
						Constantes.quadrantes[i][j].DesenhaSe(dbg, MapX, MapY);
					}else {
						Constantes.quadrantes[i][j] = new Quadrante(j-20, i-20);
						Constantes.quadrantes[i][j].DesenhaSe(dbg, MapX, MapY);
					}
				}
			}
		}
		
		for(int i = 0; i < listaDeItens.size();i++){
			listaDeItens.get(i).DesenhaSe(dbg,MapX,MapY);
		}
		
		double distview = 20000*20000;
		int count = 0;
		for(int i = 0; i < listaDePersonagens.size();i++){
			Personagem pers = (Personagem)listaDePersonagens.get(i);
			
			if(pers.heroiDist < distview) {
				pers.DesenhaSe(dbg,MapX,MapY);
				count++;
			}
		}
		//System.out.println("count "+count +" "+Constantes.telaRect );
		
		for(int i = 0; i < listaDeProjeteis.size();i++){
			listaDeProjeteis.get(i).DesenhaSe(dbg,MapX,MapY);
		}
		
		for(int i = 0; i < listaDeParticulas.size();i++){
			listaDeParticulas.get(i).DesenhaSe(dbg,MapX,MapY);
		}	
		
		for(int i = 0; i < listaDeAsteroides.size();i++){
			Asteroides asteroid = (Asteroides)listaDeAsteroides.get(i);
			
			if(Constantes.telaRect.contains(asteroid.X, asteroid.Y)) {
				asteroid.DesenhaSe(dbg,MapX,MapY);
			}
		}	
		
	
		
//		heroi.DesenhaSe(dbg);
		
		//gerenciadorEventos.desenhase(dbg, MapX,MapY);
		
		dbg.setTransform(trans);
		
		//Font f = dbg.getFont();
		
		//dbg.setFont(fonte);
		dbg.setFont(Constantes.font.deriveFont(Font.PLAIN, 20));
		dbg.setColor(Color.YELLOW);	
		dbg.drawString("FPS: "+GamePanel.FPS,  Constantes.telaW-80, 12);
		
		//dbg.setFont(fonteMini);	
	//	dbg.drawString("Velocidade", 10, Constantes.telaH-26);
		//dbg.drawString(""+(int)(Constantes.heroi.vel), 10, Constantes.telaH-15);
		
		dbg.setFont(Constantes.font.deriveFont(Font.PLAIN, 14));	 
		 dbg.setColor(Color.YELLOW);
		 dbg.drawString(""+(int)(Constantes.gold), 60, 32);
		
		 
		dbg.setColor(Color.black);
		dbg.fillRect(51, 41, (int)(270*(Constantes.heroi.lifeMax/(float)Constantes.heroi.lifeMax)), 21);
			
		dbg.setColor(lifebarcolor);
	
		
		dbg.fillRect(51, 41, (int)(270*(Constantes.heroi.life/(float)Constantes.heroi.lifeMax)), 21);

		dbg.setColor(Color.BLUE);
		dbg.fillRect(51, 65, (int)(270*(Constantes.heroi.bateria/Constantes.heroi.bateriaMaxima)), 10);
	
		//dbg.drawString("Life", 105, Constantes.telaH-28);
		
		//dbg.setFont(f);
	 
		if(Constantes.heroi.life<=0) { 
			dbg.setFont(Constantes.font.deriveFont(Font.PLAIN, 70));	 
			 dbg.setColor(Color.WHITE);
			 dbg.drawString("GAME OVER!", Constantes.telaW/2 - 120, Constantes.telaH/2);
			 dbg.setFont(Constantes.font.deriveFont(Font.PLAIN, 69));	 
			 dbg.setColor(Color.RED);
			 dbg.drawString("GAME OVER!", Constantes.telaW/2 - 121, Constantes.telaH/2);
		}
		
		
		
		dbg.drawImage(Constantes.hudImage, 0, 0, null);
		
		dbg.setColor(new Color(34,139,34,150));
		
		dbg.fillOval(Constantes.telaW-150-2, Constantes.telaH-150-2, 150, 150);
		
 
		dbg.drawImage(Constantes.radar, Constantes.telaW-150-3, Constantes.telaH-150-3, null);
		
		Composite com = dbg.getComposite();
		AffineTransform trans2 = dbg.getTransform();

		dbg.translate((int) Constantes.telaW-150-2+ Constantes.radar_ponteiro.getWidth()/2,Constantes.telaH-150-2+Constantes.radar_ponteiro.getHeight()/2);
		float prop = (float) (1.0f - (rotate_ponteiro/(float)6.05));
		
		 Composite cp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, prop);
		 dbg.setComposite(cp);
		dbg.rotate(rotate_ponteiro);
  
		dbg.drawImage(Constantes.radar_ponteiro, - Constantes.radar_ponteiro.getWidth()/2,- Constantes.radar_ponteiro.getHeight()/2, null);
 
		 
		dbg.setTransform(trans2);
		dbg.setComposite(com);
		 
		
		//dbg.setColor(new Color(255,255,0,200));
		//dbg.fillOval(Constantes.telaW-85-2, Constantes.telaH-85-2, 20, 20);
		//dbg.drawOval(Constantes.telaW-95-2, Constantes.telaH-95-2, 50, 50);
		
		
		
		int centerX = Constantes.telaW-75;
		int centerY = Constantes.telaH-75;
		
		dbg.setColor(Color.WHITE);
		dbg.fillRect(centerX-1, centerY-1, 3, 3);
		
		double radardistprecision = 500;
		double radardist = (75*radardistprecision)*(75*radardistprecision);
		
		for(int i = 0; i < listaDePersonagens.size();i++){
			Personagem p = (Personagem)listaDePersonagens.get(i);
			if(p!=Constantes.heroi) {

				double iang = p.heoiAng;
				double distq = p.heroiDist;


				
				if(distq < radardist ) {
					double idist = Math.sqrt(p.heroiDist);
					double rasao = idist/radardistprecision;
					
					dbg.setColor(new Color(0,255,0,255));
					dbg.fillRect((int)(centerX-1+Math.cos(iang)*rasao), (int)(centerY-1+Math.sin(iang)*rasao), 2, 2);
				}
			}
		}
	}
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A){
			LEFT = true;
		}
		if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D){
			RIGHT = true;
		}
		if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W){
			UP = true;
		}
		if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S){
			DOWN = true;
		}	
		if(keyCode == KeyEvent.VK_SPACE){
			DASH = true;
		}
		if(keyCode == KeyEvent.VK_ALT){
			HEALING = true;
		}
		if(keyCode == KeyEvent.VK_H){
			GamePanel.telaAtiva = new CanvasCostrucao(this,Color.blue);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A){
			LEFT = false;
		}
		if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D){
			RIGHT = false;
		}
		if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W){
			UP = false;
		}
		if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S){
			DOWN = false;
		}
		if(keyCode == KeyEvent.VK_SPACE){
			DASH = false;
		}
		if(keyCode == KeyEvent.VK_ALT){
			HEALING = false;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		MouseX = e.getX();
		MouseY = e.getY();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		MouseX = e.getX();
		MouseY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		FIRE = false;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		FIRE = true;
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
//		double oldpox = MouseX/fatorDeEscala;
//		double oldpoy = MouseY/fatorDeEscala;
		
		if(arg0.getWheelRotation()>0){
			newzoom = zoom*1.1;
		}else{
			newzoom = zoom*0.9;
		}
		
		if(newzoom>2) {
			newzoom = 2;
		}
		
		if(newzoom<0.05) {
			newzoom = 0.05;
		}
		
//		double mouseposnewx = MouseX/fatorDeEscala;
//		double mouseposnewy = MouseY/fatorDeEscala;
		
//		posx += -(oldpox-mouseposnewx);
//		posy += -(oldpoy-mouseposnewy);
	}

}
