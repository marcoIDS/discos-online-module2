package org._2binstitute.discos.online.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org._2binstitute.discos.online.domain.Domicilio;
import org._2binstitute.discos.online.domain.Perfil;
import org._2binstitute.discos.online.domain.Rol;
import org._2binstitute.discos.online.domain.Usuario;
import org._2binstitute.discos.online.repository.DomicilioRepository;
import org._2binstitute.discos.online.repository.PerfilRepository;
import org._2binstitute.discos.online.repository.RolRepository;
import org._2binstitute.discos.online.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceImplUnitTest {
	@InjectMocks
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Mock
	private UsuarioRepository usuarioRepository;	
	@Mock
	private DomicilioRepository domicilioRepository;	
	@Mock
	private RolRepository rolRepository;
	@Mock
	private PerfilRepository perfilRepository;
	
	@Test
	public void registrarUsuario_UsuarioExistente(){
		Usuario usuario = new Usuario();
		usuario.setEmail("test@gmail.com");
		Domicilio domicilio = new Domicilio();
		when(usuarioRepository.buscarPorEmail(anyString())).thenReturn(usuario);
		
		assertThrows(RuntimeException.class, () -> {
			usuarioServiceImpl.registrarUsuario(usuario, domicilio);
		});
	}	
	
	@Test
	public void registrarUsuario_NoExisteRolCliente() {
		Usuario usuario = new Usuario();
		usuario.setEmail("test@gmail.com");
		Domicilio domicilio = new Domicilio();
		when(usuarioRepository.buscarPorEmail(anyString())).thenReturn(null);
		when(rolRepository.buscarPorDescripcion(anyString())).thenReturn(null);
		
		assertThrows(RuntimeException.class, () -> {
			usuarioServiceImpl.registrarUsuario(usuario, domicilio);
		});
	}
	
	@Test
	public void registrarUsuario_RegistrarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setEmail("test2@gmail.com");
		usuario.setId(-1);
		Domicilio domicilio = new Domicilio();
		domicilio.setId(-2);
		Rol rol = new Rol();
		rol.setId(-3);
		Perfil perfil = new Perfil(usuario, rol);
		when(usuarioRepository.buscarPorEmail(anyString())).thenReturn(null);
		when(rolRepository.buscarPorDescripcion(anyString())).thenReturn(rol);
		when(usuarioRepository.insertar(any(Usuario.class))).thenReturn(usuario);
		when(domicilioRepository.insertar(any(Domicilio.class))).thenReturn(domicilio);
		when(perfilRepository.insertar(any(Perfil.class))).thenReturn(perfil);
		
		usuarioServiceImpl.registrarUsuario(usuario, domicilio);
		
		assertEquals(usuario.getId(), -1);
		assertEquals(domicilio.getId(), -2);
		assertEquals(perfil.getUsuario().getId(), -1);
		assertEquals(perfil.getRol().getId(), -3);
		
		
	}
	
}
