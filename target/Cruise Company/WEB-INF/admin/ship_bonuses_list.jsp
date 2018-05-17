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
                <th>Bonus</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="bonus" items="${requestScope.shipBonuses}">
                <tr>
                    <td><fmt:message key="${bonus.key}"/></td>
                    <td><a href="${pageContext.request.contextPath}/company/admin/remove-bonus?ship=${sessionScope.ship.id}&&bonus=${bonus}" class="btn btn-warning font-weight-bold">Remove</a> </td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td>
                    <button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#bonuses">Add</button>
                    <div class="modal fade bd-example-modal-lg modal-open" id="bonuses" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="container">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr class="bg-primary text-white">
                                            <th>Bonus</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="bonus" items="${requestScope.bonuses}">
                                            <tr>
                                                <td><fmt:message key="${bonus.key}"/></td>
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/company/admin/add-bonus?bonus=${bonus}" class="btn btn-warning font-weight-bold">Add</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/components/footer.jsp"/>

