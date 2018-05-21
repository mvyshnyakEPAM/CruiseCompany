<%@ include file="../components/include.jsp"%>

<jsp:include page="/WEB-INF/components/head.jsp"/>
<jsp:include page="/WEB-INF/components/header.jsp"/>
<jsp:include page="/WEB-INF/components/menu_client.jsp"/>

<!-- Nav tabs -->
<div id="content">
    <c:forEach var="cruise" items="${requestScope.cruises}">
        <div class="container" id="cruise-info">
            <div class="row">
                <div class="col-xl-12" align="center" style="border:1px solid #333; font-weight: bold; font-size: larger; padding: 5px">
                        ${cruise.name} (<fmt:message key="${cruise.shipClass.key}"/>)
                </div>
            </div>
            <div class="row">
                <div class="col-xl-9" style="border:1px solid #333; font-size: medium">
                    <div style="padding: 5px">
                        <img src="<c:url value='/resources/images/logo_en_lg1.png'/>" class="img-resp" align="left">
                        <span style="font-weight: bold"><fmt:message key="ship_ports_visited"/>: </span> ${cruise.portsVisited}<br>
                        <span style="font-weight: bold"><fmt:message key="ship_cruise_duration"/>: </span> ${cruise.cruiseDuration} <fmt:message key="ship_days"/><br>
                        <span style="font-weight: bold"><fmt:message key="ship_departure"/>: </span><ctg:format-date date="${cruise.departure}" locale="${sessionScope.locale}"/><br>
                        <span style="font-weight: bold"><fmt:message key="ship_arrival"/>: </span><ctg:format-date date="${cruise.arrival}" locale="${sessionScope.locale}"/><br>
                        <button type="button" class="btn btn-warning btn-sm font-weight-bold" data-toggle="modal" data-target="#${cruise.id}"><fmt:message key="action_route"/></button>
                        <div class="modal fade bd-example-modal-sm" id="${cruise.id}" align="center" style="font-size: large" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-sm">
                                <div class="modal-content">
                                    <ul class="list-group">
                                        <c:forEach var="port" items="${cruise.ports}">
                                            <li class="list-group-item font-weight-bold">${port}</li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-3" align="center" style="border:1px solid #333; vertical-align: text-bottom">
                    <div style="padding: 5px; font-size: large">
                        <p style="font-weight: bold">${cruise.freePlaces} <fmt:message key="ship_places"/></p>
                        <p style="font-weight: bold"><fmt:formatNumber type="currency" currencySymbol="$" value="${cruise.price}" /></p>
                        <p><a href="${pageContext.request.contextPath}/company/cruise-info?cruise=${cruise.nameEn}" class="btn btn-warning font-weight-bold"><fmt:message key="ship_details"/></a></p>
                    </div>
                </div>
            </div>
        </div>
        <br>
    </c:forEach>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <c:forEach var="number" begin="1" end="${requestScope.numberOfPages}">
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/company/cruises?page=${number}">${number}</a></li>
            </c:forEach>
        </ul>
    </nav>
</div>

<jsp:include page="/WEB-INF/components/footer.jsp"/>
