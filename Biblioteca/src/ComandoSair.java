public class ComandoSair implements Comando
{
    public String executar(ParametrosCommand parametros){
        System.exit(0);
        return null;
    }
}
