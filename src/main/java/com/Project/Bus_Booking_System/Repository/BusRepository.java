package com.Project.Bus_Booking_System.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.Project.Bus_Booking_System.Entity.Bus;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Bus - Rest API Controllers", description = "Bus schedules API")
@RepositoryRestResource(collectionResourceRel = "bus", path="bus")
public interface BusRepository extends JpaRepository<Bus, Integer> {

	public List<Bus> findByFromLocAndToLocAndStartDate(@Param("fromLoc") String from, @Param("toLoc") String to, @Param("startDate") LocalDate sdate);
}
