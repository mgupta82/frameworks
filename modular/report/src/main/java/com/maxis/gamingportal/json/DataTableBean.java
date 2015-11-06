package com.maxis.gamingportal.json;

import com.fasterxml.jackson.annotation.JsonProperty;


public class DataTableBean {
	
	private String empId;
	
	private String empName;
	
	private String empDesignation;

	@JsonProperty("EmpId")
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	@JsonProperty("EmpName")
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@JsonProperty("EmpDesignation")
	public String getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

}
