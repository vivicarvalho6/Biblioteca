public class Exemplar {

	private String codigo;
	private Livro livro;
	private boolean estaDisponivel;
	private Emprestimo emprestimo;

	public Exemplar(String codigo, Livro livro) {
		this.codigo=codigo;
		this.livro=livro;
		this.estaDisponivel=true;
	}

	public void cadastrarEmprestimo(Emprestimo emprestimo) {
		this.emprestimo=emprestimo;
		this.estaDisponivel=false;
	}

	public void realizarDevolucao() {
		this.emprestimo=null;
		this.estaDisponivel=true;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public Emprestimo getEmprestimo() {
		return this.emprestimo;
	}

	public boolean getDisponibilidade() {
		return this.estaDisponivel;
	}

		
	public Livro getLivro() {
		return this.livro;
	}

}
