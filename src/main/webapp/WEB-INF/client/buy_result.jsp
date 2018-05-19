<%@ include file="../components/include.jsp"%>

<jsp:include page="/WEB-INF/components/head.jsp"/>
<jsp:include page="/WEB-INF/components/header.jsp"/>
<jsp:include page="/WEB-INF/components/menu_client.jsp"/>

<div id="content">
    <div class="container">
        <div class="alert alert-${requestScope.alert}" role="alert">
            <fmt:message key="${requestScope.buyResult}"/>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/components/footer.jsp"/>

