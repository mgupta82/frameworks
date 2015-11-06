<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
Welcome : ${pageContext.request.userPrincipal.name}
<br/>
<a href="j_spring_security_logout">Logout</a>
</body>
</html>
