package org._2binstitute.discos.online.service;

import org._2binstitute.discos.online.domain.Domicilio;
import org._2binstitute.discos.online.domain.Perfil;
import org._2binstitute.discos.online.domain.Rol;
import org._2binstitute.discos.online.domain.Usuario;
import org._2binstitute.discos.online.repository.DomicilioRepository;
import org._2binstitute.discos.online.repository.PerfilRepository;
import org._2binstitute.discos.online.repository.RolRepository;
import org._2binstitute.discos.online.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private DomicilioRepository domicilioRepository;
	
	@Autowired
	private PerfilRepository perfilRepository;

	public UsuarioServiceImpl() {}	

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
