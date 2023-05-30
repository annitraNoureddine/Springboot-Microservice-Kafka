package com.pmu.course.repository;


import com.pmu.course.model.Horse;
import org.springframework.data.jpa.repository.JpaRepository;
public interface HorseRepository extends JpaRepository<Horse, Long> {
}
