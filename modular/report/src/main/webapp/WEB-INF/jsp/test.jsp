<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>This is sample page for end to end testing of environment</h2>
<form method="post" action="testdb.htm">
<input type="submit" value="Test Database">
</form>
<form method="post" action="testpwd.htm">
<input type="submit" value="Test J2C Auth alias">
</form>
<form method="post" action="testex.htm">
<input type="submit" value="Test Exception">
</form>
<form method="post" action="testtab.htm">
<input type="submit" value="Test Table">
</form>
<h2><c:out value="${message}"/></h2>
