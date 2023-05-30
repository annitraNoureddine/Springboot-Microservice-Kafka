package com.pmu.course.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "race")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @NotEmpty
    private String nom;

    @NotNull
    @Min(3)
    private Integer participantCount;

    @NotEmpty
    private String date;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_course_id", referencedColumnName = "course_id")
    @JsonManagedReference
    private Set<Horse> horses= new HashSet<>();


    // Constructeur par défaut

    public Course() {
    }

    // Constructeur avec paramètres

    public Course(Long courseId, String nom, Integer participantCount, String date, Set<Horse> horses) {
        this.courseId = courseId;
        this.nom = nom;
        this.participantCount = participantCount;
        this.date = date;
        this.horses = horses;
    }


    //getters et setters

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getParticipantCount() {
        return participantCount;
    }

    public void setParticipantCount(Integer participantCount) {
        this.participantCount = participantCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Set<Horse> getHorses() {
        return horses;
    }

    public void setHorses(Set<Horse> horses) {
        this.horses = horses;
    }

    //toString

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", nom='" + nom + '\'' +
                ", participantCount=" + participantCount +
                ", date=" + date +
                ", horses=" + horses +
                '}';
    }

}
