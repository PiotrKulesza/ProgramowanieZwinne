package com.project.projectsmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "project")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {


    @Id
    @GeneratedValue
    @Column(name = "project_id")
    private Integer projectId;

    @Column(nullable = false, length = 50)
    private String nazwa;

    @Column(nullable = false, length = 1000)
    private String opis;

    @Column(nullable = false, name = "dataczas_utworzenia")
    private LocalDateTime dataczasUtworzenia;

    @Column(nullable = false, name = "data_oddania")
    private LocalDate dataOddania;

    @OneToMany(mappedBy = "project")
    @JsonIgnoreProperties(value ={"project"}, allowSetters = true)
    private List<Task> tasks;

    @ManyToMany
    @JoinTable(name = "projekt_student",
            joinColumns = {@JoinColumn(name="projekt_id")},
            inverseJoinColumns = {@JoinColumn(name="student_id")})
    private Set<Student> studenci;

}

