package co.com.poli.pds.proyectos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.poli.pds.proyectos.entity.ProjectTask;
import co.com.poli.pds.proyectos.helper.ResponseBuilder;
import co.com.poli.pds.proyectos.model.Response;
import co.com.poli.pds.proyectos.repository.ProjectTaskRepository;
import co.com.poli.pds.proyectos.service.ProjectTaskService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class ProjectTaskController {

	private final ResponseBuilder builder;
	private final ProjectTaskService projectTaskService;
	
	private ProjectTaskRepository repository;

	@PostMapping
	public Response createTask(@RequestBody ProjectTask newTask) {
			boolean flag =projectTaskService.createTask(newTask);
			if(flag) {
				return builder.success(newTask);
			}else {
				return builder.failed(newTask);
			}
		  
	}
	
	@GetMapping("/identifier/{projectIdentifier}")
	public List<ProjectTask> findAllProjectIdentifier(@PathVariable("projectIdentifier") String project){
		return repository.findByProjectIdentifier(project);
	}
	@GetMapping
	public List<ProjectTask> findAll() {
		return projectTaskService.findAll();
	}
	
	@GetMapping("hours/project/{projectIdentifier}")
	public Double allHoursProject(@PathVariable("projectIdentifier") String projectIdentifier) {
		return projectTaskService.allHoursProject(projectIdentifier);
	}

	@GetMapping("hours/project/{projectIdentifier}/{status}")
	public Double AllHoursxStatus(@PathVariable("projectIdentifier") String projectIdentifier, @PathVariable("status") String status) {
		return projectTaskService.AllHoursxStatus(projectIdentifier, status);
	}

	@PutMapping("/{idtask}/{projectIdentifier}")
	public Response changeStatusTask(@PathVariable("idtask") Long idTask, @PathVariable("projectIdentifier") String projectIdentifier) {
		boolean flag = projectTaskService.changeStatusTask(idTask, projectIdentifier);
		if(flag) {
			return builder.changeStatus();
		}else {
			return builder.failed();
		}
	}

}