package org._2binstitute.discos.online.service;

import org._2binstitute.discos.online.domain.Domicilio;
import org._2binstitute.discos.online.domain.Perfil;
import org._2binstitute.discos.online.domain.Rol;
import org._2binstitute.discos.online.domain.Usuario;
import org._2binstitute.discos.online.repository.DomicilioRepository;
import org._2binstitute.discos.online.repository.PerfilRepository;
import org._2binstitute.discos.online.repository.RolRepository;
import org._2binstitute.discos.online.repository.UsuarioRepository;

public class UsuarioServiceImpl implements UsuarioService{
	private UsuarioRepository usuarioRepository; 
	private RolRepository rolRepository;
	private DomicilioRepository domicilioRepository;
	private PerfilRepository perfilRepository;

	public UsuarioServiceImpl() {}	
	
	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public void setRolRepository(RolRepository rolRepository) {
		this.rolRepository = rolRepository;
	}

	public void setDomicilioRepository(DomicilioRepository domicilioRepository) {
		this.domicilioRepository = domicilioRepository;
	}

	public void setPerfilRepository(PerfilRepository perfilRepository) {
		this.perfilRepository = perfilRepository;
	}

	@Override
	public Usuario registrarUsuario(Usuario usuario, Domicilio domicilio) {
		Usuario usuarioExistente = usuarioRepository.buscarPorEmail(usuario.getEmail());
		if(usuarioExistente != null) {
			throw new RuntimeException("Ya existe un usuario registrado con email: " + usuarioExistente.getEmail());
		}
		
		Rol rolCliente = rolRepository.buscarPorDescripcion("CLIENTE");
		if(rolCliente == null) {
			throw new RuntimeException("no existe el rol CLIENTE para asociarlo al usuario");
		}
		
		usuarioRepository.insertar(usuario);
		domicilio.setUsuario(usuario);
		domicilioRepository.insertar(domicilio);
		
		Perfil perfil = new Perfil(usuario, rolCliente);
		perfilRepository.insertar(perfil);
		
		return usuario;
	}

}
