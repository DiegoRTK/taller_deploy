package co.com.poli.pds.proyectos.repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import co.com.poli.pds.proyectos.entity.BackLog;
import co.com.poli.pds.proyectos.entity.ProjectTask;
import co.com.poli.pds.proyectos.repository.BackLogRepository;
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
										.projectIdentifier("RFT")
										.backLog(BackLog.builder().id(1L).build())
										.build();
		projectTaskRepository.save(project);
		
		List<ProjectTask> projectxIdentifier = projectTaskRepository.findByProjectIdentifier(project.getProjectIdentifier());
		
		Assertions.assertThat(projectxIdentifier.size()).isEqualTo(5);
	}
	
}
