package com.pmu.course.controller;


import com.pmu.course.dto.CourseDto;
import com.pmu.course.dto.HorseDto;
import com.pmu.course.response.CourseResponse;
import com.pmu.course.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/courses")
public class CourseController {


    private final CourseService courseService;

       public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/")
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseDto courseDto) {

        System.out.println(courseDto.toString());
        CourseResponse response = courseService.createCourse(courseDto);

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<CourseDto> getExampleCourseDto() {
        CourseDto exampleCourseDto = new CourseDto();
        // Définir les valeurs de l'exemple de CourseDto
        exampleCourseDto.setNom("Course 1x");
        exampleCourseDto.setParticipantCount(3);
        exampleCourseDto.setDateCourse("2023/05/29");
        Set<HorseDto> horses = new HashSet<>();
        HorseDto horse1 = new HorseDto("Horse 1", Integer.valueOf(1));
        HorseDto horse2 = new HorseDto("Horse 2", Integer.valueOf(2));
        HorseDto horse3 = new HorseDto("Horse 3", Integer.valueOf(3));
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        exampleCourseDto.setHorse(horses);


        return ResponseEntity.ok(exampleCourseDto);
    }

}
