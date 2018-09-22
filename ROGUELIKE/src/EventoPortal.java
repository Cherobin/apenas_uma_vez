
public class EventoPortal extends Evento {
	int dx,dy;
	int mapa;
	
	public EventoPortal() {
		// TODO Auto-generated constructor stub
	}
	
	public EventoPortal(String strs[]) {
		parse(strs);
	}

	public void parse(String strs[]){
		super.parse(strs);
		
		dx = Integer.parseInt(strs[readpointer]);
		dy = Integer.parseInt(strs[readpointer+1]);
		mapa = Integer.parseInt(strs[readpointer+2]);
	}

	@Override
	public void executaEvento(Personagem pers) {
		// TODO Auto-generated method stub
		pers.X = dx*16-pers.boundingbox.width/2;
		pers.Y = dy*16-pers.boundingbox.height-8;
		executaAcao();
	}
}
