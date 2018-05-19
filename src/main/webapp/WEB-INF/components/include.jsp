<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : initParam.locale}" />
<fmt:setBundle basename="messages"/>