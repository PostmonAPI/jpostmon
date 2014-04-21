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
package br.com.postmon.jpostmon.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Representação do endereço completo retornado pela chamada a API.
 * 
 * @author netomarin
 * @version 1.0.0
 * 
 */
public class Endereco {

	private String logradouro;
	private String cep;
	private String bairro;
	private String cidade;
	@SerializedName("cidade_info")
	private CidadeInfo cidadeInfo;
	private String estado;
	@SerializedName("estado_info")
	private EstadoInfo estadoInfo;
	private String complemento;
	private String unidade;
	private String endereco;

	public Endereco() {
	}

	public Endereco(String logradouro, String cep, String bairro,
			String cidade, CidadeInfo cidadeInfo, String estado,
			EstadoInfo estadoInfo) {
		super();
		this.logradouro = logradouro;
		this.cep = cep;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cidadeInfo = cidadeInfo;
		this.estado = estado;
		this.estadoInfo = estadoInfo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public CidadeInfo getCidadeInfo() {
		return cidadeInfo;
	}

	public void setCidadeInfo(CidadeInfo cidadeInfo) {
		this.cidadeInfo = cidadeInfo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public EstadoInfo getEstadoInfo() {
		return estadoInfo;
	}

	public void setEstadoInfo(EstadoInfo estadoInfo) {
		this.estadoInfo = estadoInfo;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return logradouro + ", " + bairro + " - Cidade: " + cidade + " / "
				+ estado + " - CEP: " + cep;
	}
}