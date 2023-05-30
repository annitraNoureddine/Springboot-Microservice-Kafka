package com.pmu.course.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmu.course.dto.CourseDto;
import com.pmu.course.dto.HorseDto;
import com.pmu.course.exception.CourseException;
import com.pmu.course.kafka.KafkaMessageProducer;
import com.pmu.course.model.Course;
import com.pmu.course.response.CourseResponse;
import com.pmu.course.model.Horse;
import com.pmu.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.HashSet;
import java.util.Set;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final KafkaMessageProducer kafkaMessageProducer;
    private final ObjectMapper objectMapper;


    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, KafkaMessageProducer kafkaMessageProducer, ObjectMapper objectMapper) {
        this.courseRepository = courseRepository;
        this.kafkaMessageProducer = kafkaMessageProducer;
        this.objectMapper = objectMapper;
    }

    public CourseResponse createCourse(CourseDto courseDto) {
        CourseResponse response = new CourseResponse();

        try {
            Course course=createMappingCourse(courseDto);
            validateCourse(course);
            validateDateFormat(course.getDate());
            if (!verifierNumerosRace(course)) {
                response.setSuccess(false);
                response.setMessage("Invalid horse numbers in the course.");
                return response;
                //throw new CourseException("Invalid horse numbers in the course.");
            } else {
                courseRepository.save(course);
                response.setSuccess(true);
                response.setMessage("Course created successfully");
                // Convertir l'objet courseDto en JSON
                String jsonCourseDto = convertToJson(courseDto);

                // Publier le message JSON dans Kafka si aucune exception n'est levée
                kafkaMessageProducer.publishMessage(jsonCourseDto);
            }

        } catch (CourseException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            //throw e; // Rethrow the exception
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("An error occurred while creating the course");
            //throw new CourseException("An error occurred while creating the course.");
        }

        return response;

    }

    public void validateCourse(Course course) {
        // Validation logic for the course
        if (course.getParticipantCount() < 3 || course.getHorses().size() <3) {
            throw new CourseException("A course must have at least 3 participants.");
        }

    }

    public boolean verifierNumerosRace(Course course) {
        HashSet<Integer> numerosSet = new HashSet<>();

        boolean hasNumberOne = course.getHorses().stream()
                .map(Horse::getNumero)
                .anyMatch(numero -> numero == 1);

        boolean isAllValid = course.getHorses().stream()
                .map(Horse::getNumero)
                .allMatch(numero -> {
                    // Vérifier s'il y a un doublon
                    if (numerosSet.contains(numero)) {
                        return false; // Doublon détecté
                    }

                    numerosSet.add(numero);
                    return true;
                });

        boolean hasNoMissingNumbers = numerosSet.stream()
                .allMatch(numero -> numero <= numerosSet.size());

        return hasNumberOne && isAllValid && hasNoMissingNumbers;
    }

    public void validateDateFormat(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        try {
            LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new CourseException("Invalid date format. Expected format: yyyy/MM/dd");
        }
    }

    private String convertToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public Course createMappingCourse(CourseDto  courseDto){
        Course  course=new Course();
        course.setNom(courseDto.getNom());
        course.setParticipantCount(courseDto.getParticipantCount());
        course.setDate(courseDto.getDateCourse());

        Set<Horse> horseSet=new HashSet<>();
        //Set<Horse> horseAll=new HashSet<>();
        for (HorseDto horseDto : courseDto.getHorse()) {
            Horse horse=new Horse();
            horse.setNumero(horseDto.getNumero());
            horse.setNom(horseDto.getNom());
            horseSet.add(horse);
        }
        //horseAll.addAll(horseSet);
        course.setHorses(horseSet);

       return course;
    }
}
