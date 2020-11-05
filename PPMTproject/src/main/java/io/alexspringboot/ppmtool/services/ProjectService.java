package io.alexspringboot.ppmtool.services;

import io.alexspringboot.ppmtool.domain.Project;
import io.alexspringboot.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {

        return projectRepository.save(project);
    }
}
