public class ComandoNotificador  implements Comando
{
 public String executar(ParametrosCommand parametros){
	 String codigoUsuario = parametros.getAtributo1();
     return Fachada.getInstancia().consultarProfessor(codigoUsuario);
    }
}
