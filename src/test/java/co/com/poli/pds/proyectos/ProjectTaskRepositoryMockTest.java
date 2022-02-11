package co.com.poli.pds.proyectos;

import java.util.Date;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import co.com.poli.pds.proyectos.entity.BackLog;
import co.com.poli.pds.proyectos.entity.ProjectTask;
import co.com.poli.pds.proyectos.repository.ProjectTaskRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProjectTaskRepositoryMockTest {
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	@Test
	public void when_findByProjectIdentifier_return_ListProjectIdentifier() {
		
		ProjectTask project = ProjectTask.builder()
										.name("Tarea programada")
										.acceptanceCriteria("Criterios de busqueda")
										.status("Not Started")
										.priority(3)
										.hours(5.0)
										.startDate(new Date())
										.endDate(new Date())
										.projectIdentifier("PPI")
										.backLog(BackLog.builder().id(1L).build())
										.build();
		
		List<ProjectTask> projectxIdentifier = projectTaskRepository.findByProjectIdentifier(project.getProjectIdentifier());
		
		Assertions.assertThat(projectxIdentifier.size()).isEqualTo(2);
	}
	
}
