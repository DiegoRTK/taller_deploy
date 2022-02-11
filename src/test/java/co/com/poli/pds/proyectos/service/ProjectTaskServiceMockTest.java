package co.com.poli.pds.proyectos.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import co.com.poli.pds.proyectos.entity.BackLog;
import co.com.poli.pds.proyectos.entity.ProjectTask;
import co.com.poli.pds.proyectos.repository.ProjectTaskRepository;

@SpringBootTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProjectTaskServiceMockTest {
	
	@Mock
	private ProjectTaskRepository projectTaskRepository;
	
	
	private ProjectTaskService projectTaskService;
	
	
	@BeforeEach
	public void begin() {
		MockitoAnnotations.openMocks(this);
		projectTaskService = new ProjectTaskServiceImpl(projectTaskRepository);
		
		ProjectTask project = ProjectTask.builder()
				.id(30L)
				.name("Tarea programada")
				.acceptanceCriteria("Criterios de busqueda")
				.status("Not Started")
				.priority(3)
				.hours(5.0)
				.startDate(new Date())
				.endDate(new Date())
				.projectIdentifier("TEST1")
				.backLog(BackLog.builder().id(1L).build())
				.build();
		Mockito.when(projectTaskRepository.findByProjectIdentifier("TEST1")).thenReturn((List<ProjectTask>) project);
		
	}
	
	@Test
	public void when_changeStatusTask_return_true() {
		boolean project = projectTaskService.changeStatusTask(7L, "PPI");
		Assertions.assertThat(project).isEqualTo(true);
		
	}
	
}