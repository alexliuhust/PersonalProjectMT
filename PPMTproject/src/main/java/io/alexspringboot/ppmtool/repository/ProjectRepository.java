package io.alexspringboot.ppmtool.repository;

import io.alexspringboot.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    Iterable<Project> findAll();

    Iterable<Project> findAllByProjectLeader(String username);

    public Project findProjectByProjectIdentifier(String projectId);

}
