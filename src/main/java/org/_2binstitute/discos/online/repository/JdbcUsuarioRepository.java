package org._2binstitute.discos.online.repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org._2binstitute.discos.online.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUsuarioRepository implements UsuarioRepository {	
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert jdbcInsert;
	
	@Autowired
	public JdbcUsuarioRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("usuario").usingGeneratedKeyColumns("id"); 
	}
		
	@Override
	public Usuario insertar(Usuario usuario) {
		Map<String, Object> values = new HashMap<>();
		values.put("nombre", usuario.getNombre());
		values.put("primer_apellido", usuario.getPrimerApellido());
		values.put("segundo_apellido", usuario.getSegundoApellido());
		values.put("password", usuario.getPassword());
		values.put("email", usuario.getEmail());
		values.put("rfc", usuario.getRfc());
		
		Integer idUsuario = jdbcInsert.executeAndReturnKey(values).intValue();		
		usuario.setId(idUsuario);
		
		return usuario;
	}

	@Override
	public Usuario buscarPorEmail(String email) {
		String sql = "SELECT id, email FROM usuario WHERE email  = ?";
		List<Usuario> usuarios = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Usuario>(Usuario.class), email);
		return !usuarios.isEmpty() ? usuarios.get(0) : null;
	}

}
