package com.pmu.course.controller;

import com.pmu.course.dto.CourseDto;
import com.pmu.course.dto.HorseDto;
import com.pmu.course.response.CourseResponse;
import com.pmu.course.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCourse_ValidCourseDto_ReturnsCreatedResponse() {
        CourseDto courseDto = new CourseDto();
        // Définir les valeurs du CourseDto

        CourseResponse response = new CourseResponse();
        response.setSuccess(true);
        response.setMessage("Course created successfully");

        when(courseService.createCourse(courseDto)).thenReturn(response);

        ResponseEntity<CourseResponse> expectedResponse = new ResponseEntity<>(response, HttpStatus.CREATED);
        ResponseEntity<CourseResponse> actualResponse = courseController.createCourse(courseDto);

        assertEquals(expectedResponse, actualResponse);
        verify(courseService, times(1)).createCourse(courseDto);
    }

    @Test
    void createCourse_InvalidCourseDto_ReturnsBadRequestResponse() {
        CourseDto courseDto = new CourseDto();
        // Définir les valeurs du CourseDto

        CourseResponse response = new CourseResponse();
        response.setSuccess(false);
        response.setMessage("Invalid course");

        when(courseService.createCourse(courseDto)).thenReturn(response);

        ResponseEntity<CourseResponse> expectedResponse = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        ResponseEntity<CourseResponse> actualResponse = courseController.createCourse(courseDto);

        assertEquals(expectedResponse, actualResponse);
        verify(courseService, times(1)).createCourse(courseDto);
    }


}