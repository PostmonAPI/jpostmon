package br.com.postmon.jpostmon.dao;

import com.google.gson.annotations.SerializedName;


/**
 * Representação das informações de cidade recebidas da API.
 * @author netomarin
 *
 */
public class CidadeInfo {

	@SerializedName("area_km2")
	private String areaKm2;
	@SerializedName("codigo_ibge")
	private int codigoIBGE;
	
	public CidadeInfo() {}
	
	public CidadeInfo(String nome, String areaKm2, int codigoIBGE) {
		super();				
		this.areaKm2 = areaKm2;		
		this.codigoIBGE = codigoIBGE;
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