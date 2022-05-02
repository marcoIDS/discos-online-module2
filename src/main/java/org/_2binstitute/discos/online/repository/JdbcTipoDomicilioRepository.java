package org._2binstitute.discos.online.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import org._2binstitute.discos.online.domain.TipoDomicilio;

public class JdbcTipoDomicilioRepository implements TipoDomicilioRepository {

	private DataSource dataSource;
	
	public JdbcTipoDomicilioRepository(DataSource dataSource) {
		this.dataSource = dataSource; 
	}
	
	@Override
	public TipoDomicilio buscarPorDescripcion(String descripcion) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TipoDomicilio tipoDomicilio = null;
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT id, descripcion FROM tipo_domicilio WHERE descripcion  = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, descripcion);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				tipoDomicilio = new TipoDomicilio();
				tipoDomicilio.setId(rs.getInt("id"));
				tipoDomicilio.setDescripcion(rs.getString("descripcion"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rs != null) {rs.close();}} catch (Exception e) {}
			try {if (pstmt != null) {pstmt.close();}} catch (Exception e) {}
			try {if (conn != null) {conn.close();}} catch (Exception e) {}
		}
		
		return tipoDomicilio;
	}
	
}
