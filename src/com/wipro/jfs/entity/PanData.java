package com.wipro.jfs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "PAN_DATA", uniqueConstraints = { @UniqueConstraint(columnNames = "PAN_NO") })
public class PanData {

	@Id
	@Column(name = "PAN_NO", unique = true, nullable = false, length = 10)
	private String panNo;

	@Column(name = "CREDIT_SCORE", nullable = false)
	private Float creditScore;

	public PanData() {
		super();
	}

	public PanData(String panNo, Float creditScore) {
		super();
		this.panNo = panNo;
		this.creditScore = creditScore;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public Float getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(Float creditScore) {
		this.creditScore = creditScore;
	}

}
