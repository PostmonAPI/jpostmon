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

import java.security.InvalidParameterException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.postmon.jpostmon.Consultas.Entidade;
import br.com.postmon.jpostmon.dao.Endereco;
import br.com.postmon.jpostmon.dao.Rastreamento;
import br.com.postmon.jpostmon.exception.PostmonAPIException;

import com.google.gson.Gson;

/**
 * Classe para efetuar consultas à Postmon API. Os métodos responsáveis por efetuaresm as buscas 
 * são {@link Postmon#buscar()} para CEP e {@link Postmon#rastrear()} para rastrear alguma 
 * encomenda.
 * <p>Antes de efetuar uma busca ou rastreio, os parâmetros para as operações devem ser definidos 
 * com as chamdas dos métodos adequados. Veja abaixo exemplos de buscas e rastreamento.
 * <br>Veja abaixo exemplo de consulta de CEP:</p>
 *
 *    <pre>    Endereco endereco = null;
 *    try {
 *        endereco = Postmon.consultar(Consultas.CEP)
 *            .cep("01011100")
 *            .buscar();
 *    } catch (InvalidParameterException e) {
 *        //Erro quando o CEP não é informado corretamente
 *    } catch (PostmonAPIException e) {
 *        //Algum erro na comunicação com a API
 *    }
 *
 *    if (endereco != null) {
 *        //CEP encontrado
 *        System.out.println(endereco.toString());
 *    } else {
 *        //CEP NÃO encontrado. Trate a situação como achar melhor.         
 *    }</pre>
 * 
 *    <p>Exemplo de rastreamento de um código dos Correios:</p>
 *    <pre>    Rastreamento rastreio = null;
 *    try {
 *        rastreio = Postmon.consultar(Consultas.RASTREIO)
 *            .entidade(Consultas.Entidade.ECT)
 *            .codigo("RC227774716CN")
 *            .rastrear();
 *    } catch (InvalidParameterException e) {
 *        //Erro quando o código não é informado
 *    } catch (PostmonAPIException e) {
 *        //Algum erro na comunicação com a API.
 *    }
 *
 *    if (endereco != null) {
 *        //Código de rastreamento encontrado
 *        System.out.println(rastreio.toString());
 *    } else {
 *        // Código de Rastreamento NÃO encontrado. Trate a situação como achar melhor.         
 *    }</pre>
 * 
 * @author netomarin
 * @version 1.0.0
 * 
 */
public class Postmon {

	private Consultas tipoConsulta;
	private Consultas.Entidade entidadeRastreio;
	private String cep;
	private Client client;
	private String codigo;

	/**
	 * Construtor privado da classe que define o tipo de consulta que será
	 * realizada. O tipo da consulta é definido através da chamada do método
	 * {@link Postmon#consultar(Consultas)}
	 * 
	 * @param tipoConsulta
	 */
	private Postmon(Consultas tipoConsulta) {
		client = ClientBuilder.newClient();
		this.tipoConsulta = tipoConsulta;
	}

	/**
	 * Método para criar uma nova instância do Postmon para efetuar um
	 * determinado tipo de consulta. Depois de retornada a instância é
	 * necessário configurar os dados da consulta a ser realizada fazendo
	 * chamadas para os métodos correspondentes.
	 * 
	 * @param tipoConsulta
	 *            a ser realizada. Ver {@link Consultas} para conhecer as
	 *            disponíveis.
	 * @return nova instância do Postmon pra ser configurada para realizar uma
	 *         consulta
	 */
	public static Postmon consultar(Consultas tipoConsulta) {
		return new Postmon(tipoConsulta);
	}

	/**
	 * Método para definir o CEP a ser buscado. Retorna a mesma instância do
	 * Postmon, mas agora com o cep definido. O CEP deve ter 8 caracteres
	 * númericos, sem traço ou no formato 99999-999.
	 * 
	 * @param cep
	 *            para realizar a busca de {@link Endereco}
	 * @return Postmon configurado com o CEP a ser buscado.
	 * @throws InvalidParameterException
	 *             caso o CEP seja inválido.
	 */
	public Postmon cep(String cep) {
		if (cep != null && cep.length() == 8) {
			String cepAux = cep;
			cep = cepAux.substring(0, 5) + "-" + cepAux.substring(5);
		}

		if (cep != null && cep.matches("[0-9]{5}-[0-9]{3}")) {
			this.cep = cep;
			return this;
		}

		throw new InvalidParameterException("CEP inválido");
	}

	/**
	 * Método para definir a {@link Entidade} para o rastreio de um
	 * encomenda/correspondência. Para que o rastreamento seja efetuado
	 * corretamente, é necessário definir o código a ser rastreado.
	 * 
	 * @param entidade
	 *            provedora do rastreamento de encomendas.
	 * @return {@link Postmon} configurado com a Entidade que terá o código
	 *         rastreado.
	 */
	public Postmon entidade(Consultas.Entidade entidade) {
		this.entidadeRastreio = entidade;
		return this;
	}

	/**
	 * Método para definir o código do rastreamento a ser realizado. Para que o
	 * rastreamento seja efetuado corretamente, é necessário definir a
	 * {@link Entidade} que se deseja rastrear.
	 * 
	 * @param codigo
	 *            fornecido pela entidade para o rastreamento da encomenda.
	 * @return {@link Postmon} configurado com o código a ser rastreado.
	 */
	public Postmon codigo(String codigo) {
		this.codigo = codigo;
		return this;
	}

	/**
	 * Método para realizar a busca de um CEP determinado. Antes de efetuar uma
	 * busca, o CEP deve ser configurado através da chamada do método
	 * {@link Postmon#cep}.
	 * 
	 * @return {@link Endereco} com os dados relativos ao CEP informado para a
	 *         busca. Se o CEP não for encontrado, é retornado null.
	 * @throws UnsupportedOperationException
	 *             se o tipo de consulta não for suportado pela busca. Na versão
	 *             atual, a busca é realizada apenas para {@link Consultas#CEP}.
	 * @throws PostmonAPIException
	 *             se o retorno da API tiver retorno com status diferente de 200
	 */
	public Endereco buscar() throws UnsupportedOperationException,
			PostmonAPIException {
		if (tipoConsulta.equals(Consultas.CEP)) {
			if (cep == null) {
				throw new InvalidParameterException("CEP não informado.");
			}

			WebTarget target = client.target(
					APICommons.POSTMON_HOST + APICommons.POSTMON_VER
							+ tipoConsulta).path(cep);

			Invocation.Builder invocationBuilder = target
					.request(MediaType.APPLICATION_JSON);

			Response response = invocationBuilder.get();
			if (response.getStatus() == 200) {
				Gson gson = new Gson();
				return gson.fromJson(response.readEntity(String.class),
						Endereco.class);
			} else {
				throw new PostmonAPIException("Erro ao buscar CEP: "
						+ response.getStatus() + ": "
						+ response.getStatusInfo());
			}
		} else {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Método para realizar o rastreamento do código informado em uma
	 * determinada {@link Entidade}. Antes de efetuar o rastreamento, o código
	 * deve ser configurado através da chamada do método
	 * {@link Postmon#codigo(String)}.
	 * 
	 * @return {@link Rastreamento} com os dados retornado para o código
	 *         informado. Se o rastreamento não for encontrado, é retornado
	 *         null.
	 * @throws InvalidParameterException
	 *             caso não seja informado um código antes de efetuar o
	 *             rastreamento.
	 * @throws PostmonAPIException
	 *             se o retorno da API tiver retorno com status diferente de 200
	 */
	public Rastreamento rastrear() throws InvalidParameterException,
			PostmonAPIException {
		if (tipoConsulta.equals(Consultas.RASTREIO)) {
			if (codigo == null) {
				throw new InvalidParameterException(
						"Código de rastreamento não informado");
			}
			if (entidadeRastreio.equals(Consultas.Entidade.ECT)) {
				WebTarget target = client.target(
						APICommons.POSTMON_HOST + APICommons.POSTMON_VER
								+ tipoConsulta + entidadeRastreio).path(codigo);

				Invocation.Builder invocationBuilder = target
						.request(MediaType.APPLICATION_JSON);

				Response response = invocationBuilder.get();
				if (response.getStatus() == 200) {
					Gson gson = new Gson();
					return gson.fromJson(response.readEntity(String.class),
							Rastreamento.class);
				} else if (response.getStatus() == 404) {
					return null;
				} else {
					throw new PostmonAPIException("Erro ao rastrear: "
							+ response.getStatus() + ": "
							+ response.getStatusInfo());
				}
			} else {
				throw new UnsupportedOperationException();
			}
		} else {
			throw new UnsupportedOperationException();
		}
	}
}