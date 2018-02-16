public class ComandoConsultarUsuario implements Comando
{
 public String executar(ParametrosCommand parametros){
	 String codigoUsuario = parametros.getAtributo1();
     return Fachada.getInstancia().consultarUsuario(codigoUsuario);
    }
}
