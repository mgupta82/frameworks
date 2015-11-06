<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="jquery/external/jquery.js"></script>
<link rel="stylesheet" href="jquery/jquery-ui.min.css" type="text/css" />
<script src="jquery/jquery-ui.min.js"></script>

<link rel="stylesheet" href="jquery/themes/jqueryui/jtable_jqueryui.min.css" type="text/css" />
<script src="jquery/external/json2.min.js"></script>
<script src="jquery/jquery.jtable.min.js"></script>

 <script>
 
  $(document).ready(function() {              
                //setup the jtable that will display the results
                $('#DataTableContainer').jtable({
                    title: 'Table of Employee',
                    paging: true, //Enable paging
                    pageSize: 10, //Set page size (default: 10)
                    sorting: true, //Enable sorting
                    actions: {
                        listAction: 'getData.htm'
                    },
                    fields: {
                        EmpId: {
                            title: 'Employee ID',
                            width: '30%'
                        },
                        EmpName: {
                            title: 'Employee Name',
                            width: '30%'
                        },
                        EmpDesignation: {
                            title: 'Employee Designation',
                            width: '15%'
                        }
                      }
 				});
                $('#DataTableContainer').jtable('load');              
            });    
 
 </script>

<div id="DataTableContainer" style="width:99%;"></div>
