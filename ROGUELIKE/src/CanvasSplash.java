import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;

public class CanvasSplash extends MyCanvas{

	int timer = 0;
	@Override
	public void SimulaSe(int diftime) {
		// TODO Auto-generated method stub
		timer+=diftime;
		
		if(timer > 1000/*8000*/) {
			GamePanel.telaAtiva = new CanvasMain();
		}
	}

	@Override
	public void DesenhaSe(Graphics2D dbg) {
		// TODO Auto-generated method stub
		if(timer < 1000) {
			dbg.setColor(Color.WHITE);
			dbg.fillRect(0, 0, Constantes.telaW, Constantes.telaH);
			
		AffineTransform trans = dbg.getTransform();
		//dbg.scale(1.2*timer/1000.0, 1.2*timer/1000.0);
		 
		dbg.translate(Constantes.telaW/2 - 120+(timer-1000), Constantes.telaH/2);
		 dbg.setFont(Constantes.font.deriveFont(Font.PLAIN, 70));	 
		 dbg.setColor(Color.black);
		 dbg.drawString("DKC", 0,0);
		 dbg.setFont(Constantes.font.deriveFont(Font.PLAIN, 69));	 
		 dbg.setColor(Color.blue);
		 dbg.drawString("DKC", -1,0);
		 
		 dbg.setTransform(trans);
		 
		 dbg.translate(Constantes.telaW/2 - 120+(1000-timer), Constantes.telaH/2);
		 dbg.setFont(Constantes.font.deriveFont(Font.PLAIN, 70));
		 dbg.setColor(Color.black);
		 dbg.drawString("ENGINE",  0,50);
		 dbg.setFont(Constantes.font.deriveFont(Font.PLAIN, 69));	 
		 dbg.setColor(Color.blue);
		 dbg.drawString("ENGINE",  -1,50);
		 
		 dbg.setTransform(trans);
		 
		}else if(timer < 2500){
			dbg.setColor(Color.WHITE);
			dbg.fillRect(0, 0, Constantes.telaW, Constantes.telaH);
			
			AffineTransform trans = dbg.getTransform();
			
			 dbg.translate(Constantes.telaW/2 - 120, Constantes.telaH/2);
			 dbg.setFont(Constantes.font.deriveFont(Font.PLAIN, 70));	 
			 dbg.setColor(Color.black);
			 dbg.drawString("DKC", 0,0);
			 dbg.setFont(Constantes.font.deriveFont(Font.PLAIN, 69));	 
			 dbg.setColor(Color.blue);
			 dbg.drawString("DKC", -1,0);
			 dbg.setFont(Constantes.font.deriveFont(Font.PLAIN, 70));
			 dbg.setColor(Color.black);
			 dbg.drawString("ENGINE",  0,50);
			 dbg.setFont(Constantes.font.deriveFont(Font.PLAIN, 69));	 
			 dbg.setColor(Color.blue);
			 dbg.drawString("ENGINE",  -1,50);
			 
			 dbg.setTransform(trans);
		}else {
			dbg.setColor(Color.black);
			dbg.fillRect(0, 0, Constantes.telaW, Constantes.telaH);
			
			dbg.setFont(Constantes.font.deriveFont(Font.PLAIN, 70));
			 dbg.setColor(Color.YELLOW);
			 dbg.drawString("A GAME BY: ", Constantes.telaW/2 - 321, Constantes.telaH/2);
			 dbg.drawString("  RICARDO GEROBIN ", Constantes.telaW/2 - 321, Constantes.telaH/2+50);
			 dbg.drawString("  DENNIS KERR COELHO ", Constantes.telaW/2 - 321, Constantes.telaH/2+100);
			 dbg.drawString("  MARCELO DUSRBCGWXYZ ", Constantes.telaW/2 - 321, Constantes.telaH/2+150);
			
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
		
	}

}
