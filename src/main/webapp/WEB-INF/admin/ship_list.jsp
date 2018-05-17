<%@ include file="../components/include.jsp"%>

<jsp:include page="/WEB-INF/components/head.jsp"/>
<jsp:include page="/WEB-INF/components/header.jsp"/>
<jsp:include page="/WEB-INF/components/menu_admin.jsp"/>
<!-- Nav tabs -->
<div id="content">
    <div class="container">
        <table class="table table-bordered">
            <thead>
            <tr class="bg-primary text-white">
                <th>Ship Name</th>
                <th>Passenger Capacity</th>
                <th>Staff</th>
                <th>Class</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ship" items="${requestScope.ships}">
                <tr>
                    <td>${ship.name}</td>
                    <td>${ship.passengerCapacity}</td>
                    <td>${ship.staff}</td>
                    <td><fmt:message key="${ship.shipClass.key}"/> </td>
                    <td><a href="${pageContext.request.contextPath}/company/admin/show-ship-bonuses?ship=${ship.nameEn}" class="btn btn-warning font-weight-bold">Bonuses</a> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <c:forEach var="number" begin="1" end="${requestScope.numberOfPages}">
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/company/admin/show-ships?page=${number}">${number}</a></li>
                </c:forEach>
            </ul>
        </nav>
    </div>
</div>

<jsp:include page="/WEB-INF/components/footer.jsp"/>
