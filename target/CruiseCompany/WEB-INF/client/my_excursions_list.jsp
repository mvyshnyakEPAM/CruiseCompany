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
                <th>Excursion</th>
                <th>Port</th>
                <th>Price</th>
            </tr>
            </thead>
            <tbody>
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
