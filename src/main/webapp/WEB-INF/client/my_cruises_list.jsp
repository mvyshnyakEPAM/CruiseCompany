<%@ include file="../components/include.jsp"%>

<jsp:include page="/WEB-INF/components/head.jsp"/>
<jsp:include page="/WEB-INF/components/header.jsp"/>
<jsp:include page="/WEB-INF/components/menu_client.jsp"/>

<!-- Nav tabs -->
<div id="content">
    <div class="container">
        <table class="table table-bordered">
            <thead>
            <tr class="bg-primary text-white">
                <th>Cruise Name</th>
                <th>Route</th>
                <th>Departure</th>
                <th>Arrival</th>
                <th>Duration</th>
                <th>Class</th>
                <th>Price</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="cruise" items="${requestScope.myCruises}">
                <tr>
                    <td>${cruise.name}</td>
                    <td>${cruise.ports}</td>
                    <td><ctg:format-date date="${cruise.departure}" locale="${sessionScope.language}"/></td>
                    <td><ctg:format-date date="${cruise.arrival}" locale="${sessionScope.language}"/></td>
                    <td>${cruise.cruiseDuration}</td>
                    <td><fmt:message key="${cruise.shipClass.key}"/> </td>
                    <td>${cruise.price}</td>
                    <td><a href="${pageContext.request.contextPath}/company/client/show-my-excursions?shipName=${cruise.nameEn}" class="btn btn-warning font-weight-bold">Excursions</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/components/footer.jsp"/>
