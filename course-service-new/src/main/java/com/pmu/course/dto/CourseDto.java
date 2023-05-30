package com.pmu.course.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CourseDto implements Serializable {
    private String nom;
    private int participantCount;
    private String dateCourse;
    private Set<HorseDto> horse = new HashSet<>();
    public int getParticipantCount() {
        return participantCount;
    }

    public void setParticipantCount(int participantCount) {
        this.participantCount = participantCount;
    }

    public String getDateCourse() {
        return dateCourse;
    }

    public void setDateCourse(String dateCourse) {
        this.dateCourse = dateCourse;
    }

    public Set<HorseDto> getHorse() {
        return horse;
    }

    public void setHorse(Set<HorseDto> horse) {
        this.horse = horse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CourseDto{")
                .append("nom='").append(nom).append('\'')
                .append(", participantCount=").append(participantCount)
                .append(", dateCourse='").append(dateCourse).append('\'')
                .append(", horse=[");

        for (HorseDto horseDto : horse) {
            stringBuilder.append(horseDto.toString()).append(", ");
        }

        if (!horse.isEmpty()) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()); // Supprimer la virgule et l'espace après le dernier élément
        }

        stringBuilder.append("]}");

        return stringBuilder.toString();
    }
}
