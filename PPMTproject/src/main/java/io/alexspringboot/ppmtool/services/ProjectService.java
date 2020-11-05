package io.alexspringboot.ppmtool.services;

import io.alexspringboot.ppmtool.domain.Project;
import io.alexspringboot.ppmtool.exceptions.ProjectIdException;
import io.alexspringboot.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException(
                    "Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId) {
        Project project = projectRepository.findProjectByIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException(
                    "Project ID '" + projectId.toUpperCase() + "' does NOT exist");
        }
        return project;
    }
}










