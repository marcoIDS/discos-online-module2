package org._2binstitute.discos.online.domain;

public class Perfil {
	private Usuario usuario;
	private Rol rol;
	
	public Perfil(Usuario usuario, Rol rol) {
		this.usuario = usuario;
		this.rol = rol;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
}
