package com.project.pt.repository;

import com.project.pt.model.Appointment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    List<Appointment> getAppointmentsByCustomer(String username, Pageable pageable);

    List<Appointment> getAppointmentsByTrainer(String username, Pageable pageable);

}
