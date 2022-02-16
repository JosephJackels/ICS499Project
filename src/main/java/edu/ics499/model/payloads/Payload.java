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
	
	public String getPayloadID() {
		return PayloadID;
	}

	public void setPayloadID(String payloadID) {
		PayloadID = payloadID;
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
