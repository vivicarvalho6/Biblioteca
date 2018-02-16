public class Regra1 implements RegraEmprestimo {

	public boolean podeEmprestar(Usuario usuario, Livro livro) {
		return ((livroEstaDisponivel(usuario,livro))&&(!usuarioPossuiEmprestimo(usuario,livro)));
	}
		
	
	private boolean livroEstaDisponivel(Usuario usuario, Livro livro) {
		if (!livro.estaDisponivel()) {
			throw new LivroIndisponivelException("O empréstimo não pôde ser realizado. O livro informado não possui exemplar disponível. Usuario: "+usuario.getNome()+" Livro: "+livro.getTitulo());
		} else {
			return true;
		}
	}
	
	private boolean usuarioPossuiEmprestimo(Usuario usuario, Livro livro) {
		if (usuario.possuiEmprestimo(livro)) {
			throw new ConflitoDeEmprestimoException("O empréstimo não pôde ser realizado. O usuário já possui empréstimo de um exemplar do livro informado. Usuario: "+usuario.getNome()+" Livro: "+livro.getTitulo());
		} else {
			return false;
		}
	}

}
