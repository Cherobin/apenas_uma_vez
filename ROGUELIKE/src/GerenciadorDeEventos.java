import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.lang.model.element.ExecutableElement;


public class GerenciadorDeEventos {
	ArrayList<Evento> listaDeEventos = new ArrayList<Evento>();
	public static int variaveis[] = new int[100];
	
	public GerenciadorDeEventos() {
		// TODO Auto-generated constructor stub
	}
	
	public void carregaEventos(InputStream io){
		BufferedReader bfr = new BufferedReader(new InputStreamReader(io));
		
		String line = "";
		
		try {
			while((line = bfr.readLine())!=null){
				if(line.charAt(0)!='#'){
					String strs[] = line.split(";");
					
					int tipo = Integer.parseInt(strs[1]);
					
					Evento ev = null;
					
					switch (tipo) {
					case 0:
						ev = new EventoPortal(strs);
						break;
					case 1:
						ev = new EventoDialogo(strs);
						break;

					default:
						break;
					}
					
					if(ev!=null)
					listaDeEventos.add(ev);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void desenhase(Graphics2D dbg,int mapx,int mapy){

		
		for (Evento ev : listaDeEventos) {
			if(ev.ativo){
				dbg.setColor(Color.yellow);
			}else{
				dbg.setColor(Color.red);
			}
			int px = ev.x*16-mapx;
			int py = ev.y*16-mapy;
			dbg.drawRect(px, py, 16, 16);
		}
	}
	
	public void testaEventos(Personagem pers){
		int bx = (int)((pers.X+pers.charw/2)/16);
		int by = (int)((pers.Y+pers.charh-8)/16);
		
		for (Evento ev : listaDeEventos) {
			ev.testaPrerequisitos();
			if(ev.ativo){
				if(ev.x==bx&&ev.y==by){
					ev.executaEvento(pers);
				}
			}
		}
	}
}
