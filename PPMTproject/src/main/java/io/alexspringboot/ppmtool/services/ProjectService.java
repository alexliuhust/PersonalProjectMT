package io.alexspringboot.ppmtool.services;

import io.alexspringboot.ppmtool.domain.Backlog;
import io.alexspringboot.ppmtool.domain.Project;
import io.alexspringboot.ppmtool.exceptions.ProjectIdException;
import io.alexspringboot.ppmtool.repository.BacklogRepository;
import io.alexspringboot.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    public Project saveOrUpdateProject(Project project) {
        try {
            String identifier = project.getProjectIdentifier().toUpperCase();

            project.setProjectIdentifier(identifier);
            // When we are CREATING a project, this project has no id, yet
            if (project.getId() == null) {
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(identifier);
            }

            // When we are UPDATING a project, this project must have an id already
            if (project.getId() != null) {
                project.setBacklog(backlogRepository.findBacklogByProjectIdentifier(identifier));
            }

            // Let the database to store this valid project
            return projectRepository.save(project);

        } catch (Exception e) {
            throw new ProjectIdException(
                    "Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId) {
        Project project = projectRepository.findProjectByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException(
                    "Project ID '" + projectId.toUpperCase() + "' does NOT exist");
        }
        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findProjectByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException(
                    "Project ID '" + projectId.toUpperCase() + "' does NOT exist.\nCannot delete it.");
        }
        projectRepository.delete(project);
    }
}










