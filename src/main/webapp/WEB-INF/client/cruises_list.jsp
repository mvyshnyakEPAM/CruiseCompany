<%@ include file="../components/include.jsp"%>

<jsp:include page="/WEB-INF/components/head.jsp"/>
<jsp:include page="/WEB-INF/components/header.jsp"/>
<jsp:include page="/WEB-INF/components/menu_client.jsp"/>

<!-- Nav tabs -->
<div class="container-fluid">
    <table class="table">
        <thead>
        <tr>
            <th>Cruise Name</th>
            <th>Route</th>
            <th>Departure</th>
            <th>Arrival</th>
            <th>Duration</th>
            <th>Class</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="cruise" items="${requestScope.cruises}">
            <tr>
                <td>${cruise.cruiseName}</td>
                <td>${cruise.ports}</td>
                <td>${cruise.departure}</td>
                <td>${cruise.arrival}</td>
                <td>${cruise.cruiseDuration}</td>
                <td>${cruise.shipClass}</td>
                <td>${cruise.price}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="/WEB-INF/components/footer.jsp"/>
