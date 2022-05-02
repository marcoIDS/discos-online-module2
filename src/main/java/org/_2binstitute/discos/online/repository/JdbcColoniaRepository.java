package org._2binstitute.discos.online.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org._2binstitute.discos.online.domain.Colonia;
import org._2binstitute.discos.online.domain.Estado;
import org._2binstitute.discos.online.domain.Municipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcColoniaRepository implements ColoniaRepository{
	private DataSource dataSource;
	
	@Autowired
	public JdbcColoniaRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Colonia> buscarPorCp(String cp) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Colonia> colonias = new ArrayList<>();
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT colonia.id, colonia.nombre, colonia.cp, municipio.nombre, estado.nombre "
					+ "FROM colonia "
					+ "INNER JOIN municipio ON colonia.id_municipio = municipio.id "
					+ "INNER JOIN estado ON municipio.id_estado = estado.id WHERE colonia.cp = ?;";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cp);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
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
				
				colonias.add(colonia);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rs != null) {rs.close();}} catch (Exception e) {}
			try {if (pstmt != null) {pstmt.close();}} catch (Exception e) {}
			try {if (conn != null) {conn.close();}} catch (Exception e) {}
		}
		
		return colonias;
	}
	
}
