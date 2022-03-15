package edu.ics499.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ics499.model.payloads.Payload;

public interface PayloadRepository extends JpaRepository<Payload, Long> {

}
