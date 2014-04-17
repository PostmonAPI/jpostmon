package br.com.postmon.jpostmon.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Representação do endereço completo retornado pela chamada a API.
 * @author netomarin
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

	@Override
	public String toString() {
		return logradouro + ", " + bairro + " - Cidade: " + cidade + " / "
				+ estado + " - CEP: " + cep;
	}
}