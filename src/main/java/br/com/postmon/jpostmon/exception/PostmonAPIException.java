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
package br.com.postmon.jpostmon.exception;

/**
 * Classe genérica para representar algum erro retornado pela comunicação com a
 * API. Para qualquer retorno diferente de 200 (OK) pode ser lançada essa
 * exceção e o código e a mensagem de status podem ser passadas como mensagem da
 * exceção.
 * 
 * @author netomarin
 * @version 1.0.0
 */
public class PostmonAPIException extends Exception {

	private static final long serialVersionUID = 7781999855005293387L;

	private String message;

	public PostmonAPIException() {
	}

	public PostmonAPIException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}