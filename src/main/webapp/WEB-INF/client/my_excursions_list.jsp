<%@ include file="../components/include.jsp"%>

<jsp:include page="/WEB-INF/components/head.jsp"/>
<jsp:include page="/WEB-INF/components/header.jsp"/>
<jsp:include page="/WEB-INF/components/menu_client.jsp"/>

<!-- Nav tabs -->
<div id="content">
    <c:if test="${empty requestScope.myExcursions}">
        <div id="content">
            <div class="container">
                <div class="alert alert-warning" role="alert">
                    <fmt:message key="info_no_excursions"/>
                </div>
            </div>
        </div>
    </c:if>
    <div class="container">
        <table class="table table-bordered">
            <c:if test="${not empty requestScope.myExcursions}">
                <thead>
                <tr class="bg-primary text-white">
                    <th><fmt:message key="col_excursion"/></th>
                    <th><fmt:message key="col_port"/></th>
                    <th><fmt:message key="col_price"/> ($)</th>
                </tr>
                </thead>
                <tbody>
            </c:if>
            <c:forEach var="excursion" items="${requestScope.myExcursions}">
                <tr>
                    <td>${excursion.name}</td>
                    <td>${excursion.port}</td>
                    <td>${excursion.price}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/components/footer.jsp"/>
