package edu.ics499.model.payloads;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

//abstract?
@Entity
@Table(name="payloads")
@Inheritance(strategy = InheritanceType.JOINED)
public class Payload {
	@Id
	@GeneratedValue
	private Long payloadID;
	
	private String lastUpdatedTime;
	private String updateFrequency;
	
	public Long getPayloadID() {
		return payloadID;
	}

	public void setPayloadID(Long payloadID) {
		this.payloadID = payloadID;
	}

	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getUpdateFrequency() {
		return updateFrequency;
	}

	public void setUpdateFrequency(String updateFrequency) {
		this.updateFrequency = updateFrequency;
	}
}
