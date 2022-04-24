package edu.ics499.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ics499.model.widgets.Widget;
/**
 * Widget Repository used for performing operations in DB involving widgets
 *
 */
public interface WidgetRepository extends JpaRepository<Widget, Long> {

}
