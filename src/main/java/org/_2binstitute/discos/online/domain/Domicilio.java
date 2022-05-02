package org._2binstitute.discos.online.domain;

public class Domicilio {

	private Integer id;
	private String calle;
	private String numExterior;
	private String numInterior;
	private Usuario usuario;
	private Colonia colonia;
	private TipoDomicilio tipoDomicilio;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumExterior() {
		return numExterior;
	}
	public void setNumExterior(String numExterior) {
		this.numExterior = numExterior;
	}
	public String getNumInterior() {
		return numInterior;
	}
	public void setNumInterior(String numInterior) {
		this.numInterior = numInterior;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Colonia getColonia() {
		return colonia;
	}
	public void setColonia(Colonia colonia) {
		this.colonia = colonia;
	}
	public TipoDomicilio getTipoDomicilio() {
		return tipoDomicilio;
	}
	public void setTipoDomicilio(TipoDomicilio tipoDomicilio) {
		this.tipoDomicilio = tipoDomicilio;
	}	
}
