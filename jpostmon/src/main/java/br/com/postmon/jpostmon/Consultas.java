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

/**
 * Lista dos tipos de consulta suportadas pelo client.
 * 
 * @author netomarin
 * @version 1.0.0
 * 
 */
public enum Consultas {
	CEP("/cep"), RASTREIO("/rastreio");

	private String consultaPath;

	private Consultas(String consultaPath) {
		this.consultaPath = consultaPath;
	}

	@Override
	public String toString() {
		return consultaPath;
	}

	/**
	 * Lista das entidades suportadas para rastreio.
	 * 
	 * @author netomarin
	 * 
	 */
	public enum Entidade {
		ECT("/ect");

		private String entidadePath;

		private Entidade(String providerPath) {
			this.entidadePath = providerPath;
		}

		@Override
		public String toString() {
			return entidadePath;
		}
	}
}