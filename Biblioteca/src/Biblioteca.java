import java.util.HashMap;
import java.util.Scanner;

public class Biblioteca {

	private Fachada fachada = Fachada.getInstancia();
	private static Biblioteca instancia = null;   
    private HashMap<String, Comando> comandos;
    private Scanner entrada;

	public static void main(String[] args) {
		/** O construtor deve preencher o HashMap com os comandos correpondentes **/	     
		Biblioteca.get().initCasosDeTeste();
		while (true) {
			Biblioteca.get().esperarComando();
		}
	}

	private Biblioteca() {
		entrada = new Scanner(System.in);
	    comandos = new HashMap<String, Comando>();
	   /** Inicializa comandos: sai, emp, dev, res, obs, liv, usu, ntf**/
	    comandos.put("sai", new ComandoSair());
	    comandos.put("emp", new ComandoFazerEmprestimo());
	    comandos.put("dev", new ComandoDevolverLivro());
        comandos.put("res", new ComandoReservarLivro());
        comandos.put("obs", new ComandoObserver());
        comandos.put("ntf", new ComandoNotificador());
        comandos.put("liv", new ComandoConsultarLivro());
        comandos.put("usu", new ComandoConsultarUsuario());
      }
	
	public static Biblioteca get(){
		if(instancia == null){
			instancia = new Biblioteca();
		}
		return instancia;
	}

	public Scanner getEntrada(){
		return this.entrada;
	}

	public void exibir(String mensagem) {
		System.out.println(mensagem);
	}
  
	public void esperarComando(){
		System.out.println("Digite o comando:");
  		String comando = entrada.next();
  		switch (comando) {
  		case "emp": case "dev": case "res": case "obs":{
  			String parametro1 = entrada.next();
  			String parametro2 = entrada.next();
  			String saida= comandos.get(comando).executar(new ParametrosCommand(parametro1,parametro2));
  			System.out.println(saida);
  			break;	
  		}
  		case "liv": case "usu": case "ntf": {
  			String parametro1 = entrada.next();
  			String parametro2 = "";
  			String saida=comandos.get(comando).executar(new ParametrosCommand(parametro1,parametro2));
  			System.out.println(saida);
  			break;
  		}
  		case "sai":{
  			String parametro1 = "";
  			String parametro2 = "";
  			comandos.get(comando).executar(new ParametrosCommand(parametro1,parametro2));
  			break;
  		}
  		default:{
  			System.out.println("COMANDO INVÁLIDO!");
  		}
  		}
  	}
		
	public void initCasosDeTeste(){
		Biblioteca.get().fachada.cadastrarFuncionario("123","João da Silva");
		Biblioteca.get().fachada.cadastrarAluno("456","Luiz Fernando Rodrigues");
		Biblioteca.get().fachada.cadastrarFuncionario("789","Pedro Paulo");
		Biblioteca.get().fachada.cadastrarProfessor("100","Carlos Lucena");
		Biblioteca.get().fachada.cadastrarLivro("100", "Engenharia de Software",	"AddisonWesley",	"Ian Sommervile",	"6ª",	"2000");					
		Biblioteca.get().fachada.cadastrarLivro("101",	"UML – Guia do Usuário", "Campus",	"Grady Booch,James Rumbaugh, Ivar Jacobson", "7ª", "2000");			
		Biblioteca.get().fachada.cadastrarLivro("200",	"Code Complete", "Microsoft Press",	"Steve McConnell",	"2ª",	"2014");
		Biblioteca.get().fachada.cadastrarLivro("201", "Agile Software Development, Principles, Patterns and Practices",	"Prentice Hall",	"Robert Martin",	"1ª", "2002");
		Biblioteca.get().fachada.cadastrarLivro("300",	"Refactoring:Improving the Design  of Existing Code","Addison-Wesley Professional",	"Martin Fowler","1ª","1999");
		Biblioteca.get().fachada.cadastrarLivro("301", "Software Metrics: A Rigorous and Practical Approach",	"CRC Press",	"Norman Fenton, James Bieman",	"3ª",	"2014");
		Biblioteca.get().fachada.cadastrarLivro("400","Design Patterns:Elements of Reusable Object-Oriented Software",	"Addison-Wesley Professional",	"Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides",	"1ª",	"1994");
		Biblioteca.get().fachada.cadastrarLivro("401",	"UML Distilled: A	Brief Guide to the Standard Object Modeling Language", "Addison-Wesley Professional",	"Martin Fowler","3ª","2003");
		Biblioteca.get().fachada.cadastrarExemplar("01", Biblioteca.get().fachada.getLivro("100"));
		Biblioteca.get().fachada.cadastrarExemplar("02", Biblioteca.get().fachada.getLivro("100"));
		Biblioteca.get().fachada.cadastrarExemplar("03", Biblioteca.get().fachada.getLivro("101"));
		Biblioteca.get().fachada.cadastrarExemplar("04", Biblioteca.get().fachada.getLivro("200"));
		Biblioteca.get().fachada.cadastrarExemplar("05", Biblioteca.get().fachada.getLivro("201"));
		Biblioteca.get().fachada.cadastrarExemplar("06", Biblioteca.get().fachada.getLivro("300"));
		Biblioteca.get().fachada.cadastrarExemplar("07", Biblioteca.get().fachada.getLivro("300"));
		Biblioteca.get().fachada.cadastrarExemplar("08", Biblioteca.get().fachada.getLivro("400"));
		Biblioteca.get().fachada.cadastrarExemplar("09", Biblioteca.get().fachada.getLivro("400"));
				
	}
}
