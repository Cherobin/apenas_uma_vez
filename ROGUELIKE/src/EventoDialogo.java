
public class EventoDialogo extends Evento {

	String dialogo = "";
	
	public EventoDialogo() {
		// TODO Auto-generated constructor stub
	}
	
	public EventoDialogo(String strs[]) {
		parse(strs);
	}

	public void parse(String strs[]){
		super.parse(strs);
		
		dialogo = strs[readpointer];

	}

	@Override
	public void executaEvento(Personagem pers) {
		// TODO Auto-generated method stub
		System.out.println(""+dialogo);
		executaAcao();
	}
	
	

}
