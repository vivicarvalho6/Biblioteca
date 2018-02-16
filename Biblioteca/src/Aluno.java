public class Aluno extends Usuario {

	private static int tempoDeEmprestimo = 5;

	private static int limiteDeEmprestimos = 4;

	public Aluno(String codigo, String nome) {
		super(codigo,nome,new Regra2());
	}

	public int getTempoDeEmprestimo() {
		return Aluno.tempoDeEmprestimo;
	}

	public int getLimiteDeEmprestimos() {
		return Aluno.limiteDeEmprestimos;
	}

}
