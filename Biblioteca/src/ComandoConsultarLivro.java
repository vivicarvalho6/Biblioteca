
public class ComandoConsultarLivro  implements Comando
{
 public String executar(ParametrosCommand parametros){
     String codigoLivro = parametros.getAtributo1();
     return Fachada.getInstancia().consultarLivro(codigoLivro);
    }
}
