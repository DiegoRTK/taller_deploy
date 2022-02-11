package co.com.poli.pds.proyectos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.poli.pds.proyectos.entity.ProjectTask;

@Repository("iProjectTaskRepository")
public interface ProjectTaskRepository extends JpaRepository<ProjectTask, Long> {
	
	List<ProjectTask> findByProjectIdentifier(String Ididentifier);
	
	
}
