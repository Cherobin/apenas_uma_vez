public abstract class Evento {
	int x, y;
	int tipo;
	int codigo;
	int Pvar, Pop, Pval;
	int Avar, Aop, Aval;

	int readpointer = 10;

	boolean ativo = false;

	public Evento() {
		// TODO Auto-generated constructor stub
	}

	public Evento(String strs[]) {
		parse(strs);
	}

	public void parse(String strs[]) {
		codigo = Integer.parseInt(strs[0]);
		tipo = Integer.parseInt(strs[1]);
		x = Integer.parseInt(strs[2]);
		y = Integer.parseInt(strs[3]);
		Pvar = Integer.parseInt(strs[4]);
		Pop = Integer.parseInt(strs[5]);
		Pval = Integer.parseInt(strs[6]);
		Avar = Integer.parseInt(strs[7]);
		Aop = Integer.parseInt(strs[8]);
		Aval = Integer.parseInt(strs[9]);
	}

	public abstract void executaEvento(Personagem pers);

	public void testaPrerequisitos() {
		int valovar = GerenciadorDeEventos.variaveis[Pvar];

		ativo = false;

		switch (Pop) {
		case 0:
			if (valovar == Pval) {
				ativo = true;
				return;
			}
			break;
		case 1:
			if (valovar > Pval) {
				ativo = true;
				return;
			}
			break;
		case 2:
			if (valovar < Pval) {
				ativo = true;
				return;
			}
			break;

		default:
			break;
		}
	}
	
	public void executaAcao() {
		switch (Aop) {
		case 0:
			GerenciadorDeEventos.variaveis[Avar] = Aval;
			break;
		case 1:
			GerenciadorDeEventos.variaveis[Avar]++;
			break;
		case 2:
			GerenciadorDeEventos.variaveis[Avar]--;
			break;

		default:
			break;
		}
	}
}
