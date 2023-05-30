package com.pmu.course.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmu.course.dto.CourseDto;
import com.pmu.course.dto.HorseDto;
import com.pmu.course.exception.CourseException;
import com.pmu.course.kafka.KafkaMessageProducer;
import com.pmu.course.model.Course;
import com.pmu.course.model.Horse;
import com.pmu.course.repository.CourseRepository;
import com.pmu.course.response.CourseResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private KafkaMessageProducer kafkaMessageProducer;

    @Mock
    private ObjectMapper objectMapper;

    private CourseServiceImpl courseService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();
        courseService = new CourseServiceImpl(courseRepository, kafkaMessageProducer, objectMapper);
    }

    @Test
    void createCourse_ValidCourse_Success() {
        // Mock repository save method
        Mockito.when(courseRepository.save(Mockito.any(Course.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Create a valid course DTO
        CourseDto courseDto = new CourseDto();
        courseDto.setNom("Course 1");
        courseDto.setParticipantCount(3);
        courseDto.setDateCourse("2023/05/29");

        Set<HorseDto> horses = new HashSet<>();
        horses.add(new HorseDto("Horse 1", 1));
        horses.add(new HorseDto("Horse 2", 2));
        horses.add(new HorseDto("Horse 3", 3));
        courseDto.setHorse(horses);

        // Call the createCourse method
        CourseResponse response = courseService.createCourse(courseDto);

        // Verify the response
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals("Course created successfully", response.getMessage());

    }

    @Test
    void createCourse_InvalidParticipantCount_ExceptionThrown() {
        // Create a course DTO with invalid participant count
        CourseDto courseDto = new CourseDto();
        courseDto.setNom("Course 2");
        courseDto.setParticipantCount(2); // Invalid count, should throw an exception
        courseDto.setDateCourse("2023/05/30");

        Set<HorseDto> horses = new HashSet<>();
        horses.add(new HorseDto("Horse 1", 1));
        horses.add(new HorseDto("Horse 2", 2));
        courseDto.setHorse(horses);

        // Call the createCourse method
        CourseResponse response = courseService.createCourse(courseDto);

        // Verify the response
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals("A course must have at least 3 participants.", response.getMessage());
    }

    // Add more test cases for other scenarios
}
