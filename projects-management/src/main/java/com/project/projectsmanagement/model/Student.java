package com.project.projectsmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    @Column(name="student_id")
    private Integer studentId;

    @Column(nullable = false, length = 50)
    private String imie;

    @Column(nullable = false, length = 100)
    private String nazwisko;

    @Column(unique=true, nullable = false, length = 50, name = "nr_indeksu")
    private String nrIndeksu;

    @Column(nullable = false)
    private boolean stacjonarny;

    @ManyToMany(mappedBy = "studenci")
    @JsonIgnore
    private Set<Project> projekty;

    @OneToOne
    private Login login;



}