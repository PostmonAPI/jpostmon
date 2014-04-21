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

/**
 * Representação do retorno de Rastreio enviado pelos Correios.
 * 
 * @author netomarin
 * @version 1.0.0
 * 
 */
public class Rastreamento {

	private String codigo;
	private String servico;
	private Ocorrencia[] historico;

	public Rastreamento() {
	}

	public Rastreamento(String codigo, String servico, Ocorrencia[] historico) {
		super();
		this.codigo = codigo;
		this.servico = servico;
		this.historico = historico;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public Ocorrencia[] getHistorico() {
		return historico;
	}

	public void setHistorico(Ocorrencia[] historico) {
		this.historico = historico;
	}

	class Ocorrencia {
		private String detalhes;
		private String local;
		private String data;
		private String situacao;

		public String getDetalhes() {
			return detalhes;
		}

		public void setDetalhes(String detalhes) {
			this.detalhes = detalhes;
		}

		public String getLocal() {
			return local;
		}

		public void setLocal(String local) {
			this.local = local;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public String getSituacao() {
			return situacao;
		}

		public void setSituacao(String status) {
			this.situacao = status;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Código: " + codigo);
		sb.append("\nServiço: " + servico);

		for (Ocorrencia o : historico) {
			sb.append("\n\tOcorrência: ");
			sb.append("\n\t\tDetalhe: " + o.getDetalhes());
			sb.append("\n\t\tLocal: " + o.getLocal());
			sb.append("\n\t\tData: " + o.getData());
			sb.append("\n\t\tStatus: " + o.getSituacao());
		}

		return sb.toString();
	}
}