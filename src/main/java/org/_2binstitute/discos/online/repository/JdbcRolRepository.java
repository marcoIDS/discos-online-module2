package org._2binstitute.discos.online.repository;

import org._2binstitute.discos.online.domain.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcRolRepository implements RolRepository{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcRolRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
		
	@Override
	public Rol buscarPorDescripcion(String descripcion) {
		try {			
			String sql = "SELECT id, descripcion FROM rol WHERE descripcion  = ? ";
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Rol>(Rol.class), descripcion);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	/*@Override
	public Rol buscarPorDescripcion(String descripcion) {
		String sql = "SELECT id, descripcion FROM rol WHERE descripcion  = ? ";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Rol>(Rol.class), descripcion);
	}*/


}
