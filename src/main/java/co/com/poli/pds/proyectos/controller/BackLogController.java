package co.com.poli.pds.proyectos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.poli.pds.proyectos.entity.BackLog;
import co.com.poli.pds.proyectos.helper.ResponseBuilder;
import co.com.poli.pds.proyectos.model.Response;
import co.com.poli.pds.proyectos.repository.BackLogRepository;
import co.com.poli.pds.proyectos.repository.ProjectRepository;
import co.com.poli.pds.proyectos.service.BackLogService;
import co.com.poli.pds.proyectos.service.ProjectService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/backlogs")
@RequiredArgsConstructor
public class BackLogController {
	
	private final BackLogService backLogService;
	private final BackLogRepository backlogRepository;
	
	private final ResponseBuilder builder;
	
	@PostMapping
	public Response createBackLog(@RequestBody BackLog newBackLog){
		int flag = backLogService.save(newBackLog);
		System.out.print(flag);
		if(flag == 0) {
			backlogRepository.save(newBackLog);
			return builder.success(newBackLog);
		}else {
			return builder.failed();
		}
	}
	
	
	@GetMapping("/{idBack}")
	public Response findById(@PathVariable("idBack") Long id) {
		BackLog back = backLogService.findById(id);
		if(back == null) {
			return builder.failed();
		}
		return builder.success(back);
	}
}