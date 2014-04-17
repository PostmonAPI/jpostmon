package br.com.postmon.jpostmon;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.postmon.jpostmon.dao.Consultas;
import br.com.postmon.jpostmon.dao.Endereco;

import com.google.gson.Gson;

/**
 * Classe para efetuar consultas a API. Apenas consulta de CEP disponível no momento.
 * @author netomarin
 *
 */
public class Postmon {

	private Consultas consultar;
	private String cep;

	private Postmon(Consultas tipoConsulta) {
		this.consultar = tipoConsulta;
	}

	public static Postmon consultar(Consultas tipoConsulta) {
		return new Postmon(tipoConsulta);
	}

	public Postmon cep(String cep) {
		this.cep = cep;
		return this;
	}

	public Endereco enviar() throws UnsupportedOperationException {
		if (consultar.equals(Consultas.CEP)) {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target(
					APICommons.POSTMON_HOST + APICommons.POSTMON_VER
							+ APICommons.POSTMON_PATH_CEP).path(cep);

			Invocation.Builder invocationBuilder = target
					.request(MediaType.APPLICATION_JSON);

			Response response = invocationBuilder.get();
			Gson gson = new Gson();
			return gson.fromJson(response.readEntity(String.class),
					Endereco.class);
		} else {
			throw new UnsupportedOperationException();
		}
	}
}