<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : initParam.language}" />
<fmt:setBundle basename="messages"/>