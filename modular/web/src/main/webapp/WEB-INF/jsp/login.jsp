<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form method="POST" action="j_spring_security_check">
	Username : <input type="text" name="username"> <br/>
	Password : <input type="password" name="password"> <br/>
	<input type="submit" value="login"/>
</form>
