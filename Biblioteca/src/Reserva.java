import java.util.Date;
import java.util.Calendar;

public class Reserva {

	private Usuario usuario;
	private Livro livro;
	private Date dataDeRealizacao;
	private Calendar calendario;
	
	public Reserva(Usuario usuario, Livro livro) {
		calendario = Calendar.getInstance();
		this.dataDeRealizacao=calendario.getTime();
		this.usuario=usuario;
		this.livro=livro;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public Livro getLivro() {
		return this.livro;
	}
	
	public Date getDataDeRealização() {
		return this.dataDeRealizacao;
	}
	
}
