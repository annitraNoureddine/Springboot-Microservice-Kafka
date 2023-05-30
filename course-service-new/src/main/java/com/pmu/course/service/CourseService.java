package com.pmu.course.service;

import com.pmu.course.dto.CourseDto;
import com.pmu.course.model.Course;
import com.pmu.course.response.CourseResponse;

public interface CourseService {

    CourseResponse createCourse(CourseDto course);

    void validateCourse(Course course);

    boolean verifierNumerosRace(Course course);
}
