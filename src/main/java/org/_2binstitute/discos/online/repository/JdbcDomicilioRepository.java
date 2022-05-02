package org._2binstitute.discos.online.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;
import org._2binstitute.discos.online.domain.Domicilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcDomicilioRepository implements DomicilioRepository{
	private DataSource dataSource;
	
	@Autowired
	public JdbcDomicilioRepository(DataSource dataSource) {
		this.dataSource = dataSource; 
	}
	
	@Override
	public Domicilio insertar(Domicilio domicilio) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO domicilio(calle, num_exterior, num_interior, "
					+ "id_usuario, id_colonia, id_tipo_domicilio) VALUES (?, ?, ?, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, domicilio.getCalle());
			pstmt.setString(2, domicilio.getNumExterior());
			pstmt.setString(3, domicilio.getNumInterior());
			pstmt.setInt(4, domicilio.getUsuario().getId());
			pstmt.setInt(5, domicilio.getColonia().getId());
			pstmt.setInt(6, domicilio.getTipoDomicilio().getId());
			
			pstmt.executeUpdate();	
			rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				domicilio.setId(rs.getInt(1));
			}
			
			return domicilio;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rs != null) {rs.close();}} catch (Exception e) {}
			try {if (pstmt != null) {pstmt.close();}} catch (Exception e) {}
			try {if (conn != null) {conn.close();}} catch (Exception e) {}
		}		
		return null;
	}

}
