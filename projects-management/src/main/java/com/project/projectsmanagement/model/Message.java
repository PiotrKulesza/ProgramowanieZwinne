package com.project.projectsmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "message")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue
    @Column(name="messageid")
    private Long messaged;

    @Column(nullable = false, length = 1000)
    private String text;

    @ManyToOne
    @JoinColumn(name = "student_id_nad")
    private Student sender;

    @ManyToOne
    @JoinColumn(name = "student_id_ad")
    private Student receiver;

}