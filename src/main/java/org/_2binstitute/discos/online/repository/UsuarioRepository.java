package org._2binstitute.discos.online.repository;
import org._2binstitute.discos.online.domain.Usuario;

public interface UsuarioRepository {
	
	Usuario insertar(Usuario usuario);
	Usuario buscarPorEmail(String email);
	
}
