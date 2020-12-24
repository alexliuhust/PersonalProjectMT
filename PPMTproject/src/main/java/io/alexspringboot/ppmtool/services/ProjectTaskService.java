package io.alexspringboot.ppmtool.services;

import io.alexspringboot.ppmtool.domain.Backlog;
import io.alexspringboot.ppmtool.domain.ProjectTask;
import io.alexspringboot.ppmtool.repository.BacklogRepository;
import io.alexspringboot.ppmtool.repository.ProjectTaskRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepsitory projectTaskRepsitory;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
        // Exception: Project Not Found

        // ProjectTsk to be added to a specific project, where this project != null
        Backlog backlog = backlogRepository.findBacklogByProjectIdentifier(projectIdentifier);
        // Set the backlog to projectTask
        projectTask.setBacklog(backlog);
        // We want out project sequence to look like this: A0023-1, A0023-2, ...(100), A0023-101,
        Integer backlogSequence = backlog.getPTSequence();
        // Update the backlog PTSequence
        backlogSequence++;
        backlog.setPTSequence(backlogSequence);
        // Add sequence to projectTask
        projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);
        // Initial priority if null
        if (projectTask.getPriority() == null || projectTask.getPriority() == 0) {
            projectTask.setPriority(3);
        }
        // Initial status if null
        if (projectTask.getStatus() == null || projectTask.getStatus() == "") {
            projectTask.setStatus("TO_DO");
        }

        return projectTaskRepsitory.save(projectTask);
    }

    public Iterable<ProjectTask> findBacklogById(String backlog_id) {
        return projectTaskRepsitory.findByProjectIdentifierOrderByPriority(backlog_id);
    }
}