package com.project.projectsmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
public class CorsConfig implements WebFluxConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/getStudentByLogin")
                .allowedOrigins("*")
                .allowedMethods("GET")
                .maxAge(3600);
        corsRegistry.addMapping("/getLecturerByLogin")
                .allowedOrigins("*")
                .allowedMethods("GET")
                .maxAge(3600);
        corsRegistry.addMapping("/lecturerProjects/{id}")
                .allowedOrigins("*")
                .allowedMethods("GET")
                .maxAge(3600);
        corsRegistry.addMapping("/lecturer/{id}")
                .allowedOrigins("*")
                .allowedMethods("GET")
                .maxAge(3600);
        corsRegistry.addMapping("/students")
                .allowedOrigins("*")
                .allowedMethods("GET")
                .maxAge(3600);
        corsRegistry.addMapping("/getLoginByEmail")
                .allowedOrigins("*")
                .allowedMethods("GET")
                .maxAge(3600);
        corsRegistry.addMapping("/getStudentsByProject")
                .allowedOrigins("*")
                .allowedMethods("GET")
                .maxAge(3600);
        corsRegistry.addMapping("/getStudentByNrIndeksu")
                .allowedOrigins("*")
                .allowedMethods("GET")
                .maxAge(3600);
        corsRegistry.addMapping("/getStudentInProject")
                .allowedOrigins("*")
                .allowedMethods("GET")
                .maxAge(3600);
        corsRegistry.addMapping("/getTaskByProject")
                .allowedOrigins("*")
                .allowedMethods("GET")
                .maxAge(3600);
        corsRegistry.addMapping("/updateLecturer/{id}")
                .allowedOrigins("*")
                .allowedMethods("PUT")
                .maxAge(3600);
        corsRegistry.addMapping("/loginUpdate")
                .allowedOrigins("*")
                .allowedMethods("PUT")
                .maxAge(3600);
        corsRegistry.addMapping("/addProject")
                .allowedOrigins("*")
                .allowedMethods("POST")
                .maxAge(3600);
        corsRegistry.addMapping("/postLogin")
                .allowedOrigins("*")
                .allowedMethods("POST")
                .maxAge(3600);
        corsRegistry.addMapping("/postStudent")
                .allowedOrigins("*")
                .allowedMethods("POST")
                .maxAge(3600);
        corsRegistry.addMapping("/postLecturer")
                .allowedOrigins("*")
                .allowedMethods("POST")
                .maxAge(3600);
        corsRegistry.addMapping("/addStudentToProject")
                .allowedOrigins("*")
                .allowedMethods("POST")
                .maxAge(3600);


    }
}