package br.com.postmon.jpostmon;

import java.security.InvalidParameterException;

import junit.framework.TestCase;
import br.com.postmon.jpostmon.dao.Endereco;
import br.com.postmon.jpostmon.dao.Rastreamento;
import br.com.postmon.jpostmon.exception.PostmonAPIException;

public class PostmonTest extends TestCase {

	public void testBuscarCEPValido() {
		Endereco endereco = null;
		try {
			endereco = Postmon.consultar(Consultas.CEP).cep("01011100")
					.buscar();
		} catch (InvalidParameterException e) {
		} catch (PostmonAPIException e) {
			e.printStackTrace();
		}

		assertNotNull("CEP válido, deve ser retornado um endereco", endereco);
	}
	
	public void testBuscarCEPInexistente() {
		Endereco endereco = null;
		try {
			endereco = Postmon.consultar(Consultas.CEP).cep("11111111")
					.buscar();
		} catch (InvalidParameterException e) {
		} catch (PostmonAPIException e) {
		}

		assertNull("CEP inexistente, deve ser retornado null", endereco);
	}

	public void testeBuscarCEPInvalidoMenor() {
		try {
			Postmon.consultar(Consultas.CEP).cep("1111111")
					.buscar();
		} catch (InvalidParameterException e) {
			// CEP inválido, exceção deve ser lançada.
			assertTrue(true);
		} catch (PostmonAPIException e) {
			e.printStackTrace();
		}
	}
	
	public void testeBuscarCEPInvalidoMaior() {
		try {
			Postmon.consultar(Consultas.CEP).cep("111111111")
					.buscar();
		} catch (InvalidParameterException e) {
			// CEP inválido, exceção deve ser lançada.
			assertTrue(true);
		} catch (PostmonAPIException e) {
			e.printStackTrace();
		}
	}
	
	public void testeRastrearCodigoECTValido() {
		Rastreamento rastreio = null;
	    try {
	        rastreio = Postmon.consultar(Consultas.RASTREIO)
	            .entidade(Consultas.Entidade.ECT)
	            .codigo("RC227774716CN")
	            .rastrear();
	    } catch (InvalidParameterException e) {
	    } catch (PostmonAPIException e) {}
	    
	    assertNotNull("Código rastreio válido, deve ser retornado uma classe Rastreio", rastreio);
	}
	
	public void testeRastrearCodigoECTInvalido() {
		Rastreamento rastreio = null;
	    try {
	        rastreio = Postmon.consultar(Consultas.RASTREIO)
	            .entidade(Consultas.Entidade.ECT)
	            .codigo("xxxxxxxx")
	            .rastrear();
	    } catch (InvalidParameterException e) {
	    } catch (PostmonAPIException e) {}
	    
	    assertNull("Código rastreio inválido, deve ser retornado null", rastreio);
	}
}