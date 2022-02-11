package co.com.poli.pds.proyectos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.poli.pds.proyectos.entity.BackLog;
import co.com.poli.pds.proyectos.entity.Project;

@Repository("iProjectRepository")
public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	List<Project> findByProjectIdentifier(String projectIdentifier);
	List<Project> findByProjectName(String projectName);

}