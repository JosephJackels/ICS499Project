package edu.ics499.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ics499.model.Widget;

public interface WidgetRepository extends JpaRepository<Widget, Long> {

}
