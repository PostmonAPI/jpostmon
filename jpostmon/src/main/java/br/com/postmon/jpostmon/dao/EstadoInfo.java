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
 * Representação das informações de estado recebidas da API.
 * @author netomarin
 *
 */
public class EstadoInfo {

	private String nome;
	@SerializedName("area_km2")
	private String areaKm2;
	@SerializedName("codigo_ibge")
	private int codigoIBGE;
	
	public EstadoInfo() {}
	
	public EstadoInfo(String nome, String areaKm2, int codigoIBGE) {
		super();
				
		this.nome = nome;		
		this.areaKm2 = areaKm2;		
		this.codigoIBGE = codigoIBGE;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getAreaKm2() {
		return areaKm2;
	}
	
	public void setAreaKm2(String areaKm2) {
		this.areaKm2 = areaKm2;
	}
	
	public int getCodigoIBGE() {
		return codigoIBGE;
	}
	
	public void setCodigoIBGE(int codigoIBGE) {
		this.codigoIBGE = codigoIBGE;
	}
}