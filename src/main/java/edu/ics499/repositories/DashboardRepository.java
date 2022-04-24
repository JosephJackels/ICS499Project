package edu.ics499.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ics499.model.Dashboard;
/**
 * Dashboard Repository used for performing operations in DB involving dashboards
 *
 */
public interface DashboardRepository extends JpaRepository<Dashboard, Long> {
	Dashboard findByUserId(Long userID);
}
