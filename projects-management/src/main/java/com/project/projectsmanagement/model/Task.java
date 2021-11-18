package com.project.projectsmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="zadanie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue
    @Column(name="task_id")
    private Integer taskId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToOne
    private Student student;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(length = 1000)
    private String odescriptionpis;

    @Column(nullable = false, name = "dataczas_dodania")
    private LocalDateTime dataczasDodania;

    @Column(name = "kolejnosc")
    private Integer kolejnosc;

}
