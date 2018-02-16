import java.util.Date;
import java.util.Calendar;

public class Emprestimo {

	private Usuario usuario;
	private Date dataDeRealizacao;
	private Date dataDeDevolucao;
	private Exemplar exemplar;
	private boolean emCurso;
	private Calendar calendario;

	public Emprestimo(Usuario usuario, Exemplar exemplar) {
		calendario = Calendar.getInstance();
		this.usuario=usuario;
		this.exemplar=exemplar;
		this.emCurso=true;
		this.dataDeRealizacao=calendario.getTime();
		calendario.add(Calendar.DATE, +usuario.getTempoDeEmprestimo());
		this.dataDeDevolucao = calendario.getTime();
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public Date getDataDeEmprestimo() {
		return this.dataDeRealizacao;
	}

	public Date getDataDeDevolucao() {
		return this.dataDeDevolucao;
	}
	
	public void realizarDevolucao() {
		this.emCurso=false;
		calendario = Calendar.getInstance();
		this.dataDeDevolucao=calendario.getTime();
	}

	public Exemplar getExemplar() {
		return this.exemplar;
	}
	
	public boolean emCurso() {
		return this.emCurso;
	}

}
