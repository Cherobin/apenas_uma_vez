import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;


public class CanvasCostrucao extends MyCanvas {
	
	public MyCanvas canvasOrigem = null;
	
	public Color cor = null;
	
	private BufferedImage fundo;
	
	
    int NTileX,NTileY;
    int base[][] = new int[31][31];
    int armas[][] = new int[31][31];
    
    

	public CanvasCostrucao(MyCanvas canvasOrigem,Color cor) {
		fundo = GamePanel.instance.carregaImagem("fundo.png");
		
		this.canvasOrigem = canvasOrigem;
		this.cor = cor;
		NTileX = NTileY = 32;
		for (int j = 0; j < base.length; j++) {
			for (int i = 0; i < base[j].length; i++) {
				base[j][i] = 0;
			}
		}
		
		for (int j = 0; j < armas.length; j++) {
			for (int i = 0; i < armas[j].length; i++) {
				armas[j][i] = 0;
			}
		}
	}
	 
	@Override
	public void SimulaSe(int diftime) {
		// TODO Auto-generated method stub
	 
	}

	@Override
	public void DesenhaSe(Graphics2D dbg) {
		// TODO Auto-generated method stub
		dbg.setColor(cor);
		dbg.fillRect(0, 0, Constantes.telaW, Constantes.telaH);
		
		dbg.setColor(Color.white);
		dbg.drawString("Clique nos icones para construção", 10, 10);
	 
		for (int j = 0; j < base.length; j++) {
			for (int i = 0; i < base[j].length; i++) {
				    int tilex = (base[j][i]%NTileX);
	                int tiley = (base[j][i]/NTileY);
	                if(base[j][i]==0) {
	                	 dbg.drawImage(fundo,i*16+384,j*16+50,(i*16)+16+384,(j*16)+16+50,tilex*16,tiley*16,(tilex*16)+16,(tiley*16)+16,null); 	
	                }
	           	}
		}
		
		for (int j = 0; j < Constantes.armas.size(); j++) {
			dbg.drawImage(Constantes.armas.get(j).imagem, 10+16, 10+16*j, null);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_ESCAPE){
			GamePanel.telaAtiva = canvasOrigem;
		}
		if(keyCode == KeyEvent.VK_G){
			GamePanel.telaAtiva = new CanvasCostrucao(this,Color.GREEN);
		}
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
