package com.maxis.gamingportal.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataTableResponse {
	
	private String Result;
	
	private DataTableBean Record;
	
	private String Message;
	
	public DataTableResponse(){}
	
	public DataTableResponse(String Result){
		this.Result = Result;
	}

	public DataTableResponse(String Result,DataTableBean Record){
		this.Result = Result;
		this.Record = Record;
	}	

	public DataTableResponse(String Result,DataTableBean Record,String Message){
		this.Result = Result;
		this.Record = Record;
		this.Message = Message;
	}
	
    @JsonProperty("Record")
    public DataTableBean getRecord() {
        return Record;
    }
 
    public void setRecord(DataTableBean Record) {
        this.Record = Record;
    }   
 
    @JsonProperty("Message")
    public String getMessage() {
        return Message;
    }
 
    public void setMessage(String Message) {
        this.Message = Message;
    }

    @JsonProperty("Result")
	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}
    
    
 	
	
}
