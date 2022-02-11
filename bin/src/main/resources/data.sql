INSERT INTO projects (id, projectName, projectIdentifier, description, startDate, endDate) VALUES ( 1, 'Proyecto1', 'PPI', 'Primer proyecto de prueba', null, null);

INSERT INTO backlogs (id, identifier, project_id) VALUES (1, 1, 1);

INSERT INTO projectTask (id, name, sumary, acceptanceCriteria, status, priority, hours, startDate, endDate, projectIdentifier, backLog_id) VALUES (1, 'Tarea1', 'proyecto de prueba', 'Que el proyecto se guarde','Not Started', 3, 7.0, null, null, 'PPI', 1);