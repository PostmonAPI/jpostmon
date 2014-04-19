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

import br.com.postmon.jpostmon.dao.Consultas;
import br.com.postmon.jpostmon.dao.Endereco;
import br.com.postmon.jpostmon.dao.Rastreio;

/**
 * Classe para demonstrar o uso da API de consulta de CEP
 * 
 * @author netomarin
 */
public class App {
	/*
	 * TODO mover esse código para um exemplo separado e remover do client
	 */
	public static void main(String[] args) {
		Endereco endereco = Postmon.consultar(Consultas.CEP).cep("01011100")
				.enviar();
		System.out.println(endereco.toString());

		Rastreio rastreio = Postmon.consultar(Consultas.RASTREIO)
				.provider(Consultas.Provider.ECT)
				.codigoRastreio("RC227774716CN").rastrear();
		System.out.println(rastreio.toString());
	}
}