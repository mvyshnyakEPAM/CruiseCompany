<%@ include file="../components/include.jsp"%>

<jsp:include page="/WEB-INF/components/head.jsp"/>
<jsp:include page="/WEB-INF/components/header.jsp"/>
<jsp:include page="/WEB-INF/components/menu_client.jsp"/>

<!-- Nav tabs -->
<div id="content" style="padding-top: 50px">
    <div class="container" id="cruise-info">
        <div class="row">
            <div class="col-xl-12" align="center" style="border:1px solid #333; font-weight: bold; font-size: larger; padding: 5px">
                ${sessionScope.cruise.name} (<fmt:message key="${sessionScope.cruise.shipClass.key}"/>) - ${sessionScope.cruise.cruiseDuration} <fmt:message key="ship_days"/>
            </div>
        </div>
        <div class="row">
            <div class="col-xl-9" style="border:1px solid #333; font-size: small">
                <div style="padding: 5px">
                    <img src="<c:url value='/resources/images/logo_en_lg1.png'/>" class="img-resp" align="left">
                    <span style="font-weight: bold"><fmt:message key="ship_ports_visited"/>: </span> ${sessionScope.cruise.portsVisited}<br>
                    <span style="font-weight: bold"><fmt:message key="ship_departure"/>: </span><ctg:format-date date="${sessionScope.cruise.departure}" locale="${sessionScope.locale}"/><br>
                    <span style="font-weight: bold"><fmt:message key="ship_arrival"/>: </span><ctg:format-date date="${sessionScope.cruise.arrival}" locale="${sessionScope.locale}"/><br>
                    <span style="font-weight: bold"><fmt:message key="ship_passenger_capacity"/>:  </span>${sessionScope.cruise.passengerCapacity}<br>
                    <span style="font-weight: bold"><fmt:message key="ship_staff"/>: </span>${sessionScope.cruise.staff}<br>
                    <button type="button" class="btn btn-warning btn-sm font-weight-bold" data-toggle="modal" data-target=".bd-example-modal-sm"><fmt:message key="action_bonuses"/></button>
                    <div class="modal fade bd-example-modal-sm" align="center" style="font-size: large" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <ul class="list-group">
                                    <c:forEach var="bonus" items="${sessionScope.cruise.bonuses}">
                                        <li class="list-group-item font-weight-bold"><fmt:message key="${bonus.key}"/></li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xl-3" align="center" style="border:1px solid #333; vertical-align: text-bottom">
                <div style="padding: 5px; font-size: large">
                    <p style="font-weight: bold">${sessionScope.cruise.freePlaces} <fmt:message key="ship_places"/></p>
                    <p style="font-weight: bold"><fmt:formatNumber type="currency" currencySymbol="$" value="${requestScope.price}" /></p>
                    <p><a href="${pageContext.request.contextPath}/company/pay-cruise" class="btn btn-warning font-weight-bold btn-block"><fmt:message key="action_pay"/></a></p>
                </div>
            </div>
        </div>
    </div>
    <br>
    <div class="container">
        <div class="row no-gutter">
            <c:forEach var="port" items="${sessionScope.cruise.ports}">
                <div class="col">
                    <button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#${port.nameEn}">${port.name}</button>
                    <div class="modal fade bd-example-modal-lg modal-open" id="${port.nameEn}" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="container">
                                    <p class="font-weight-bold" style="font-size: larger" align="center">${port}</p>
                                    <table class="table table-bordered table-sm">
                                        <thead>
                                        <tr class="bg-primary text-white">
                                            <th><fmt:message key="col_excursion"/></th>
                                            <th><fmt:message key="col_price"/> ($)</th>
                                            <th><fmt:message key="col_action"/></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="excursion" items="${port.excursions}">
                                            <tr>
                                                <td>${excursion.name}</td>
                                                <td>${excursion.price}</td>
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/company/add-excursion?excursion=${excursion.nameEn}" class=" btn-sm btn-warning font-weight-bold btn-block text-center"><fmt:message key="action_add"/></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <br>
    <div class="container" style="width:1000px;">
        <table class="table table-bordered table-sm">
            <c:if test="${not empty requestScope.excursions}">
                <thead>
                <tr class="bg-primary text-white">
                    <th><fmt:message key="col_excursion"/></th>
                    <th><fmt:message key="col_port"/></th>
                    <th><fmt:message key="col_price"/> ($)</th>
                    <th><fmt:message key="col_action"/></th>
                </tr>
                </thead>
            </c:if>
            <tbody>
                <c:forEach var="excursion" items="${requestScope.excursions}">
                    <tr>
                        <td>${excursion.name}</td>
                        <td>${excursion.port}</td>
                        <td>${excursion.price}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/company/remove-excursion?excursion=${excursion.nameEn}" class="btn btn-sm btn-warning font-weight-bold btn-block"><fmt:message key="action_remove"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="/WEB-INF/components/footer.jsp"/>
