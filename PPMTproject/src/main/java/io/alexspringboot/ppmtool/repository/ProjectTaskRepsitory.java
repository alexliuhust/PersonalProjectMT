package io.alexspringboot.ppmtool.repository;

import io.alexspringboot.ppmtool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTaskRepsitory extends CrudRepository<ProjectTask, Long> {
}
