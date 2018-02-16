public class Professor extends Usuario implements Observer {

	private static int tempoDeEmprestimo = 7;

	private static int limiteDeEmprestimos = 0;

	private int qtdNotificacoes = 0;

	public Professor(String codigo, String nome) {
		super(codigo,nome,new Regra1());
	}

	public int getTempoDeEmprestimo() {
		return Professor.tempoDeEmprestimo;
	}

	public int getLimiteDeEmprestimos() {
		return Professor.limiteDeEmprestimos;
	}

	public int getNotificacoes() {
		return this.qtdNotificacoes;
	}

    public void atualizar(Subject s){
        this.qtdNotificacoes++;
    }

}
