package org._2binstitute.discos.online.service;
import org._2binstitute.discos.online.domain.Domicilio;
import org._2binstitute.discos.online.domain.Usuario;

public interface UsuarioService {
	
	Usuario registrarUsuario(Usuario usuario, Domicilio domicilio);
	
}
