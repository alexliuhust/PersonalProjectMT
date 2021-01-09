package io.alexspringboot.ppmtool.services;

import io.alexspringboot.ppmtool.domain.Backlog;
import io.alexspringboot.ppmtool.domain.ProjectTask;
import io.alexspringboot.ppmtool.exceptions.ProjectNotFound;
import io.alexspringboot.ppmtool.repository.BacklogRepository;
import io.alexspringboot.ppmtool.repository.ProjectRepository;
import io.alexspringboot.ppmtool.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String username) {

        // This method from projectService will handle the possible exceptions
        Backlog backlog = projectService.findProjectByIdentifier(projectIdentifier, username).getBacklog();

        // ProjectTsk to be added to a specific project, where this project != null
        // Set the backlog to projectTask
        projectTask.setBacklog(backlog);
        // We want out project sequence to look like this: A0023-1, A0023-2, ...(100), A0023-101,
        Integer backlogSequence = backlog.getPTSequence();
        // Update the backlog PTSequence
        backlogSequence++;
        backlog.setPTSequence(backlogSequence);
        // Add sequence to projectTask
        projectTask.setProjectSequence(projectIdentifier.toUpperCase() + "-" + backlogSequence);
        projectTask.setProjectIdentifier(projectIdentifier.toUpperCase());

        // Initial priority if null
        if (projectTask.getPriority() == null || projectTask.getPriority() == 0) {
            projectTask.setPriority(3);
        }
        // Initial status if null
        if (projectTask.getStatus() == null || projectTask.getStatus() == "") {
            projectTask.setStatus("TO_DO");
        }

        return projectTaskRepository.save(projectTask);
    }

    public Iterable<ProjectTask> findBacklogById(String backlog_id, String username) {
        // Check whether the project exists or belongs to the user
        projectService.findProjectByIdentifier(backlog_id, username);

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
    }

    public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id, String username) {

        // ========================= URL Validation Starts =========================
        // Check whether the backlog exists or belongs to the user
        projectService.findProjectByIdentifier(backlog_id, username);

        // Make sure the projectTask exist;
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id.toUpperCase());
        if (projectTask == null) {
            throw new ProjectNotFound(
                    "Project Task with ID '" + pt_id.toUpperCase() + "' NOT found");
        }

        // Make sure the backlog, project, and projectTask match each other;
        if (!projectTask.getProjectIdentifier().equals(backlog_id.toUpperCase())) {
            throw new ProjectNotFound(
                    "Project Task with ID '" + pt_id.toUpperCase() + "' " +
                    "NOT exist in the project '" + backlog_id.toUpperCase() + "'. " +
                    "What are you playing at?");
        }
        // ========================== URL Validation Ends ==========================

        return projectTask;
    }

    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_id, String username) {
        // Use findPTByProjectSequence to help us do the URL validation
        ProjectTask projectTask = findPTByProjectSequence(backlog_id,pt_id, username);
        projectTask = updatedTask;
        projectTask.setProjectIdentifier(updatedTask.getProjectIdentifier().toUpperCase());
        projectTask.setProjectSequence(updatedTask.getProjectSequence().toUpperCase());
        return projectTaskRepository.save(projectTask);
    }

    public void deleteByProjectSequence(String backlog_id, String pt_id, String username) {
        // Use findPTByProjectSequence to help us do the URL validation
        ProjectTask projectTask = findPTByProjectSequence(backlog_id,pt_id,username);

        projectTaskRepository.delete(projectTask);
    }
}
