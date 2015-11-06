<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<html>
	<head>
		<title>
			<tiles:insertAttribute name="title" ignore="true" />
		</title>
		<style>
		body{ margin-top:20px; margin-bottom:20px; background-color:#DFDFDF;}
		</style>
	</head>
	<body>
		<div style="border: #C1C1C1 solid 1px; border-radius:10px;">
			<!-- Header -->
			<tiles:insertAttribute name="header" />
			<!-- Body Page -->
			<div>
				<tiles:insertAttribute name="body" />
			</div>
			<!-- Footer Page -->
			<tiles:insertAttribute name="footer" />
		</div>
	</body>
</html>