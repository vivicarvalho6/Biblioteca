public class Funcionario extends Usuario {

	private static int tempoDeEmprestimo = 2;

	private static int limiteDeEmprestimos = 3;

	public Funcionario(String codigo, String nome) {
		super(codigo,nome,new Regra2());
	}

	public int getTempoDeEmprestimo() {
		return Funcionario.tempoDeEmprestimo;
	}

	public int getLimiteDeEmprestimos() {
		return Funcionario.limiteDeEmprestimos;
	}

}
