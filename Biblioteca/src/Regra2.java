public class Regra2 implements RegraEmprestimo {

	public boolean podeEmprestar(Usuario usuario, Livro livro) {
		return ((livroEstaDisponivel(usuario,livro))&&(!usuarioEstaDevedor(usuario,livro))&&(!usuarioTemLimiteDeEmprestimos(usuario,livro))&&(!livroEstaReservado(usuario,livro))&&(!usuarioPossuiEmprestimo(usuario,livro)));
	}
		
	
	private boolean livroEstaDisponivel(Usuario usuario, Livro livro) {
		if (!livro.estaDisponivel()) {
			throw new LivroIndisponivelException("O empréstimo não pôde ser realizado. O livro informado não possui exemplar disponível. Usuario: "+usuario.getNome()+" Livro: "+livro.getTitulo());
		} else {
			return true;
		}
	} 
	
	private boolean usuarioEstaDevedor(Usuario usuario, Livro livro) {
		if (usuario.estaDevedor()) {
			throw new UsuarioDevedorException("O empréstimo não pôde ser realizado. O usuário informado possui empréstimo com atraso. Usuario: "+usuario.getNome()+" Livro: "+livro.getTitulo());
		}else {
			return false;
		}
	}
	
	private boolean usuarioTemLimiteDeEmprestimos(Usuario usuario, Livro livro) {
		if (usuario.getEmprestimosCorrentes().size()==usuario.getLimiteDeEmprestimos()) {
			throw new LimiteDeEmprestimosException("O empréstimo não pôde ser realizado. O usuário informado já possui a quantidade limite de empréstimos. Usuario: "+usuario.getNome()+" Livro: "+livro.getTitulo());
		}else {
			return false;
		}
	}
	
	private boolean livroEstaReservado(Usuario usuario,Livro livro) { 
		if ((livro.getReservas().size()>=livro.getExemplaresDisponiveis()) && (!usuario.possuiReserva(livro))) {
			throw new LivroReservadoException("O empréstimo não pôde ser realizado. Todos os exemplares do livro informado estão reservados. Usuario: "+usuario.getNome()+" Livro: "+livro.getTitulo());
		} else {
			return false;
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
