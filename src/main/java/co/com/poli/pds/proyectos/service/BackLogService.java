package co.com.poli.pds.proyectos.service;

import java.util.List;

import co.com.poli.pds.proyectos.entity.BackLog;

public interface BackLogService {
	
//	ResponseEntity<BackLog> createBackLog(BackLog newBackLog);
//	List<BackLog> consultarBackLog();
	
	int save(BackLog backlog);
    void delete(BackLog backlog);
    List<BackLog> findAll();
    BackLog findById(Long id);
    boolean verificarIngesta(BackLog newBackLog);
	
}
