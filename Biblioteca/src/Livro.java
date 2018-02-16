import java.util.*;

public class Livro extends Subject {
		
    private ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
	private String codigo;
	private String titulo;
	private String editora;
	private String autores;
	private String edicao;
	private String anoDePublicacao;

	public Livro(String codigo, String titulo, String editora, String autores, String edicao, String anoDePublicacao) {
		this.codigo=codigo;
		this.titulo=titulo;
		this.editora=editora;
		this.autores=autores;
		this.edicao=edicao;
		this.anoDePublicacao=anoDePublicacao;
	}

	public void cadastrarExemplar(String codigo) {
		Exemplar novoExemplar= new Exemplar(codigo,this);
		this.exemplares.add(novoExemplar);
	}

	public String getTitulo() {
		return this.titulo;
	}

	public String getCodigo() {
		return this.codigo;
	}
		
	public ArrayList<Reserva> getReservas() {
		return this.reservas;
	}
	
	
	public ArrayList<Exemplar> getExemplares() {
		return this.exemplares;
	}

	public void cadastrarReserva(Reserva reserva) {
		this.reservas.add(reserva);
		if(this.reservas.size()>2) {
			this.notifyObservers();
			}	
	}

	public void excluirReserva(Usuario usuario) {
		for (Reserva reserva:reservas) {
			if(reserva.getUsuario().equals(usuario)) {
				this.reservas.remove(reserva);
			}
		}
		
	}
	
	public Exemplar getExemplarDisponivel(){
		for(int i=0;i<this.exemplares.size();i++) {
			if(this.exemplares.get(i).getDisponibilidade()==true)
			return this.exemplares.get(i);
		}
		return null;
	}
	
	public boolean estaDisponivel(){
		for(int i=0;i<this.exemplares.size();i++) {
			if(this.exemplares.get(i).getDisponibilidade()==true)
				return true;
			}
		return false;
	}
	
	
	public int getExemplaresDisponiveis(){
		int numerodex=0;
		for(int i=0;i<this.exemplares.size();i++) {
			if(this.exemplares.get(i).getDisponibilidade()==true)
				numerodex++;
			
		}
		
		return numerodex;
	}		
}