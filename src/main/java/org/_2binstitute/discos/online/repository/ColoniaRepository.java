package org._2binstitute.discos.online.repository;
import java.util.List;
import org._2binstitute.discos.online.domain.Colonia;

public interface ColoniaRepository {
	
	List<Colonia> buscarPorCp(String cp);
	
}
