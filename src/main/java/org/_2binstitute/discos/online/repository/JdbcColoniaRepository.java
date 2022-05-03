package org._2binstitute.discos.online.repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org._2binstitute.discos.online.domain.Colonia;
import org._2binstitute.discos.online.domain.Estado;
import org._2binstitute.discos.online.domain.Municipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcColoniaRepository implements ColoniaRepository{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcColoniaRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private Colonia mapRowToColonia(ResultSet rs, int rowNum) throws SQLException {
		Colonia colonia = new Colonia();
		Municipio municipio = new Municipio();
		Estado estado = new Estado();
		
		colonia.setMunicipio(municipio);
		municipio.setEstado(estado);
		
		colonia.setId(rs.getInt("colonia.id"));
		colonia.setName(rs.getString("colonia.nombre"));
		colonia.setCp(rs.getString("cp"));
		
		municipio.setNombre(rs.getString("municipio.nombre"));
		estado.setNombre(rs.getString("estado.nombre"));
		
		return colonia;
	}
	
	@Override
	public List<Colonia> buscarPorCp(String cp) {
		String sql = "SELECT colonia.id, colonia.nombre, colonia.cp, municipio.nombre, estado.nombre "
				+ "FROM colonia "
				+ "INNER JOIN municipio ON colonia.id_municipio = municipio.id "
				+ "INNER JOIN estado ON municipio.id_estado = estado.id WHERE colonia.cp = ?;";
		
		return jdbcTemplate.query(sql, this::mapRowToColonia, cp);		
	}
	
}
