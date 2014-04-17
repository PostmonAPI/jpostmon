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