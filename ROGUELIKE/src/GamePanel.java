import java.awt.*;
import java.awt.event.*;
import java.awt.font.ImageGraphicAttribute;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.image.*;

import javax.imageio.ImageIO;


public class GamePanel extends JPanel implements Runnable{

public static GamePanel instance = null;

private Thread animator;
private boolean running = false;
private boolean gameOver = false; 

private Graphics2D dbg;

Canvas gui = null;
public BufferStrategy strategy = null;


public static int FPS,SFPS;
int fpscount;

public static MyCanvas telaAtiva = null;

public static Random rnd = new Random();





public GamePanel()
{
	instance = this;

	setBackground(Color.white);
	setPreferredSize( new Dimension(Constantes.telaW, Constantes.telaH));

	// create game components
	setFocusable(true);

	requestFocus(); // JPanel now receives key events
	
	gui = new Canvas();
	gui.setSize(Constantes.telaW, Constantes.telaH);
	setLayout(new BorderLayout());
	add(gui, BorderLayout.CENTER);
	
	// Adiciona um Key Listner
	gui.addKeyListener( new KeyAdapter() {
		public void keyPressed(KeyEvent e)
			{ 
				if(telaAtiva!=null){
					telaAtiva.keyPressed(e);
				}
			}
		@Override
			public void keyReleased(KeyEvent e ) {
				if(telaAtiva!=null){
					telaAtiva.keyReleased(e);
				}
			}
	});
	
	gui.addMouseMotionListener(new MouseMotionListener() {
		
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			if(telaAtiva!=null){
				telaAtiva.mouseMoved(e);
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			if(telaAtiva!=null){
				telaAtiva.mouseDragged(e);
			}
		}
	});
	
	
	
	gui.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			if(telaAtiva!=null){
				telaAtiva.mouseReleased(e);
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if(telaAtiva!=null){
				telaAtiva.mousePressed(e);
			}
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			if(telaAtiva!=null){
				telaAtiva.mouseExited(e);
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if(telaAtiva!=null){
				telaAtiva.mouseEntered(e);
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(telaAtiva!=null){
				telaAtiva.mouseClicked(e);
			}
		}
	});
	
	gui.addMouseWheelListener(new MouseWheelListener() {
		
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			// TODO Auto-generated method stub
			if(telaAtiva!=null){
				telaAtiva.mouseWheelMoved(e);
			}
		}
	});
	
	
	carregaAssetsEntreOutrasCoisas();
	
	telaAtiva = new CanvasMain();
	
	Constantes.carregaImagens();
  
} // end of GamePanel()

private void carregaAssetsEntreOutrasCoisas() {
	//Constantes.armas
	
	try {
	 
		InputStream In = getClass().getResourceAsStream("armas.csv");
	
			String line = "";
		
			BufferedReader bfr = new BufferedReader(new InputStreamReader(In));
		
	 
			while((line = bfr.readLine())!=null){
				if(line.charAt(0)!='#'){
					String strs[] = line.split(";");
					
						Arma arma = new Arma();
					
						arma.id = Integer.parseInt(strs[0]);
						arma.nome = strs[1];
						arma.imagem = carregaImagem(strs[2]);
						arma.dano = Float.parseFloat(strs[3]);
						arma.candecia_tiro = Float.parseFloat(strs[4]);
						arma.velocidade_tiro = Float.parseFloat(strs[5]);
						arma.tiro_automatico = Boolean.parseBoolean(strs[6]);
						arma.tipo_tiro = Integer.parseInt(strs[7]);
						arma.custo = Float.parseFloat(strs[8]);
						arma.energia = Float.parseFloat(strs[9]);
						Constantes.armas.add(arma);
				  
				}
			}
		 
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
	    System.out.println(e.getMessage()+ "  abreaA!!!");
		e.printStackTrace();
	}
	
	
	try {
		 
		InputStream In = getClass().getResourceAsStream("bases.csv");
	
			String line = "";
		
			BufferedReader bfr = new BufferedReader(new InputStreamReader(In));
		
	 
			while((line = bfr.readLine())!=null){
				if(line.charAt(0)!='#'){
					String strs[] = line.split(";");
					
						Base base = new Base();
					
						base.id = Integer.parseInt(strs[0]);
						base.nome = strs[1];
						base.imagem = carregaImagem(strs[2]);
						base.tipo_base = Integer.parseInt(strs[3]);
						base.custo = Float.parseFloat(strs[4]);
						base.poder = Integer.parseInt(strs[5]);
						 
						Constantes.bases.add(base);
				  
				}
			}
		 
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
	    System.out.println(e.getMessage()+ "  abreaA!!!");
		e.printStackTrace();
	}
	
	
	
	carregaNave("nave_1.csv");
	carregaNave("nave_2.csv");
	carregaNave("nave_3.csv");
	carregaNave("nave_4.csv");
	
 
	Constantes.imgBigAsteroide.add(carregaImagem("asteroide_1.png"));
	Constantes.imgBigAsteroide.add(carregaImagem("asteroide_2.png"));
	Constantes.imgBigAsteroide.add(carregaImagem("asteroide_3.png"));
	
	
	Constantes.imgSmallAsteroide.add(carregaImagem("asteroide_small_1.png"));
	Constantes.imgSmallAsteroide.add(carregaImagem("asteroide_small_2.png"));
	Constantes.imgSmallAsteroide.add(carregaImagem("asteroide_small_3.png"));
	Constantes.imgSmallAsteroide.add(carregaImagem("asteroide_small_4.png"));
	Constantes.imgSmallAsteroide.add(carregaImagem("asteroide_small_5.png"));
	Constantes.imgSmallAsteroide.add(carregaImagem("asteroide_small_6.png"));
	Constantes.imgSmallAsteroide.add(carregaImagem("asteroide_micro_1.png"));
	Constantes.imgSmallAsteroide.add(carregaImagem("asteroide_micro_2.png"));
	Constantes.imgSmallAsteroide.add(carregaImagem("asteroide_micro_3.png"));
	
	
}
public void carregaNave(String file) {
	try {
		 
		InputStream In = getClass().getResourceAsStream(file);
	
			String line = "";
		
			BufferedReader bfr = new BufferedReader(new InputStreamReader(In));
			NaveBase nave = new NaveBase();
			int isBase = -1;
			int e =0;
			while((line = bfr.readLine())!=null){
				if(line.charAt(0)=='#'){
					isBase++; 
					e = 0; 
				}
				if(line.charAt(0)!='#'){
					String strs[] = line.split(";");
				 
					for (int i = 0; i < strs.length; i++) {
						 if(isBase == 0) {
							 nave.layer0[e][i] = Integer.parseInt(strs[i]);
						 }else {
							 nave.layer1[e][i] = Integer.parseInt(strs[i]);
						 }
					}
					e++;
				} 
				
			}
			 Constantes.navesBase.add(nave);
			
		 
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
	    System.out.println(e.getMessage()+ "  abreaA!!!");
		e.printStackTrace();
	}
}

public void addNotify()
{
	super.addNotify(); // creates the peer
	startGame(); // start the thread
}

private void startGame()
// initialise and start the thread
{
	if (animator == null || !running) {
		animator = new Thread(this);
		animator.start();
	}
} // end of startGame()

public void stopGame()
// called by the user to stop execution
{ running = false; }


public void run()
/* Repeatedly update, render, sleep */
{
	running = true;
	
	long DifTime,TempoAnterior;
	
	int segundo = 0;
	DifTime = 0;
	TempoAnterior = System.currentTimeMillis();
	
	gui.createBufferStrategy(2);
	strategy = gui.getBufferStrategy();
	
	while(running) {
		
		dbg = (Graphics2D) strategy.getDrawGraphics();
		dbg.setClip(0, 0, Constantes.telaW, Constantes.telaH);
		dbg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		gameUpdate(DifTime);
		gameRender();
		
		dbg.dispose();
		strategy.show();
	
		try {
			Thread.sleep(0); // sleep a bit
		}	
		catch(InterruptedException ex){}
		
		DifTime = System.currentTimeMillis() - TempoAnterior;
		TempoAnterior = System.currentTimeMillis();
				
		if(segundo!=((int)(TempoAnterior/1000))){
			FPS = SFPS;
			SFPS = 1;
			segundo = ((int)(TempoAnterior/1000));
		}else{
			SFPS++;
		}
	
	}
System.exit(0); // so enclosing JFrame/JApplet exits
} // end of run()

private void gameUpdate(long diftime)
{ 
	if(telaAtiva!=null){
		telaAtiva.SimulaSe((int)diftime);
	}
}

private void gameRender()
// draw the current frame to an image buffer
{
	if(telaAtiva!=null){
		telaAtiva.DesenhaSe(dbg);
	}
}


public static void main(String args[])
{
  GamePanel ttPanel = new GamePanel();

  // create a JFrame to hold the timer test JPanel
  JFrame app = new JFrame("Swing Timer Test");
  app.getContentPane().add(ttPanel, BorderLayout.CENTER);
  app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  
  app.setResizable(false);  
  app.setVisible(true);
  
  app.pack();
} // end of main()

public BufferedImage carregaImagem(String path){
	try {
		BufferedImage imgtmp = ImageIO.read(this.getClass().getResourceAsStream(path));
		BufferedImage imagereturn = new BufferedImage(imgtmp.getWidth(),imgtmp.getHeight(),BufferedImage.TYPE_INT_ARGB);
		imagereturn.getGraphics().drawImage(imgtmp, 0, 0, null);
		return imagereturn;
		
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	return null;
}

} // end of GamePanel class

