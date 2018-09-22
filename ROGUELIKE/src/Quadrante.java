import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javafx.geometry.Point3D;

public class Quadrante {
	
	int xquadrante;
	int yquadrante;
	
	ArrayList<Point3D> listaDeFagulhas;	
	public Quadrante(int xquadrante,int yquadrante) {
		
		this.xquadrante = xquadrante;
		this.yquadrante = yquadrante;
		
		listaDeFagulhas = new ArrayList<>(); 
		
		for(int i = 0; i < 10000; i++) {
			Point3D p = new Point3D(8000*GamePanel.rnd.nextDouble(), 8000*GamePanel.rnd.nextDouble(),GamePanel.rnd.nextInt(100));
			listaDeFagulhas.add(p);
		}
	}
	
	public void DesenhaSe(Graphics2D dbg,int xMundo,int yMundo) {
		// TODO Auto-generated method stub
		for(Point3D p:listaDeFagulhas) {
			if(Constantes.telaRect.contains((int)(p.getX()+8000*xquadrante), (int)(p.getY()+8000*yquadrante))) {
				dbg.setColor(new Color((int)p.getZ(),(int)p.getZ(),(int)p.getZ()));
				//System.out.println(" "+xquadrante+" "+yquadrante);
				dbg.fillRect((int)((p.getX()+8000*xquadrante)-xMundo), (int)((p.getY()+8000*yquadrante)-yMundo), 2, 2);
		//		dbg.setColor(Color.red);
		//		dbg.drawRect((int)X-xMundo+2, (int)Y-yMundo+2, 28, 44);
			}
		}
	}
}
