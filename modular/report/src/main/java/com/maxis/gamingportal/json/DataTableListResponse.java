package com.maxis.gamingportal.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class DataTableListResponse {
	
    private String Result;
    
    private List<DataTableBean> Records;
 
    private int TotalRecordCount;
 
    private String Message;
 
    public DataTableListResponse(String Result, List<DataTableBean> Records,int TotalRecordCount) {
        this.Result = Result;
        this.Records = Records;
        this.TotalRecordCount = TotalRecordCount;
    }
 
    public DataTableListResponse(String Result, String Message) {
        this.Result = Result;
        this.Message = Message;
    } 
 
    @JsonProperty("Result")
    public String getResult() {
        return Result;
    }
 
    public void setResult(String Result) {
        this.Result = Result;
    }
 
    @JsonProperty("Records")
    public List<DataTableBean> getRecords() {
        return Records;
    }
 
    public void setRecords(List<DataTableBean> Records) {
        this.Records = Records;
    }
 
    @JsonProperty("TotalRecordCount")
    public int getTotalRecordCount() {
        return TotalRecordCount;
    }
 
    public void setTotalRecordCount(int TotalRecordCount) {
        this.TotalRecordCount = TotalRecordCount;
    } 
 
    @JsonProperty("Message")
    public String getMessage() {
        return Message;
    }
 
    public void setMessage(String Message) {
        this.Message = Message;
    }  
}	


