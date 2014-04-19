/**
 * Copyright 2014 Postmon API (http://postmon.com.br)
 * https://github.com/PostmonAPI 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.postmon.jpostmon;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.postmon.jpostmon.dao.Consultas;
import br.com.postmon.jpostmon.dao.Endereco;
import br.com.postmon.jpostmon.dao.Rastreio;

import com.google.gson.Gson;

/**
 * Classe para efetuar consultas a API. Apenas consulta de CEP disponível no
 * momento.
 * 
 * @author netomarin
 * 
 */
public class Postmon {

	private Consultas tipoConsulta;
	private Consultas.Provider tipoRastreio;
	private String cep;
	private Client client;
	private String codigoRastreamento;

	private Postmon(Consultas tipoConsulta) {
		client = ClientBuilder.newClient();
		this.tipoConsulta = tipoConsulta;
	}

	public static Postmon consultar(Consultas tipoConsulta) {
		return new Postmon(tipoConsulta);
	}

	public Postmon cep(String cep) {
		this.cep = cep;
		return this;
	}

	public Postmon provider(Consultas.Provider provider) {
		this.tipoRastreio = provider;
		return this;
	}

	public Postmon codigoRastreio(String codigoRastreamento) {
		this.codigoRastreamento = codigoRastreamento;
		return this;
	}

	public Endereco enviar() throws UnsupportedOperationException {
		if (tipoConsulta.equals(Consultas.CEP)) {
			WebTarget target = client.target(
					APICommons.POSTMON_HOST + APICommons.POSTMON_VER
							+ tipoConsulta).path(cep);

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

	public Rastreio rastrear() {
		if (tipoConsulta.equals(Consultas.RASTREIO)) {
			if (tipoRastreio.equals(Consultas.Provider.ECT)) {
				WebTarget target = client.target(
						APICommons.POSTMON_HOST + APICommons.POSTMON_VER
								+ tipoConsulta + tipoRastreio)
						.path(codigoRastreamento);

				Invocation.Builder invocationBuilder = target
						.request(MediaType.APPLICATION_JSON);

				Response response = invocationBuilder.get();
				Gson gson = new Gson();
				return gson.fromJson(response.readEntity(String.class),
						Rastreio.class);
			} else {
				throw new UnsupportedOperationException();
			}
		} else {
			throw new UnsupportedOperationException();
		}
	}
}