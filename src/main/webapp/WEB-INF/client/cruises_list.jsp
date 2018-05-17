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
            <c:forEach var="cruise" items="${requestScope.cruises}">
                <tr>
                    <td>${cruise.name}</td>
                    <td>${cruise.ports}</td>
                    <td><ctg:format-date date="${cruise.departure}" locale="${sessionScope.language}"/></td>
                    <td><ctg:format-date date="${cruise.arrival}" locale="${sessionScope.language}"/></td>
                    <td>${cruise.cruiseDuration}</td>
                    <td><fmt:message key="${cruise.shipClass.key}"/> </td>
                    <td>${cruise.price}</td>
                    <td><a href="${pageContext.request.contextPath}/company/client/show-cruise-info?cruise=${cruise.nameEn}" class="btn btn-warning font-weight-bold">Details</a> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <c:forEach var="number" begin="1" end="${requestScope.numberOfPages}">
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/company/client/show-cruises?page=${number}">${number}</a></li>
                </c:forEach>
            </ul>
        </nav>
    </div>
</div>

<jsp:include page="/WEB-INF/components/footer.jsp"/>
