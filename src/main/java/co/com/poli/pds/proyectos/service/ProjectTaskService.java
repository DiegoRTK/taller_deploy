package co.com.poli.pds.proyectos.service;

import java.util.List;

import co.com.poli.pds.proyectos.entity.ProjectTask;
import co.com.poli.pds.proyectos.model.Response;

public interface ProjectTaskService {
	
	boolean createTask(ProjectTask newTask);
	
	List<ProjectTask> viewAllTaskProject(String projectIdentifier);
	
	Double allHoursProject(String projectIdentifier);
	
	Double AllHoursxStatus(String projectIdentifier, String status);
	
	boolean changeStatusTask(Long idTask, String projectIdentifier);
	
	List<ProjectTask> findAll(); 
}