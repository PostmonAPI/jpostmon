package br.com.postmon.jpostmon;

import br.com.postmon.jpostmon.dao.Consultas;
import br.com.postmon.jpostmon.dao.Endereco;


/**
 * Classe para demonstrar o uso da API de consulta de CEP
 * @author netomarin
 * TODO mover código para teste e remover essa classe
 */
public class App 
{
    public static void main( String[] args )
    {
    	Endereco endereco = Postmon.consultar(Consultas.CEP)
    			.cep("01011100")
    			.enviar();
        System.out.println( endereco.toString() );
    }
}
