package co.com.poli.pds.proyectos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import co.com.poli.pds.proyectos.entity.BackLog;
import co.com.poli.pds.proyectos.entity.Project;
import co.com.poli.pds.proyectos.entity.ProjectTask;
import co.com.poli.pds.proyectos.repository.BackLogRepository;
import co.com.poli.pds.proyectos.repository.ProjectRepository;
import co.com.poli.pds.proyectos.repository.ProjectTaskRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@CrossOrigin // Para manejar las solicitudes cruzadas que provienen del navegador del cliente
public class BackLogServiceImpl implements BackLogService {

	@Autowired
	private BackLogRepository backLogRepository;

	@Autowired
	private ProjectTaskRepository projectTaskRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public int save(BackLog backlog) {
		int cont = 0;
		if (verificarIngesta(backlog)) {
			List<BackLog> backlogsAll = backLogRepository.findAll();
			for (BackLog backlogValid : backlogsAll) {
				System.out.print(backlogValid.getProject().getId());
				System.out.print(backlog.getProject().getId());
				if (backlogValid.getIdentifier().toUpperCase().equals(backlog.getIdentifier().toUpperCase())
						|| backlogValid.getProject().getId() == backlog.getProject().getId()) {
					cont = cont + 1;
				}
			}
		}
		cont = 1;
		return cont;
	}

	@Override
	public void delete(BackLog backlog) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BackLog> findAll() {
		return backLogRepository.findAll();

	}

	@Override
	@Transactional(readOnly = true)
	public BackLog findById(Long id) {
		return backLogRepository.findById(id).orElse(null);
	}

	@Override
	public boolean verificarIngesta(BackLog newBackLog) {
		if (newBackLog.getIdentifier().equals("") || newBackLog.getProject().getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}