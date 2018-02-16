import java.text.*;
import java.util.*;

public class Fachada {
	
	private ArrayList<Livro> livros = new ArrayList<Livro>();
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private static Fachada instancia;

	private Fachada() {}
	
	public static Fachada getInstancia() {
		if(instancia==null) {
			instancia=new Fachada();
		}
		return Fachada.instancia;
	}

	public Usuario getUsuario(String codigo) {
	for(int i=0;i<this.usuarios.size();i++) {
			if(this.usuarios.get(i).getCodigo().equals(codigo)) {
				return this.usuarios.get(i);
			}
			
		}
		throw new IllegalArgumentException("O codigo de usuário informado não existe!");
	}

	public Livro getLivro(String codigo) {
		for(int i=0;i<this.livros.size();i++) {
			if(this.livros.get(i).getCodigo().equals(codigo)) {
				return this.livros.get(i);
			}
		}
		throw new IllegalArgumentException("O codigo de livro informado não existe!");
	}
	
	public ArrayList<Livro> RetornaLivros(){
		return this.livros;
	}

	public void cadastrarLivro(String codigo, String titulo, String editora, String autores, String edicao, String anoDePublicacao) {
		Livro livro = new Livro(codigo,titulo,editora,autores,edicao,anoDePublicacao);
		this.livros.add(livro);
	}

	public void cadastrarExemplar(String codigo, Livro livro) {
		livro.cadastrarExemplar(codigo);
	}

	public void cadastrarFuncionario(String codigo, String nome) {
		Funcionario funcionario = new Funcionario(codigo,nome);
		this.usuarios.add(funcionario);
	}

	public void cadastrarAluno(String codigo, String nome) {
		Aluno aluno = new Aluno(codigo,nome);
		this.usuarios.add(aluno);
	}

	public void cadastrarProfessor(String codigo, String nome) {
		Professor professor = new Professor(codigo,nome);
		this.usuarios.add(professor);
	}

	public String cadastrarObservador(String codigoUsuario, String codigoLivro) {
		String mensagem="O observador não foi cadastrado.";
		try {
			Observer observer = (Observer)getUsuario(codigoUsuario);
			Subject subject= (Subject)getLivro(codigoLivro);
			subject.registerObserver(observer);
			mensagem= "O observador foi cadastrado com sucesso.";
			
		} catch (IllegalArgumentException e) {
			return (e.getMessage());
		}
		return mensagem;
	}


	public String realizarEmprestimo(String codigoUsuario, String codigoLivro) {
		String mensagem ="";
		try {
			Usuario usuario = getUsuario(codigoUsuario);
			Livro livro = getLivro(codigoLivro);
			if(usuario.podeEmprestar(livro)) {
				if (usuario.possuiReserva(livro)) {
					usuario.excluirReserva(livro);
					livro.excluirReserva(usuario);
				}
				usuario.realizarEmprestimo(livro.getExemplarDisponivel());
				mensagem = "O empréstimo foi realizado com sucesso. Usuário: "+usuario.getNome()+" Livro: "+livro.getTitulo();
			}
		} catch (IllegalArgumentException e) {
			return (e.getMessage());
		} catch (LivroIndisponivelException e) {
			return (e.getMessage());
		} catch (UsuarioDevedorException e) {
			return (e.getMessage());
		} catch (LimiteDeEmprestimosException e) {
			return (e.getMessage());
		} catch (LivroReservadoException e) {
			return (e.getMessage());
		} catch (ConflitoDeEmprestimoException e) {
			return (e.getMessage());
		}
		return mensagem;
	}

	public String realizarDevolucao(String codigoUsuario, String codigoLivro) {
		String mensagem ="";
		try {
			Usuario usuario = getUsuario(codigoUsuario);
			Livro livro = getLivro(codigoLivro);
			if(usuario.possuiEmprestimo(livro)) {
				Emprestimo emprestimo = usuario.retornaEmprestimo(livro);
				usuario.realizarDevolucao(emprestimo);
				mensagem = "A devolução foi realizada com sucesso. Usuário: "+usuario.getNome()+" Livro: "+livro.getTitulo();
			} else {
				mensagem = "Não existe empréstimo aberto do livro "+livro.getTitulo()+" para o usuário "+usuario.getNome();
			}
		} catch (IllegalArgumentException e) {
			return (e.getMessage());
		}
		return mensagem;
	}

	public String realizarReserva(String codigoUsuario, String codigoLivro) {
		String mensagem ="";
		try {
			Usuario usuario = getUsuario(codigoUsuario);
			Livro livro = getLivro(codigoLivro);
			if(usuario.podeReservar()) {
				usuario.realizarReserva(livro);
				mensagem = "A reserva do livro "+livro.getTitulo()+" para o usuário "+usuario.getNome()+" foi realizada com sucesso.";
			} else {
				mensagem = "A reserva do livro "+livro.getTitulo()+" não pôde ser realizada, pois o usuário "+usuario.getNome()+" já possui o limite de 3 (três) reservas.";
			}
		} catch (IllegalArgumentException e) {
			return (e.getMessage());
		}
		return mensagem;
	}

		
	public String consultarUsuario(String codigoUsuario) {
		String resultado ="";
		try {
			Usuario usuarioencontrado = getUsuario(codigoUsuario);
			resultado+="Usuário: "+usuarioencontrado.getNome()+"\n";
			if (usuarioencontrado.getEmprestimosCorrentes().size()>0) {
				resultado+="--EMPRESTIMOS CORRENTES--\n";
				for (Emprestimo emprestimo : usuarioencontrado.getEmprestimosCorrentes()) {
					DateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
					String dataEmprestimo = formataData.format(emprestimo.getDataDeEmprestimo());
					String dataDevolucao = formataData.format(emprestimo.getDataDeDevolucao());
					String titulo=emprestimo.getExemplar().getLivro().getTitulo();
					resultado+="Título: "+titulo+" -Data do empréstimo: "+dataEmprestimo+" -Data de devolução prevista: "+dataDevolucao+"\n";
				} 
			}else { resultado+="O usuário não possui empréstimos correntes.\n";}
			if (usuarioencontrado.getEmprestimosPassados().size()>0) {
				resultado+="--EMPRESTIMOS PASSADOS--\n";
				for (Emprestimo emprestimo : usuarioencontrado.getEmprestimosPassados()) {
					DateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
					String dataEmprestimo = formataData.format(emprestimo.getDataDeEmprestimo());
					String dataDevolucao = formataData.format(emprestimo.getDataDeDevolucao());
					String titulo=emprestimo.getExemplar().getLivro().getTitulo();
					resultado+="Título: "+titulo+" -Data do empréstimo: "+dataEmprestimo+" -Data de devolução: "+dataDevolucao+"\n";
				}
			}else { resultado+="O usuário não possui empréstimos passados.\n";}
			if (usuarioencontrado.getReservas().size()>0) {
				resultado+="--RESERVAS--\n";
				for (Reserva reserva : usuarioencontrado.getReservas()) {
					DateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
					String data = formataData.format(reserva.getDataDeRealização());
					String titulo=reserva.getLivro().getTitulo();
					resultado+="Título: "+titulo+" -Data de solicitação: "+data+"\n";
				}
				
			} else { resultado+="O usuário não possui reservas.\n";}				
		} catch (IllegalArgumentException e) {
			return (e.getMessage());
		}
		return resultado;
		
	}

	public String consultarLivro(String codigoLivro) {
		String resultado ="";
		try {
			Livro livroencontrado = getLivro(codigoLivro);
			int quantidadeReservas=livroencontrado.getReservas().size();
			resultado = "Titulo: "+livroencontrado.getTitulo() + "\n Quantidade de reservas: " + quantidadeReservas+"\n";
			if(quantidadeReservas!=0) {
				resultado+="--RESERVAS--\n";
				for(int i=0;i<livroencontrado.getReservas().size();i++) {
					Reserva reserva = livroencontrado.getReservas().get(i);
					resultado+="Usuário: "+reserva.getUsuario().getNome()+"\n";
				}
			}
			resultado+="--EXEMPLARES--\n";
			for(int i=0;i<livroencontrado.getExemplares().size();i++) {
				String status = "";
				String emprestimo="";
				Exemplar exemplar = livroencontrado.getExemplares().get(i);
				if (exemplar.getDisponibilidade()) {
					status = "Disponível";
					emprestimo="";
				} else {
					status = "Emprestado";
					DateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
					String dataEmprestimo = formataData.format(exemplar.getEmprestimo().getDataDeEmprestimo());
					String dataDevolucao = formataData.format(exemplar.getEmprestimo().getDataDeDevolucao());
					emprestimo = "      Dados do empréstimo:  -Usuário: "+exemplar.getEmprestimo().getUsuario().getNome()+" -Data de empréstimo: "+dataEmprestimo+" -Data prevista da devolução: " + dataDevolucao+"\n";
				}
				resultado+="Código: "+exemplar.getCodigo()+" Status: "+status+"\n"+emprestimo+"\n";
			}	
					
		} catch (IllegalArgumentException e) {
			return (e.getMessage());
		}
		return resultado;		
	}

	public String consultarProfessor(String CodigoProfessor) {
		Professor professor = (Professor) getUsuario(CodigoProfessor);
		return "O professor " + professor.getNome() + " foi notificado " + professor.getNotificacoes() + " vezes.";
	}
}
