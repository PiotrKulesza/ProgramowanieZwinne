package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Project;
import reactor.core.publisher.Flux;

public interface ProjectService {

    Flux<Project> getProjectsByLecturer(Integer lecturerId);

}
