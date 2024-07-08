package com.Project.Bus_Booking_System.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "bus")
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name="Bus.Choose", query = "from bus where fromLoc =:from and toLoc=:to and startDate =:sdate")
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bus_id")
	private int busId;

	@NotEmpty
	@Column(name = "bus_name")
	private String busName;

	@NotEmpty
	@Column(name = "from_loc")
	private String fromLoc;

	@NotEmpty
	@Column(name = "to_loc")
	private String toLoc;

	@NotEmpty
	@Column(name = "start_date")
	private LocalDate startDate;

	@NotEmpty
	@Column(name = "end_date")
	private LocalDate endDate;

	@NotEmpty
	@Column(name = "start_time")
	private LocalTime startTime;

	@NotEmpty
	@Column(name = "end_time")
	private LocalTime endTime;

	@NotEmpty
	private String route;

	@NotEmpty
	@Column(name = "total_seats")
	private int totalSeats;

	@NotEmpty
	@Column(name = "available_seats")
	private int availableSeats;

	@NotEmpty
	private int price;
}
