package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Lecturer;
import com.project.projectsmanagement.repositories.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LecturerServiceImpl implements LecturerService{

    private LecturerRepository lecturerRepository;

    @Autowired
    public LecturerServiceImpl(LecturerRepository lecturerRepository){
        this.lecturerRepository=lecturerRepository;
    }

    @Override
    public Lecturer newLecturer(Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }
}
