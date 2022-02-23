package edu.ics499.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ics499.model.Dashboard;

public interface DashboardRepository extends JpaRepository<Dashboard, Long> {
	Dashboard findByUserUserID(Long userUserID);
}
