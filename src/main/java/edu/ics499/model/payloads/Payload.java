package edu.ics499.model.payloads;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//abstract?
@Entity
@Table(name="payloads")
public class Payload {
	@Id
	@GeneratedValue
	private String PayloadID;
	
	private String lastUpdatedTime;
	private String updateFrequency;
}
