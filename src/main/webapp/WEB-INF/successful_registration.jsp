<%@ include file="components/include.jsp"%>

<jsp:include page="/WEB-INF/components/head.jsp"/>
<jsp:include page="/WEB-INF/components/header.jsp"/>
<jsp:include page="/WEB-INF/components/menu_guest.jsp"/>

<div id="content">
    <div class="container">
        <div class="alert alert-success" role="alert">
            <fmt:message key="${requestScope.message}"/>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/components/footer.jsp"/>
