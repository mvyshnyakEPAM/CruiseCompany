<%@ include file="../components/include.jsp"%>

<jsp:include page="/WEB-INF/components/head.jsp"/>
<jsp:include page="/WEB-INF/components/header.jsp"/>
<jsp:include page="/WEB-INF/components/menu_client.jsp"/>

<!-- Nav tabs -->
<div id="content">
    <c:if test="${empty requestScope.myCruises}">
        <div id="content">
            <div class="container">
                <div class="alert alert-warning" role="alert">
                        <fmt:message key="info_no_cruises"/>
                </div>
            </div>
        </div>
    </c:if>
    <c:forEach var="cruise" items="${requestScope.myCruises}">
        <div class="container" id="cruise-info">
            <div class="row">
                <div class="col-xl-12" align="center" style="border:1px solid #333; font-weight: bold; font-size: larger; padding: 5px">
                    ${cruise.name} (<fmt:message key="${cruise.shipClass.key}"/>)
                </div>
            </div>
            <div class="row">
                <div class="col-xl-9" style="border:1px solid #333; font-size: small">
                    <div style="padding: 5px">
                        <img src="<c:url value='/resources/images/logo_en_lg1.png'/>" class="img-resp" align="left">
                        <span style="font-weight: bold"><fmt:message key="ship_departure"/>: </span><ctg:format-date date="${cruise.departure}" locale="${sessionScope.language}"/><br>
                        <span style="font-weight: bold"><fmt:message key="ship_arrival"/>: </span><ctg:format-date date="${cruise.arrival}" locale="${sessionScope.language}"/><br>
                        <span style="font-weight: bold"><fmt:message key="ship_route"/>: </span>${cruise.ports}<br>
                        <span style="font-weight: bold"><fmt:message key="ship_passenger_capacity"/>: </span>${cruise.passengerCapacity}<br>
                        <span style="font-weight: bold"><fmt:message key="ship_staff"/>: </span>${cruise.staff}
                    </div>
                </div>
                <div class="col-xl-3" align="center" style="border:1px solid #333; vertical-align: text-bottom">
                    <div style="padding: 5px; font-size: large">
                        <p style="font-weight: bold"><fmt:formatNumber type="currency" currencySymbol="$" value="${cruise.price}" /></p>
                        <p style="font-weight: bold">
                            <button type="button" class="btn btn-warning btn-sm font-weight-bold" data-toggle="modal" data-target="#modal-${cruise.id}"><fmt:message key="action_bonuses"/></button>
                            <div class="modal fade bd-example-modal-sm" id="modal-${cruise.id}" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-sm">
                                    <div class="modal-content">
                                        <ul class="list-group">
                                            <c:forEach var="bonus" items="${cruise.bonuses}">
                                                <li class="list-group-item font-weight-bold"><fmt:message key="${bonus.key}"/></li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </p>
                        <p><a href="${pageContext.request.contextPath}/company/show-my-excursions?shipName=${cruise.nameEn}" class="btn btn-sm btn-warning font-weight-bold"><fmt:message key="action_excursions"/></a></p>
                    </div>
                </div>
            </div>
        </div>
        <br>
    </c:forEach>
</div>

<jsp:include page="/WEB-INF/components/footer.jsp"/>
