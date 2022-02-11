package co.com.poli.pds.proyectos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import co.com.poli.pds.proyectos.entity.ProjectTask;
import co.com.poli.pds.proyectos.helper.ResponseBuilder;
import co.com.poli.pds.proyectos.model.Response;
import co.com.poli.pds.proyectos.repository.ProjectTaskRepository;
import lombok.RequiredArgsConstructor;

@Service
@CrossOrigin //Para manejar las solicitudes cruzadas que provienen del navegador del cliente
@RequiredArgsConstructor
public class ProjectTaskServiceImpl implements ProjectTaskService{
private  ResponseBuilder builder;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean createTask(ProjectTask newTask) {
			if(this.verificarIngesta(newTask) && !this.verificarStatus(newTask.getStatus())) {
				projectTaskRepository.save(newTask); 
				return true;
			}else {
				return false;
			}
	}


	@Override
	@Transactional(readOnly = true)
	public Double allHoursProject(String projectIdentifier) {
			List<ProjectTask> projects = projectTaskRepository.findByProjectIdentifier(projectIdentifier);
			Double contFlag = 0D;
			for(ProjectTask projectTaskIdentifier : projects) {
				if(!projectTaskIdentifier.getStatus().equals("deleted")) {
					Double contHoras = projectTaskIdentifier.getHours();
					contFlag = contFlag + contHoras;
				}
			}
			
		return contFlag;
	}

	@Override
	@Transactional(readOnly = true)
	public Double AllHoursxStatus(String projectIdentifier, String status) {
		List<ProjectTask> projectTaskList = projectTaskRepository.findByProjectIdentifier(projectIdentifier);
		Double contTasks = 0D, contFlag = 0D;
		if(!this.verificarStatus(status)) {
			for(ProjectTask tasks : projectTaskList) {
				if(tasks.getStatus().equals(status)) {
					contTasks = tasks.getHours();
					contFlag = contFlag +  contTasks;
				}
			}
		}
		return contFlag;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ProjectTask> viewAllTaskProject(String projectIdentifier) {
		return projectTaskRepository.findByProjectIdentifier(projectIdentifier);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProjectTask> findAll() {
		return projectTaskRepository.findAll();
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean changeStatusTask(Long idTask,String projectIdentifier) {
		Optional<ProjectTask> borradoLogico = projectTaskRepository.findById(idTask);
		
		if(borradoLogico.isPresent() && borradoLogico.get().getProjectIdentifier().equals(projectIdentifier)) {
			borradoLogico.get().setStatus("deleted");
			return true;
		}else {
			return false;
		}
	}
	
	private boolean verificarIngesta(ProjectTask validate) {
		if(validate.getName() == "" || validate.getProjectIdentifier() == "" || validate.getSumary() == "" ||
				validate.getStatus() == "" ) {
			return false;
		}
		
		if(validate.getPriority()>= 1 && validate.getPriority() <= 5 && validate.getHours() >=1D && validate.getHours() <= 8D){
			return true;
		}else {
			return false;
		}
		
	}
	
	private boolean verificarStatus(String nameStatus) {
		if(nameStatus != "Not Started" || nameStatus != "in progress" || nameStatus != "completed" || nameStatus != "deleted") {
			return false;
		}else {
			return true;
		}
	}


	public ProjectTaskServiceImpl(ProjectTaskRepository projectTaskRepository2) {
		// TODO Auto-generated constructor stub
	}
}